package lectures.part1basics

object Expressions extends App {
  val aValue = 1 + 2 // `1 + 2` is called an /expression/
  // FUNDAMENTAL: Expressions are *evaluated to values* (here 3) and have a *type*
  // -> Enables type inference

  // /Mathematical operators/ are parsed according to operator precedence (i.e., order of operations)
  println(2 + 3 * 4)
  // + - * / & | ^ << >>
  // Unique operator in Scala: `>>>` - Right shift w/ zero extension (rarely used)

  // /Relational operators/: test equality and inequality
  println(1 == 2)
  // == != < <= > >=

  // /Boolean operators/: used for logic
  println(!(1 == aValue))
  // ! (unary operators) && || (binary operators)

  var aVariable = 2
  aVariable += 3
  // += -= *= /=
  // IMPORTANT: there are no ++ and -- in Scala (who cares anyway? They imply side effects)
  println(aVariable)
  // These operators imply side effects

  // Instructions (or statements) VS Expressions
  // Instruction: explain the computer what to do (imperative programming)
  // Expression: explain to the computer how to do something (imperative (\implies functional) programming)
  // (also "give me the value of something")
  // -> Every single part of the program will compute a value

  // `if` *expressions* (not statement)
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // IMPORTANT: `()` are required
  println(aConditionedValue)
  // IMPORTANT: In Scala, `if` are evaluated to values and therefore are expressions
  println(if (aCondition) 5 else 3)

  // Loops exist in Scala but their use is discouraged b/c of the mutations they imply
  // -> Too much reminiscent of the imperative style
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  // In a nutshell: looping in Scala is banned (very specific to imperative programming)
  // -> Writing imperative Scala is the worst thing one can do

  // FUNDAMENTAL: everything in Scala is an expression (except vals, vars, and package definitions)
  // -> Even reassigning a variable is an expression, but the result is not meaningful:
  val aWeirdValue = (aVariable = 3) // /Result type/ is `Unit` (equivalent to `void` in other languages)
  // The `Unit` type is a /singleton/: it has a single possible value: `()`
  println(aWeirdValue) // Prints `()`

  // FUNDAMENTAL: side effects in Scala look like instructions but they are still expressions w/ the `Unit` result type
  // Example: `while` loops has also a result type of `Unit`
  var j = 0
  val aWhile = while (j < 10) { // Inferred type: Unit (IMPORTANT: you should write the type annotation in that case)
    println(j)
    j += 1
  }
  println(println("Yo")) // Also last invocation prints `()`
  // Example of side effects: println(), while, reassignments

  // FUNDAMENTAL: a function w/ a that has the result type `Unit` is only called for its side effects

  // Code blocks - A set of computations delimited by `{}`
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "Hello" else "Good-bye"
  }
  println(aCodeBlock)
  // Code blocks are expressions: a special kind, w/ special properties
  // Like any expressions, code blocks can be assigned to a variable
  // FUNDAMENTAL: the value of code block is the value of its last expression
  // REMEMBER: your IDE can infer the type of any expression (including code blocks)

  // Everything you declare inside the code block stays visible within the code block
  //val anotherValue = z + 1 // ERROR: `z` is undefined
}
