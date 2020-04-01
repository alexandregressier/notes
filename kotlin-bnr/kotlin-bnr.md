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
Kotlin supports at least Java 1.8
The JDK is required in order to let the toolchain properly convert Kotlin code to JVM bytecode (_i.e._, _targeting_ the JVM)

Create a Kotlin IntelliJ project w/o a build tool:
- Select the `Kotlin` section in the left margin
- Select JVM IDEA

The pane of the left of IntelliJ is called the _project **tool window**_
The small triangles are called _disclosure arrows_

**A project:** a single organizational unit that encapsulates all of a project’s source code, libraries, and build instructions

**A module:** a discrete unit of functionality that can be run, tested, and debugged independently

`*.iml` files contain information about modules (whether a submodule or a parent module)
The `.idea/` directory contains information about the entire project + those specific to your interactions w/ the IDE

`src/` is where to put your Kotlin source files

`<A-Ins>` in the Project Tool Window to create a new file

Kotlin source files have the `.kt` extension
Source file name have the same case as the class name

<https://jetbrains.org/intellij/sdk/docs/basics/project_structure.html>

The Kotlin standard library is called _stdlib_ as well

In any Kotlin/JVM project, the dependencies are:
- The Java API (provided by the JDK)
- The `KotlinJavaRuntime` which contains:
  - `kotlin-reflect.jar` (Different)
  - `kotlin-stdlib.jar`
  - `kotlin-stdlib-jdk7.jar`
  - `kotlin-stdlib-jdk8.jar`
  - `kotlin-test.jar` (Different)

In any Kotlin Gradle with Kotlin DSL project, the dependencies are:
- The Java API (provided by the JDK)
- The Kotlin JVM Gradle plugin
- The Kotlin `stdlib-jdk8` dependency, which brings:
  - `kotlin-stdlib`
  - `kotlin-stdlib-common` (Different)
  - `kotlin-stdlib-jdk7`
  - `kotlin-stdlib-jdk8`
  - `jetbrains:annotations` (Different)

`main` is the _entry point_ of the program

IMPORTANT: since Kotlin 1.3, `args: Array<String>` is no longer required in the `main` function

In macOS, the following default IntelliJ shortcuts are assigned:
- `<C-S-R>` to run
- `<C-S-D>` to debug

`<A-4>`: Run Tool Window

When using IntelliJ w/o a build tool, the "Run" command is equivalent to `mvn compile`

On macOS, there might be an issue related to the `JavaLauncherHelper` displayed in the run tool window due to the way the JRE is installed on macOS
-> It is possible to remove it but requires a lot of efforts

_Event Log Tool Window_

How the Kotlin/JVM compilation works by clicking the "Run" button:
- IntelliJ compiles the Kotlin code into JVM bytecode using the `kotlinc-jvm` compiler
- If no error occurred, IntelliJ moves on to the execution phase
- During the latter, the bytecode is executing on the JVM

Kotlin REPL (Read Evaluate Print Loop): a tool that allows code to be tested w/o creating a file or running a complete program

Tools > Kotlin > Kotlin REPL
-> `<C-RET>` to evaluate

REPL features:
- Interaction w/ your code (if compiled beforehand)
- Automatic handling of `val` reassignments (by overwriting them)
- Automatic resolution of imports

Use the `res*` variables to reuse previous results
`println` works in it as intended

> The REPL reads the code, evaluate it, and prints out the resulting value or side effect

Kotlin code can be written using any plain text editor
IntelliJ enables you to write syntactically and semantically correct Kotlin code
-> Context-sensitive suggestions, automatic code completion, powerful refactoring capabilities...

**IMPORTANT:** each OS has its own JVM bytecode instruction set

The JVM acts as a bridge between the bytecode and the underlying OS (and thus indirectly hardware)

Since Kotlin can be converted to the bytecode that the JVM can execute, it is considered a _JVM language_
-> Can also negates this VM layer by compiling to native

> Kotlin & Scala addresses the _shortcomings_ of Java

Kotlin does not support the `0.` floating-point value expressions
`3 / 5` -> `0`
`3.0 / 5` -> `0.6`
`3 / 5.0` -> `0.6` (valid for `+`, `-`, `*`, `%` as well)
`512 % 360` -> `152`
`-3.absoluteValue` -> `-3`
`(-3).absoluteValue` -> `3`
`PI` -> `3.141592653589793`
`1.2.pow(4)` -> `2.0736` (note the two meanings of `.`)
`round(1.50)` -> `2.0`
`round(1.49)` -> `1.0` 
`min(-120, 50)` -> `-120` (works only w/ 2 numbers)
`max(-120, 50)` -> `50`
`4.0.withSign(5.0 - 6)` -> `-4.0`

Example of `Math` usage:
```kotlin
val karma = (Math.random().pow((110 - healthPoints) / 100.0) * 20).toInt()
```
-> `pow()` can both be used a function w/ 2 parameters or a method w/ 1 parameter

<http://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/index.html>


## Chapter 2 - Variables, Constants, and Types

Like C & Java, Kotlin supports the two comment types `//` and `/* */`

IMPORTANT: do not trust your future self and comment your code
-> Comments can be thought of as notes

In Kotlin, trailing `;` are optional (and therefore not recommended)

**variable:** an element that holds a value; variable may be read-only or mutable

**constant:** an element that holds a value that cannot be changed

**type:** a classification of data; a variable's type determines the nature of the values it can hold

Kotlin features _type-checking_: prevents the assignment of the wrong kind of data to a variable or constant
-> The resulting error is called a _type mismatch_

INTELLIJ TIP: type `main` (or `maina`, `psvm`) to use a _live template_
-> Use `foo.sout` as well (called a _postfix template_)

In Kotlin, a type annotation is called a _type specification_ (and _type definition in the book_)

You assign an _instance_ of a type to a variable

`var` declares a mutable variable (a.k.a., read-only variable)
`val` declares an immutable variable (a.k.a., a constant)
-> From an FP perspective though, vals and vars are both considered variables

`=` is called the _assignment operator_

FUNDAMENTAL:
- `val x: Int = 5` is called a _variable definition_
- `val x` is called a _variable declaration_
- `x = 5` is called a _variable assignment_ (prohibited in FP)

**Type system:** a system in which the compiler labels source code w/ type information for checking
-> Kotlin features a _static_ type system (as opposed to _dynamic_, in languages like Groovy)

`+=` is called the _addition and assignment operator_

IMPORTANT: `String`s have a `length` whereas `Collection`s have a `size`

Kotlin built-in types (non-exhaustive):

| Type      | Description                        | Example Literal/expressions                                        |
|-:-:-------|------------------------------------|--------------------------------------------------------------------|
| `String`  | Textual data                       | `"Estragon"`<br>`"happy meal"`                                     |
| `Char`    | Single character                   | `'X'`<br>Unicode character U+0041                                  |
| `Boolean` | True/False values                  | `true`<br>`false`                                                  |
| `Int`     | Whole number                       | `"Estragon".length`<br>`5`                                         |
| `Double`  | Decimal numbers (64-bit precision) | `3.14`<br>`2.718`<br>`3.14e10`                                     |
| `Float`   | Decimal numbers (32-bit precision) | `3.14f`<br>`2.718f` (`f` is case-insensitive)                      |
| `List`    | Collections of elements            | `3, 1, 2, 4, 3`<br>`"root beer", "club soda", "coke"`              |
| `Set`     | Collections of unique elements     | `"Larry", "Moe", "Curly"`<br>`"Mercury", "Venus", "Earth", "Mars"` |
| `Map`     | Collections of key-value pairs     | `"small" to 5.99, "medium" to 7.99, "large" to 10.99`              |

(`Byte` is not listed)

Binary (_e.g.,_ `0b00001011`) and hexadecimal (_e.g._, `0x0F`) are supported
-> Not specific types: they are other ways to express numbers, and are to be assigned to number types (_e.g._, `Int`)
-> Octal notation is not supported

`Map` define _mappings_ of elements

**Collection types:** data types that represent groups of data elements (_e.g._, list, set, map)

IMPORTANT: Kotlin allows underscores to make number literals more readable:
`val oneMillion = 1_000_000`
-> Can be placed anywhere in the literal

FUNDAMENTAL: behind the scenes, the compiler optimizes `Int`s into the appropriate primitive type (_e.g._., `Short`)

PRO TIP: try to use the `Number` type every time you can (especially for class properties or parameter types, o/w let _type inference_ do the work) b/c it does implicit casts, _e.g._:
`val a: Number = 5.0` -> Java type: `Double`
`val b: Number = 5` -> Java type: `Int`
`val c: Number = a + b` -> Does not compile (type mismatch) (unlike in TypeScript, which is smarter in this case)
`val d: Number = Int.MAX_VALUE + 1` -> Java type: `Long`
-> Note that although the Java type is shown, the Kotlin type (here `Number`) stays the same
-> WARNING: the error `a + b` could be problematic, using `Double` could be preferable when combining `Number`s

Good `Number` use case:
`val l = listOf(1, 2, 3.0)` -> Infered Kotlin type: `List<Any>`
`val l: List<Number> = listOf(1, 2, 3.0)` -> Kotlin type: `List<Number>`
-> But _e.g._, list folding does not work for both

<https://kotlinlang.org/docs/reference/basic-types.html>

vals are useful for guarding accidental reassignment of variables
-> Use a val every time you do not need a var (even in imperative programming)

IntelliJ signals vars that can made into a val by analyzing the code statically

Enter is to Windows what return is to macOS

> `Option-Return` for `Alt-Enter`

Kotlin features _type inference_: the ability of the compiler to automatically resolve/infer types from the type of the assigned expressions

To _label_ types

IMPORTANT: type inference should be used everywhere the type is unambiguous
-> Make the refactoring easier since this is another abstraction

IMPORTANT: when doing FP, since you only have function signatures in mind, it is useful explicitly specify the result type of a function

INTELLIJ TIP: `<C-S-P>` to show the type of an expression (when ambiguous, choices are prompted)

TO UNDERSTAND: vals can return different values when they hold a class reference

_Compile-time constants_ (as opposed to _runtime constants_: vals):
- Same purpose as `#define`s in C
- Should be thought of like the ones you would filter w/ a build tool (_e.g._, Maven)
- Cannot contain any code that require evaluation (_e.g._, a function call) because they are not accessible by the JVM
- Are subject to compiler optimizations (e.g., constant folding)

```kotlin
const val MAX_EXPERIENCE = 5000
```
IMPORTANT: a compile-time constant must defined outside of any function

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

There are 8 primitive types in Java (minus `String`) that map to those types

Knowing how to inspect the Java equivalent of Kotlin code is a great technique for understanding how Kotlin works
Tools > Kotlin > Show Kotlin Bytecode

You can translate the bytecode back to Java to make the reading easier

The bytecode demonstrates that:
- Type inference is working
- Numbers (_e.g._, `Int`s) are optimized to best matching Java type

In Java, there are 2 kinds of types:
- Primitive types -> lowerCamelCased
- Reference types (defined by classes, since a class define a type) -> UpperCamelCased

All primitives in Java have a corresponding reference type (a.k.a. boxed type)
-> Mainly b/c of `<T>` w/ generics (primitive types do not define a type in the sense of `T`)
-> Also b/c of useful OO features (_e.g._, methods)

Primitive types always have better performance though

Unlike Java, Kotlin only offer boxed types, which can be later optimized into primitive types (like Scala)
Kotlin gives you the ease of reference types w/ the performance of primitives under the hood

Neither Java nor Kotlin require importing `java.lang.String`
-> `String` is present in package `java.lang` which is imported by default in all Java programs
   
<https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html>


## Chapter 3 - Conditionals

JetBrains headquarters are located in Prague, Czech Republic
JetBrains debuted as _IntelliJ Software_
Their first product was _IntelliJ Renamer_, a code refactoring tool
_IntelliJ IDEA_ is their first IDE

**control flow:** rules for when code should be executed

**branch:** a set of code executed conditionally (_e.g._, the `else` branch)
-> Also referred to as a _clause_ (_e.g._, the `else` clause)

The expression between the `()` of an `if` statement is called the _condition_

A _conditional_ (a.k.a. conditional expression) refers to a statement w/ a condition (_e.g._, `if/else`, `when`)

A branch is _triggered_ when its condition is evaluated to `true`

> The flow of the code execution will branch depending on whether the specified condition is met

INTELLIJ TIP: `healthPoints == 100.if` expands into the unexpected `if (healthPoints == 100)`

`==` is called the _structural equality operator_
-> It checks whether value on its LHS is equal to value on its RHS

It is one of the _comparison operators_:
- `<`
- `<=`
- `>`
- `>=`
- `==`
- `!=`
- `===`  (checks whether 2 instances point to the same reference, as opposed to values until now)
- `!==`

FUNDAMENTAL:
- Like Scala, `str1 == str2` in Kotlin is equivalent to `str1.equals(str2)` in Java
- `===` in Kotlin is the equivalent of using `==` in Java

**String concatenation:** combining two or more strings in a single one
-> In Kotlin, `String` concatenation is done via the _addition operator_ (_i.e._, `+`)

Like C & Java, the `{}` are optional for `if/else` statements if it _encloses_ a single instruction
-> Omitting them seems to make the code clearer

```kotlin
var arrowsInQuiver = 2
if (arrowsInQuiver >= 5)
    println("Plenty of arrows")
    println("Cannot hold any more arrows") // Will print unconditionally
```

The Kotlin community prefers to put `{}` (_braces_) every time (at least for multiline `if`s) b/c of inexperienced developers
-> But do we care about new readers? (That are this much inexperienced)

BRILLIANT: It's not about saving keystrokes it's about saving space in your head (but the trap is that too much conciseness requires more space in your head to remember what it exactly expands too)

REMEMBER: you spend more time reading than writing code

IMPORTANT: `else if` is just an `else` with another `if/else` as a block (like in C)

`if`s can also be nested (but envisage to use `&&` or `||`)
-> An `if/else` is considered to be a single instruction and thus can be enclosed w/o `{}` in another `if/else`

Obviously, the order of the `if`s matters

Any boolean expression can be negated by prefixing it w/ `!`

To avoid having to duplicate nested conditions, use the _logical operators in conditions:_
- `&&` (_Logical "and" operator)
- `||` (_Logical "or" operator)
- `!` (_Logical "not" operator)
-> The binary ones _combine_ 2 boolean expressions (as opposed to the unary operator `!`)

_Operator precedence_, from highest to lowest:
1. `()`
2. `!`
3. `*`, `/`, `%`
4. `+`, `-`
5. `<`, `<=`, `>`, `>=`
6. `==`, `!=`
7. `&&`
8. `||` FUNDAMENTAL: _and_ has a higher precedence than _or_ (like in any other language)
-> Operator w/ the same precedence are applied from **left to right**

**Refactor:** change the presentation or location of code w/o changing its functionality

INTELLIJ TIP: extracting an expression to a new variable:
1. Place your cursor on the expression
2. `<C-A-V>`
3. Select the desired expression from the list of suggestions

The reverse operation: replacing a variable by its value is called _inlining a variable_ (similar to applying referential transparency)

INTELLIJ TIP: inline a variable
1. Put your cursor on any of the variable occurrence
2. `<C-A-N>`: Refactor > Inline...

Consider extracting `if`'s condition as variable to label them (especially when long)
-> Or create a dedicated function w/ the required parameters

Like Scala, `if`s are expressions in Kotlin (called _conditional expressions_), and therefore can be assigned to variables
-> Make sure the `if` yields the same type for all of its branches, o/w the result type will be `Any`
-> When not assigned, the yielded value is discarded (the conditional is then referred to as an _`if` statement_)

IMPORTANT: Kotlin has no ternary operator since it offers conditional expressions

IntelliJ does not yet offer variable introduction (w/ `<A-RET>`) for conditional expressions

In the code, all the conditions _branch off_ the value of the integer `healthPoints`
-> You can also use _ranges_ (_e.g._, `75..90 - 1`; both _bounds_ are inclusive) combined w/ the `in` keyword
-> The `in` keyword enables you to check whether a value is within a range
-> Both _lower bound_ and _upper bound_ must be defined (_i.e._, no `100..`)

(Inequalities were more readable in my opinion)

**Range:** a sequential/linear series of values or characters

`in` is only valid for ranges
 -> Use `.contains()` w/ lists

`100 in 100..101` -> `true`
`101 in 100..101` -> `true`
`101 in 100 until 101` -> `false`
`100 in 100..100` -> `true`
`75 in 100..50` -> `false` (no range generated)
`75 in 100 downTo 50` -> `true` (descends instead of ascending)
`'A' in 'A'..'F'` -> `true`
`'a' in 'A'..'F'` -> `false`
`'x' !in 'a'..'z'` -> `false`
`for (i in 1..5) print(i)` -> Prints `12345`
`for (i in 1..5 + 1) print(i)` -> IMPORTANT: Prints `123456`
`for (i in 8 downTo 1 step 2) print(i)` -> Prints `8642`
`(1..3).toList()` -> `[1, 2, 3]`
`(3 downTo 1).toList()` -> `[3, 2, 1]`
`(3..1).toList()` -> `[]`

Although `[]` are used by the Kotlin REPL to represent lists in results, there are currently no collection literals for code outside of annotations (use )

> Kotlin does not have dedicated syntax constructs for creating lists or sets. Use methods from the standard library, such as `listOf()`, `mutableListOf()`, `setOf()`, `mutableSetOf()`, `mapOf()`, `mutableMapOf()`.

Ranges are not lists b/c it enables them to be lazily/optimally evaluated

By defining both bounds of the expected range (doable w/ inequalities as well), the ordering of `if`s no longer matters

You can call the `rangeTo` method for your classes, notably w/ the _operator form_ `..` (_e.g._, `Version(1, 11)..Version(1, 30)`)

The `when` expression is another _control flow_ mechanism (_i.e._, conditional) available in Kotlin

```kotlin
val race = "gnome"
val faction = when (race) { // `race` is called an _argument_
    "dwarf" -> "Keeper of the Mines"
    "gnome" -> "Keeper of the Mines" // You should variablize these results
    "orc" -> "Free People of the Rolling Hills"
    "humans" -> "Free People of the Rolling Hills"
    else -> "Unknown" // Optional, like any other `else` (however required in FP)
}
```

When having multiple conditions/clauses that yield the same result, you can use a `,` to enumerate them:

```kotlin
val race = "gnome"
val faction = when (race) { // Better
    "dwarf", "gnome" -> "Keeper of the Mines"
    "orc", "humans" -> "Free People of the Rolling Hills"
    else -> "Unknown" // Optional, like any other `else` (however required in FP)
}
```

`when` is a good fit when wanting to fully cover the values of a variable in order to act accordingly
-> Enables you to put a variable/expression as a common denominator

IMPORTANT: like in Scala, when doing pattern matching on classes, use _sealed classes_ in order to implement _exhaustive pattern matching_ (and therefore omit the `else` clause):
```kotlin
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // The `else` clause is not required because we've covered all the cases
}
```

<https://kotlinlang.org/docs/reference/sealed-classes.html>

Unlike Scala, Kotlin is using `->` (and not `=>`) for:
- `when`
- Lambdas
- Function type definitions

By default, the conditions of `when` are built using `==` (_i.e._, the structural equality operator)
-> O/w, you used `in` instead for example

RULE OF THUMB: you should use `when` whenever you need an `else if` (_i.e._, an `if` w/ > 2 branches)

INTELLIJ TIP: `<A-RET>` > Replace `if` with `when`

**Scope:** the portion of a program in which an entity (_e.g._, a variable) can be referred to by name
-> _Resolving symbols_

IMPORTANT: `when` is different in that it scopes the LHS of the condition automatically to whatever is provided as an argument
-> Is that really called `scoping`? (associating?)

**String template:** syntax that allows a variable to _stand in_ for its value in a string

**String interpolation:** using a string template

_String interpolation/templating_ enables you to inject values into a single string
- Variables are interpolated using `$`
- Expressions (in general requiring evaluation, but variables are still valid) are interpolated using `${}`

`.trimMargin()` still works w/ string templates (which requires evaluation)
-> Is it executed at compile time o/w?


# Chapter 4 - Functions

**Function:** a reusable portion of code that accomplishes a specific task
-> A way to group & reuse expressions in a program

> Programs are a series of functions combined to accomplish more complex tasks

-> They both have an input & and output
A function should solely compute its output from its inputs (or sole input when considering currying)

`println()` is a function provided by the `stdlib`

A function _definition_ has:
- 0 to many parameters
- A result type
- A name (as opposed to _anonymous function_ or _lambda expression_)
- A body, which can be either a single expression or statements enclosed in `{}`

In Kotlin, a function call in named an _invocation_ (b/c you can override the `invoke()` function that is named so)
-> It is the same as `.apply()` in Scala
-> A function invocation _evaluates_ to an expression

IMPORTANT: _lambda expressions_ (distinct from _anonymous functions_ in Kotlin) are _function literals_, _i.e._, functions that are not declared, but passed immediately as an expression
-> Useful for ad-hoc/single-use functions (and they are omnipresent in FP)
```kotlin
{ a: Int, b: Int -> a + b } // Type: `(Int, Int) -> Int`
// IMPORTANT: the type of parameters cannot be inferred
res0(1, 2) // -> `3`, note that nothing prevents you from using the `res` variables directly
```
As seen in the REPL, `res` is a common abbreviation for "result"

Since the Kotlin REPL does not implement `:type` yet, you should the `.javaClass` property on expressions
-> Shows the Kotlin type on the LHS and the  underlying Java type on the RHS

NOTE: Lambda are really _lambda expressions_ (_i.e._, a function literal)

BRILLIANT: on the contrary to Scala, Kotlin interprets _blocks_ (as they are called in Scala) as lambda expressions:
Scala 3:
`{ 1; 2; }` -> Creates 2 results `val res0: Int = 1` and `val res1: Int = 2`
`val a = { 1; 2; }` -> Produces a warning & `val a: Int = 2`
`a` -> `2`

ESSENTIAL: In Scala, when you pass a block where a function **with a single parameter** is expected (single b/c this parameter can be then omitted), it automatically becomes lambda by impliciting this parameter w/ `_` (and you cannot pass a function that has (1) no `_` (2) no `(x) =>` prefix)
`{ (x: Int) => x + 2 }` -> Type `Int => Int`, LEARN: "parentheses are required around the parameter of a lambda"
`{ 1 + 2 }` -> `3`
`List(1, 2, 3).map { 1 + 2 }` -> IMPORTANT: does not work because `{ 1 + 2 }` is not a lambda
`List(1, 2, 3).map { (x) => 1 + 2 }` -> `List(3, 3, 3)`, note that `x` is unused
`List(1, 2, 3).map { (_) => 1 + 2 }` -> `List(3, 3, 3)`, ignoring `x` (also available in Kotlin)
`List(1, 2, 3).map { _ + 2 }` -> `List(3, 4, 5)`
ESSENTIAL: the `_` in Scala does not only substitute the single parameter of the current function (here `x`), but it actually transforms the passed block into a lambda (by impliciting `(x) =>`)

These remarks correlates to the choice of the _by-name parameter_ syntax (_e.g._, `a: => Int` that is really `a: () => Int`), where the `() =>` can be omitted as well
-> `=>` denotes the requirement of evaluation (one more tranformation/mutation and you get the value)

In Kotlin, there is not this question b/c _blocks_ are by default lambda expressions (and thus always considered to have the `(x) =>` prefix or `_`)
FUNDAMENTAL: Moreover, _blocks_ in Kotlin are actually the syntax for Lambda expressions
`{ 1; 2; }` -> A lambda with the type `() -> kotlin.Int`
`res0.invoke()` -> `2`
`res0()` -> `2`

Consequently, blocks in Kotlin are always one step away from yielding a value
-> This is therefore the way to define by-name parameters, _e.g._:
```kotlin
//fun whilst(a: -> Int): Int = a() + 1 // Does not compile
fun whilst(a: () -> Int): Int = a() + 1 // (() -> Int) -> Int, first `()` are required
whilst({ 1 + 2 + 3 }) // -> `7`
// OR
whilst { 1 + 2 + 3 } // -> `7`
```
It does not seem possible to emulate the conditional of a `while` w/ `()`

In Scala, things get more complicated b/c you can call `a` without `()` (at least not when the function takes no argument)

**Parameters:** part of the definition
**Arguments:** expressions assigned to parameters, part of the function invocation

Kotlin has no native currying :(
-> _Combine_ (in the sense of category theory) functions instead

Scala arguments can only be separated by a ` ` instead of `,` when using PAFs
-> Hence the use in the `whilst`

Unused lambda parameters can be replaced by `_`
`{ x: Int -> x + 2 }` -> Type `(Int) -> Int`, LEARN: In Kotlin, there are no `()` for lambda parameters (unlike Scala); There are `()` in the type definition though
`{ 1 + 2 }` -> `3` -> Type `() -> Int`
`listOf(1, 2, 3).map { 1 + 2 }` -> `[3, 3, 3]`, works in Kotlin (note that a list literal delimited with `[]` is used in the output of the REPL)
`listOf(1, 2, 3).map { (x) -> 1 + 2 }` -> Does not compile, we said no `()`
`listOf(1, 2, 3).map { x -> 1 + 2 }` -> `[3, 3, 3]`, note that `x` is unused
`listOf(1, 2, 3).map { _ -> 1 + 2 }` -> `[3, 3, 3]`, right syntax to ignore the lambda parameter (IntelliJ should prompt for simplification)
`listOf(1, 2, 3).map { it + 2 }` -> `[3, 4, 5]`, the `it` keyword is equivalent to `_` from Scala

`it` (or `_`) in Scala only work if there is an argument provider upfront (_e.g._, an element from a list for `map`)

When you are required to pass a function that has the result type `Unit` (or `void`), it means that this function will be executed only for its side effects
-> Do not mistake this for by-name parameters (parameter type VS result type)

Like Scala, you declare single expression function bodies using `=` instead of `{}` (_i.e._, you assign a body to a function)
-> For static definitions, it has never been the `=>` or `->` like lambdas
-> IMPORTANT: like Scala, the `=` is optional when you have a code block/function body just after

In Kotlin, you also have anonymous functions (distinct from _lambda expressions_):
```kotlin
val a = fun(s: String): Int { return s.toIntOrNull() ?: 0 }
// OR
val a = fun(s: String): Int = s.toIntOrNull() ?: 0
```
`a("Alex")` -> `0`
`a.invoke("Alex")` -> `0`
`a(5)` -> `5`
-> Supposed made for Java people inexperienced w/ lambda expressions

You should always write the result type explicitly for the sake of readability

You should organize your programs into functions

INTELLIJ TIP: IntelliJ will help you group your existing logic into functions easily:
1. Select the lines that you desire to embody
2. `<C-A-M>`: Refactor > Extract > Function
-> The generated function is appended at the end of the file

Functions are set to `private` by default when extracted
