package lectures.part2oop

object Exceptions extends App {

  // 1. Throwing exceptions

  // /Exceptions/ are /thrown/ when your program behaves unexpectedly
  // -> Exceptions are concept inherent to the JVM

  // The exceptions that are /thrown/ in Scala come from the Java language

  val x: String = null
  //println(x.length) // ERROR: `NullPointerException` (abbreviated NPE)
  // Since there are no block to /catch/ the exception, it stops the JVM

  // Exceptions are thrown using the keyword `throw`:
  //throw new NullPointerException
  // FUNDAMENTAL: like (almost) everything else, throwing an exception is an expresion
  //val aWeirdValue: Nothing = throw new NullPointerException // Still interrupt the process
  // -> This expression's result type is `Nothing`, which explains why the program stops

  // REMEMBER: `Nothing` is valid substitute for any other type:
  //val foo: String = throw new NullPointerException

  // FUNDAMENTAL: exceptions are instances of (special) classes
  // -> Hence the `new` keyword

  // `NullPointerException` extends `RuntimeException`, which extends `Exception`, which extends `Throwable`
  // `Exception`s and `Error`s are the main `Throwable` subtypes
  // -> Both will interrupt the execution of JVM but their semantics are different:
  //    - Exceptions: denote something that went wrong with the program (e.g., `NullPointerException`)
  //    - Errors: denote something that went wrong with the system (e.g., `StackOverflowError`)


  // 2. Catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new NullPointerException("No int for you")
    else 42

  // Use a /try-catch(-finally) expression/ to guard against an exception thrown while evaluating this method:
  val potentialFailure: AnyVal = try {
    // Evaluations that might fail
    getInt(true)
  } catch { // Scala employs pattern matching for dealing with the exception type
    // Try to match the kind of exception that might be thrown in the `catch` block
    // FUNDAMENTAL: the cases are checked in order and each can match either the exception itself or its supertypes
    // -> (But not its subtypes)
    case e: RuntimeException => println("Caught a `RuntimeException`")
    // The expression on the RHS is evaluated when the case is matched
    case e: NullPointerException => println("Caught a `NullPointerException`") // Unreached b/c of the first case
  } finally  { // The `finally` block is optional
    // Code that will get executed no matter what (e.g., `withExceptions = false`)
    // Useful to close a connection to an external service, logging, write to a file...
    // -> FUNDAMENTAL: `finally` should only be used for side effects
    println("This message is printed out no matter what")
  }

  // `try`, `catch`, and `finally` are keywords
  // Like `throw` expression and everything else in Scala, `try-catch-finally` blocks are expressions as well
  // -> Thus can be assigned to variables

  // A type unification of the following expressions occurs to get its result type (here `AnyVal`):
  //    - `getInt` which is `Int`
  //    - The RHS expressions of the cases, which are `Unit`
  //    - (`finally`'s result type is not taken into account)
  //    -> Final result type: `AnyVal`

  // Example w/ to get the `Int` result type:
  val anotherPotentialFailure: Int = try {
    getInt(true)
  } catch {
    case e: NullPointerException => -1
  } finally  {
    println("This other message is printed out no matter what")
  }

  println(anotherPotentialFailure) // Prints `-1` if `withExceptions == true` else `42`


  // 3. Defining your own exceptions
  // In practice, you rarely need to derive a class from `Error`
  // -> But you derive from `Exception`:
  class MyException(protected val number: Int = 42) extends Exception
  // This class benefits from all the properties of regular classes: parameters, members...
  // -> Although you most usually need a name or a `printStackTrace`-like utility method
  val exception = new Exception // Does not interrupt the execution if not thrown

  // It could be useful to make use of anonymous classes to create a one-shot ad-hoc exception
  try {
    throw new MyException(48) {
      println(s"Side effect printing the number held within: $number!")
    }
  } catch {
    case e: MyException => {
      println(s"Caught a `MyException`:")
      e.printStackTrace()
    }
  }
}
