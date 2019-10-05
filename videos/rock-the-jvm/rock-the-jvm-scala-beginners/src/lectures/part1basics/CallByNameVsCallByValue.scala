package lectures.part1basics

object CallByNameVsCallByValue extends App {
  // A Scala specific concept: calling functions by name vs by value
  // This is different evaluating parameters in functions

  // ELEMENTAL: the word `evaluate` means to transform into a value

  def calledByValue(x: Long): Unit = {
    println("By value: " + x) // String concatenation is intentionally avoided for clarity
    println("By value: " + x)
  }

  def calledByName(x: => Long): Unit = { // `=> A` tell the compiler that the parameter will be called by name
    println("By name: " + x)
    println("By name: " + x)
  }

  // `System.nanoTime()` gives the current timestamp in nanoseconds (result type is `Long`, hence the parameter's types)
  // -> Generally, time functions on the JVM have the result type `Long`
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  // Results:
  // By value: 21914768430570
  // By value: 21914768430570
  // By name: 21914917923333
  // By name: 21914917974851

  // FUNDAMENTAL:
  // In the by value call, the args are evaluated (i.e., transformed into a value) when being assigned to parameters
  // -> (Immutable) copies of the arguments are made
  // In the by name call, the arguments are not evaluated when being assigned to parameters
  // -> The arguments (which are expressions) are passed as-is
  // -> Thus their evaluation are deferred to the evaluation of the parameters occurs in the function body
  // FUNDAMENTAL: a given argument can be evaluated more than once if the assigned parameter is evaluated more than once

  // Evaluations;
  // def calledByValue(1914768430570L): Unit = {
  //   println("By value: " + 1914768430570L)
  //   println("By value: " + 1914768430570L)
  // }

  // def calledByName(System.nanoTime()): Unit = {
  //   println("By name: " + System.nanoTime())
  //   println("By name: " + System.nanoTime())
  // }

  // Thus the `=>` symbol makes total sense b/c you are passing a set of computations (i.e., a function) to the function

  // Call by name is extremely useful in lazy streams and things that might fails (w/ the `try` type)

  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int) = println(x)

  // Since the call by name delays the evaluation of the passed expression until it is used, `infinite()` can be passed
  // (Because its assigned parameter `y` is never evaluated)
  printFirst(34, infinite())
}
