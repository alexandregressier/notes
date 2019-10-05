package lectures.part1basics

object Functions extends App {

  // Declaring a function (i.e., method)
  def aFunction(a: String, b: Int): String = { // In Scala, this is called the function's /signature/
    // Each parameter has a name and a type
    // Each function has a result type that can be inferred (except for the recursive ones)
    // Each function has a body: an expression (often a code block) that is assigned to it
    // Since that value of a code block is the one of its last expression, it also the one of the function
    a + " " + b // `b` is automatically casted to `String`
  }

  // Calling a function
  println(aFunction("Hello", 3)) // IMPORTANT: function calls are also expressions

  // FUNDAMENTAL: in Scala, parameterless functions can be called without `()`
  // -> Pick your own style
  def aParameterLessFunction(): Int = 42
  println(aParameterLessFunction())
  println(aParameterLessFunction) // FUNDAMENTAL: IN SCALA, THIS IS NOT THE FUNCTION ITSELF!!!

  // /Recursion/ is the way to loop in functional programming
  // FUNDAMENTAL: recursive function definitions require the result type to be mentioned explicitly
  def concatNTimes(str: String, n: Int): String =
    if (n == 0) ""
    else str + concatNTimes(str, n - 1) // /String interpolation/ could have been used as well

  println(concatNTimes("yo", 5))
  // Good practice: always explicitly write the function's result type nonetheless

  // IMPORTANT: if you choose to write `if (n == 1) str`, then your function will never work for n = 0 (which should)
  // `n == 0` is called a /stop condition/

  // IntelliJ IDEA automatically identifies recursive function definitions

  // The result type of a function can be `Unit` for a function meant to be called only for its side effects
  def aFunctionWithSideEffects(str: String): Unit =
    println(str) // `println`'s result is (), which is of type `Unit`

  // FUNDAMENTAL: auxiliary definitions in code blocks can concern vals and vars, but also functions just as well
  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = a + b // IMPORTANT: ideal for /recursion launchers/

    aSmallFunction(n, n - 1) // The result type of `aBigFunction` is the result type of `aSmallFunction`
  }
  println(aBigFunction(4))
  // Note that even `aBigFunction` and all other functions are part of the `main` provided by `App`

  // Exceptions are related to OO concepts in Scala

  // Exercises
  def greetings(name: String, age: Int) = s"Hi, my name is $name and I am $age years old."
  // String interpolation are `String` literals prefixed with an `s` (supports $var and ${expr})
  println(greetings("David", 12))

  def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1)
  println(factorial(5))
  // Although mathematically incorrect, using the stop condition `n <= 0` prevents errors related to negative numbers

  def fibonacci(n: Int): Int =
    if (n == 0) 0 // IMPORTANT: do not forget the "or equal" operators for stop conditions
    else if (n == 1) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  println(fibonacci(10))

  def isPrime(n: Int): Boolean = { // n > 0
    // Apparently, you would annotate this function for tail recursion
    def isPrimeUntil(t: Int): Boolean = // Auxiliary function definition in action
      if (t == 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2) // This is a preoptimization that one can use since a prime number is not divisible by 2
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(8096))
}
