# Angular for Beginners, Angular University - Notes

## Helicopter View

https://angular.io/

Goal: learn the most commonly used features

> In Angular you will be using 10% of its features maybe 90% of the time!

Topics:
- Custom components with `@Component`
- Components `@Input` and `@Output`, event Emitters
- `ngFor`
ngIf`
ngClass`
ngStyle`
ngSwitch`
Built-In Pipes
Async Pipe
Custom Pipes
`@Injectable` and Custom Services
HTTP Client - `GET` `POST` `PUT` `DELETE`

This course is a subset of the _Angular Core Deep Dive_ course

Angular University is always updated the latest Angular version


## Development Environment Setup

Installation requirements:
- Node.js (not LTS, since it works better w/ tools _e.g._, Angular CLI)
- IDE: this course is using WebStorm

(Avoid using Node.js from GraalVM: LTS + apparently present only for JVM interop)

Installing Node.js on Ubuntu:
```bash
$ curl -sL https://deb.nodesource.com/setup_13.x | sudo -E bash -
```

This will configure the Node source APT repositories by:
- Adding the GPG keys
- Creating the `/etc/apt/sources.list.d/nodesource.list` file

```bash
$ sudo apt install nodejs
```
This will also install `npm`

Verify:
```bash
$ node --version
$ npm --version
```
(You can use `which` to be sure of which executable is used & `which -a` to see the available ones)

Installing Angular CLI:

https://cli.angular.io/

Listing installed packages:

Local:
```bash
$ npm ls --depth 0
/home/alex/prj/notes/angular-university/angular-for-beginners
└── (empty)
```

Global:
```bash
$ npm ls -g --depth 0                        
/usr/lib
└── npm@6.14.4
```
Note: w/ GraalVM, npm is not listed here (_i.e._, `(empty)` is displayed)


Installing Angular:
```bash
$ sudo npm install -g @angular/cli
```

Verify:
```bash
$ npm ls -g --depth 0                        
/usr/lib
├── @angular/cli@9.1.1
└── npm@6.14.4
```

```bash
$ ng --version
```

List `ng` commands:
```bash
$ ng
...
  new (n) Creates a new workspace and an initial Angular app.
...
  serve (s) Builds and serves your app, rebuilding on file changes.

```

Creating a new _Angular application_:
```bash
$ ng n <app name>
```
(Do not use Angular Routing yet & use _plain_ CSS)
Note: if the cwd is already managed by `git`, its initialization will be skipped (there will be .gitignore anyway )

- Creates a set of files
- **IMPORTANT:** installs several npm dependencies via `npm i` (in `node modules`):
```bash
$ npm ls --depth 0
```
`node_modules` is called the _library root_

(`⠹` rocks!)

Build & serve the app via _hot reloading_:
```bash
$ cd <app name>
$ npm start
# OR
$ ng s
```

In the `package.json` file, there are the following `npm` _scripts_:
- `ng`
- `start`
- `build`
- `test`
- `lint`
- `e2e`
-> Thus `npm start` runs the `start` script (which is equivalent to `ng serve`

https://docs.npmjs.com/misc/scripts

`npm start` is the command used by IntelliJ in the `Angular CLI server` command

`Angular Application` is used to open or debug your **running** application

As you can notice, Angular is not only a web framework: there are also many tools _e.g._, the CLI
