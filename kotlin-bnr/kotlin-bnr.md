# Kotlin - Notes

Kotlin version at the time of writing: `1.3.71`

Kotlin is great b/c:
- It has amazing tooling support, and it is IntelliJ IDEA (the integration between the two has been carefully designed)
- It is developed and backed by JetBrains
- It integrates most of the functional programming constructs natively and via the _Arrow_ library
- It is popular, implying that people are getting paid to write it (and finding competent employees as well)
- It is recent and modern
- It offers a seamless interoperability w/ Java & its ecosystem (and thus it is an  _agile_ language choice)
- It can be used in Gradle build files
- It is meant to last since it has been chosen as an official language for Android app development
- The _Orange Book_ is out this year+

2011: Kotlin was first announced
2017: Google announces that Kotlin would be an officially supported development path for Android

Kotlin is _platform-agnostic_ since it runs:
- On the JVM
- Natively

It can be used to write:
- Web apps
- Native Windows/macOS/Linux apps
- JS apps
- Android apps

Style guides:
- (JetBrains)[https://kotlinlang.org/docs/reference/coding-conventions.html]
- (Google)[https://android.github.io/kotlin-guides/style.html]

## Chapter 1 - Your first Kotlin Application

IntelliJ means _Intelligent Java Integrated Development Environment_ (+ 'A' for the _idea_ pun)

Download IntelliJ IDEA: <https://jetbrains.com/idea/download>

Kotlin features a toolchain that _targets_ several platforms
Kotlin supports at most Java 1.8
The JDK is required in order to let the toolchain properly convert Kotlin code to JVM bytecode (_i.e._, _targeting_ the JVM)

Create a Kotlin IntelliJ project w/o a build tool:
- Select the `Kotlin` section in the left margin
- Select JVM IDEA

The pane of the left of IntelliJ is called the _project **tool window**_
The small triangles are called _disclosure arrows_

A project: all the source code for a program, along w/ information about **dependencies** and **configuration**
A module: a discrete unit of functionality that can be run, tested, and debugged independently

*.iml files contain information about modules (whether submodule or parent)
The .idea/ directory contains information about the entire project + those specific to your interactions

In any Kotlin/JVM project, the JDK standard library and the `KotlinJavaRuntime` are added as dependencies

The Kotlin standard library is called _stdlib_ as well

<https://jetbrains.org/intellij/sdk/docs/basics/project_structure.html>

`src/` is where to put your Kotlin source files

`<A-Ins>` in the Project Tool Window to create a new file

Kotlin source files have the `.kt` extension
Source file name have the same case as the class name

IMPORTANT: since Kotlin 1.3, the main parameter is not necessary

In macOS, the following default IntelliJ shortcuts are assigned:
- `<C-S-R>` to run
- `<C-S-D>` to debug

`<A-4>`: Run Tool Window

When using IntelliJ w/o a build tool, the run command is equivalent to `mvn compile`

On macOS, there might be an issue related to the `JavaLauncherHelper` displayed in the run tool window due to the way the JRE is installed on macOS
-> It is possible to remove it but requires a lot of efforts

_Event Log Tool Window_

How the Kotlin/JVM compilation works by clicking the `Run` button:
- IntelliJ compiles the Kotlin code into JVM bytecode using the `kotlinc-jvm` compiler
- If no error occurred, IntelliJ moves on to the execution phase
- During the latter, the bytecode is executing on the JVM

Kotlin REPL (Read Evaluate Print Loop): a tool that allows code to be tested w/o creating a file or running a complete program

Tools > Kotlin > Kotlin REPL
-> `<C-RET>` to evaluate

The REPL can use your current code if you compiled it before

The REPL is able to automatically import libraries

Use the `res*` variables to reuse previous results
`println` works in it as intended

The REPL reads the code, evaluate it, and prints out the resulting value or side effect

Kotlin can be written using any plain text editor
IntelliJ enables you to write syntactically and semantically correct Kotlin code
-> Context-sensitive suggestions, automatic code completion, powerful refactoring capabilities...

**IMPORTANT:** each OS has its own JVM bytecode instruction set

The JVM acts as a bridge between the bytecode and the underlying OS (and thus indirectly hardware)

Since Kotlin can be converted to the bytecode that the JVM can execute, it is considered a _JVM language_
-> Can also negates this VM layer by compiling to native

Kotlin & Scala addresses the _shortcomings_ of Java

Kotlin does not support the `0.` floating-point value expressions
`3 / 5` -> `0`
`3.0 / 5` -> `0.6`
`3 / 5.0` -> `0.6` (valid for `+`, `-`, `*`, `%` as well)
`512 % 360` -> `152`
`-3.absoluteValue` -> `-3`
`(-3).absoluteValue` -> `3`
`PI` -> `3.141592653589793`
`1.2.pow(4)` -> `2.0736`
`round(1.50)` -> `2.0`
`round(1.49)` -> `1.0` 
`min(-120, 50)` -> `-120` (works only w/ 2 numbers)
`max(-120, 50)` -> `50`
`4.0.withSign(5.0 - 6)` -> `-4.0`

<http://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/index.html>
