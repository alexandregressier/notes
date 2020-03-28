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


## Chapter 2 - Variables, Constants, and Types

In Kotlin, trailing `;` are optional, and therefore not recommended

**variable:** an element that holds a value; variable may be read-only or mutable

**constant:** an element that holds a value that cannot be changed

**type:** a classification of data; a variable's type determines the nature of the values it can hold

Kotlin features _type-checking_: prevents the assignment of the wrong kind of data to a variable or constant
-> The resulting error is called a _type mismatch_

INTELLIJ TIP: type `main` (or `psvm`) to use a template
-> Use `foo.sout` as well

`main` is the _entry point_ of the program

In Kotlin, a type annotation is called a _type specification_

You assign an _instance_ of type to a variable

`var` declares a mutable variable (a.k.a., read-only variables)
`val` declares an immutable variable (a.k.a., a constant)
-> From an FP perspective though, vals and vars are both considered variables

`=` is called the _assignment operator_

FUNDAMENTAL:
- `val x: Int = 5` is called a _variable definition_
- `val x` is called a _variable declaration_

_type system:_ a system in which the compiler labels source code w/ type information for checking
-> Kotlin features a _static_ type system (by contrast to _dynamic_)

`+=` is called the _addition and assignment operator_

IMPORTANT: `String`s have a `length` whereas `Collection`s have a `size`

Kotlin built-in types (non-exhaustive):

|    Type   | Description                    | Example Literal/expressions                                      |
|:---------:|--------------------------------|--------------------------------------------------------------------|
| `String`  | Textual data                   | `"Estragon"`<br>`"happy meal"`                                     |
| `Char`    | Single character               | `'X'`<br>Unicode character U+0041                                  |
| `Boolean` | True/False values              | `true`<br>`false`                                                  |
| `Int`     | Whole number                   | `"Estragon".length`<br>`5`                                         |
| `Double`  | Decimal numbers                | `3.14`<br>`2.718`                                                  |
| `List`    | Collections of elements        | `3, 1, 2, 4, 3`<br>`"root beer", "club soda", "coke"`              |
| `Set`     | Collections of unique elements | `"Larry", "Moe", "Curly"`<br>`"Mercury", "Venus", "Earth", "Mars"` |
| `Map`     | Collections of key-value pairs | `"small" to 5.99, "medium" to 7.99, "large" to 10.99`              |

`Map` define _mappings_ of elements

*collection types:* data types that represent groups of data elements (_e.g._, list, set, map)

vals are useful for guarding accidental reassignment of variables
-> Use a val every time you do not need a var

IntelliJ signals vars that can made into a val by analyzing the code statically

Enter is to Windows what return is to macOS
-> _Option-Return_ for _Alt-Enter_

Kotlin features _type inference_: the ability of the compiler to automatically resolve/infer type specifications from the type of the assigned expressions

Type inference should be used everywhere the type is unambiguous
-> Make the refactoring easier

IMPORTANT: when doing FP, since you only have function signatures in mind, it is useful explicitly specify the result type

INTELLIJ TIP: `<C-S-P>` to show the type of an expression

vals can return different values when they hold a class reference

_Compile-time constants_ are opposed to runtime constants (_i.e._, vals)
- They act like `#define`s in C
- They cannot contain any code that require evaluation (_e.g._, a function call) because they are not accessible by the JVM
- Think of them like the ones you filter w/ Maven
- They are subject to compiler optimizations (e.g., constant folding)

```kotlin
const val MAX_EXPERIENCE = 5000
```
A compile-time constant must defined outside of any function

`const` is called a _modifier_ as well

Compile-time constants can only be of the following types:
- `String` (even if it is a class in Java)
- `Int`
- `Double`
- `Float`
- `Long`
- `Short`
- `Byte`
- `Char`
- `Boolean` (not Bool)

There are 8 primitive types in Java (minus `String`)

Knowing how to inspect the Java equivalent of Kotlin code is a great technique for understanding how Kotlin works

Tools > Kotlin > Show Kotlin Bytecode

IMPORTANT: behind the scenes, the compiler optimizes `Int`s into the appropriate primitive type (_e.g._., `Short`)

You can translate the bytecode back to Java to make the reading easier

The bytecode demonstrates that type inference is working

In Java, there are 2 kinds of types:
- Primitive types; LowerCamelCased
- Reference types (defined by classes, since a clas define a type); UpperCamelCased

All primitives in Java have a corresponding reference type (a.k.a. boxed type)
-> Mainly b/c of `<T>` w/ generics (primitive types do not define a type in the sense of `T`)
-> Also b/c of OO features

Primitive types have better performance though

Unlike Java, Kotlin only offer boxed types, which can be later optimized into primitive types (like Scala)
Kotlin gives you the ease of reference types w/ the performance of primitives under the hood

Neither Java nor Kotlin require importing `java.lang.String`
-> `String` is present in package `java.lang` which is imported by default in all java programs
   
<https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html>
