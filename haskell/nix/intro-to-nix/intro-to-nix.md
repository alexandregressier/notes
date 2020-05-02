# Intro to Nix, YouTube - Notes

_Nix_ is not be confused w/ _nix*_, which designates Unix-family OSes

The Nix logo is 6 lambdas circled around

Nix is:
- A package manager
- A language (Haskell-like)
- A package library ecosystem: Nixpkgs
- An OS: NixOS
- A deployment system: NixOps
- Distributed systems deployment tool: Disnix
- Hydra CI system
- ...

The package manager:
- Pure
- Deterministic
- Composable
- Elegant
-> Just like FP functions

Swift has a lot of ideas coming from FP
React does not allow mutations

The big idea:
- FP has taught the world that:
  - Global state must be banished
  - **Effects should be controlled**
    - _Effect_: something that changes the state of the world (_e.g._, `print`)
    - Mutation should be minimized (mutation is also an effect)
-> Adopted in programs, but what about OSes (userland)?

- OSes are still built on globals & mutation
  - Example: `/usr/local/bin/git` is accessible to everyone it can be changed (global & mutable state of the OS, for example via system upgrade)
  - What happens when you install a package?

**Problems:**
- In-place mutation:
  - Cannot be undone: `git` is overwritten forever
  - Is hard to trace
  - Hard to do atomically: upgrades are dangerous (imagine library dependencies being installed, & the computer shuts off)
  - Complex dependencies become hard or impossible to support: _the dreaded diamond_ (like w/ C++)
    - Imagine a program needing 2 libraries, & those 2 requires another library (_e.g., OpenSSL) but in 2 different versions: impossible to install on a Linux system
    - Haskell's Cabal had this problem for quite long
  - Library overwrites can lead to unintended breakage
  - Rollbacks can only be done via snapshotting: a fragile & non-composable solution
  - Package composition is unwieldy & error-prone (_e.g._, `/.configure` scripts)
  - Declarative system configuration is extremely difficult (IaaC) & systems tend to "drift" (hard to rollback)
    - Files represent the state of the system
    - Docker also have this problem
    - Somebody/something did something that you need & you are unsure of who it was
    - Your specification no longer matches your system
  - Testing your packages in different configurations, environments... is a nightmare
    - No one does it b/c it is so hard to do
  
**The solution:**
> "Don't do that." -- Nix

Deterministic packages:
- A Nix package:
  - Is identified by a hash function (deterministic by essence) of:
    - All its inputs (any thing): source files, headers, compilation flags, environment variables...
    - All its dependencies: other Nix packages (**IMPORTANT:** which are also inputs able to change the hash, like in a _Merkle tree_)
    - All its outputs
  - No cheating: packages are built in an **isolated environments** w/ access only to declared inputs
  - No time: all timestamps are fixed at the Unix epoch
    - If you would build a package 100 years ago or in the future, you would still get the same hash
    - No time-dependent event dependency
    - No `sleep` & the like
  - No shortcuts: no incremental builds & often disable parallel builds if they cannot be proven to be deterministic (only when creating the package: you can still use parallel builds while dev)
  - _Package definitions_ are like pure functions: given the same input(s) they will always produce the same output
    - Like a pure function: only uses its inputs to produce its outputs
  - As a consequence to determinism, all package builds can be safely cached

No globals / Immutable env:
- `/usr/local/bin` is a mutable global: a mutable list of all installed executables
  + The contents of the executables are also mutable (via an upgrade)
- Nixpkgs can obviously not use such paths
- Instead, each package refers to its dependencies using their _unique hash_
  - The only way to have dependencies
- If a dependency changes, the hash of the using package changes too (-> Merkle tree)
- A _user environment_ is represented by a _profile_ which is a set of _symlinks_ to packages
  - `which git` would result in `git -> /nix/store/<hash>/git`
  - When upgrading, `which git` would result in `git -> /nix/store/<another hash>/git`
  - `brew` uses the same symlink techniques as well, but still has to install them in a global location
- **IMPORTANT:** the set of packages that your package depends on is also hashed
  - Your profile gets a new hash, and a new symlink to your profile is used
  - **FUNDAMENTAL:** The switch from one profile is atomic b/c of this
    - There is no moment where you have half of the packages
- It is still a problem if you have two package executables w/ the same name on your `PATH`
  - No problem w/ dev libraries though

A lot of dev package managers (_e.g.,_ Python's virtual environments) have isolated environments, but Nix has that all the way down to the kernel

Implications:
- Packages can have complex dependency requirements w/o affecting other packages
  - Examples: if `curl` & `git` need different versions of `openssl`, both versions can coexist
- Declarative definitions are:
  - Idempotent by essence, since everything is a pure function (_e.g._, run an installation command still get the same output)
    - _Desired state_
-  Easy to _version control_ & include entire dependency tree w/ a **single** hash
- Useful for teams that want people to have the **exact** same development environment
- Changes/upgrades are non-destructive:
  - They can be done **atomically**
  - You never overwrite old files: you create new files & you point to them
  - **IMPORTANT:** you can always roll back
  - The NixOS does that at the **driver level**!

Nix does **not fill up your drive** b/c it uses similar techniques to how Git works (-> it has a garbage collector that detects unused packages)

Example:
- Nix only works on any Linux distro or macOS (there has been reports that it works on Windows subsystems for Linux)
  - Can be installed next to `apt` or `yum` b/c **it does nothing global** & won't touch your existing system packages
  - There are a lot things that you probably don't want to install w/ Nix on macOS: XCode...
    - Is not as nice on macOS as it is on Linux, but good enough to use regularly
```bash
$ curl -L https://nixos.org/nix/install | sh
```
- Install `git` in your profile:
```bash
$ nix-env -f '<nixpkgs>' -iA git
# As indicated by the command: if you do not want leave your current shell
$ sh ~/ .nix-profile/etc/profile.d/nix.sh
```
- Open a shell w/ `git` available in a _temporary profile_:
```bash
$ nix-shell -p git
```
- Open a shell w/ just `git` available in a _temporary profile_:
  - Does not quite work on macOS (b/c Nix does not have that much control)
```bash
$ nix-shell --pure -p git
```
- Find the real path of a package:
```bash
> realpath $(which git)
/nix/store/rdz130p4ss02lyasi2d9skai2b624vic-git-2.11.0/bin/git
```
**IMPORTANT:** there are no directory tree in the Nix store: everything is at depth 0

- Write scripts that can install their own dependencies w/o affecting the system:
```bash
nix-shell -p git --run <<'HEREDOC'
  git status
HEREDOC
```
- Show a graph of the dependencies of `git`:
```bash
nix-store --query --tree $(which git)
# Using `xdot`
xdot <(nix-store --query --graph $(which git))
# Or using `dot` (macOS)
nix-store --query --graph $(which git) | dot -Tpng > graph.png && open graph.png
```
The `--graph` commands:
- Must be ran on a `nix-shell`
-> Build-time dependencies are also listed (_e.g.,_ `clang` is there for `git`)
-> Note that the arrows points to towards the root

Nix is great b/c listing dependencies of a traditional package is already a hassle

**IMPORTANT:** there is a difference between _runtime_ & _build-time_ dependencies
-> Nix "clojures"

A package cannot depend on itself

- Obviously, `git` on macOS has different hash from the one on Linux
  - First b/c it is compiled for another kernel
  - And then b/c it has other dependencies like `Libsystem-osx-10.11.6`

Are there as many packages for macOS as there is for Linux?
