package lectures.part1basics

object TypeInference extends App {
  // To infer types, the compiler will write the omitted type annotation in order to have a full value (val) definition
  // The compiler is able to do many steps in order to determine the type of a value:
  val x = 2 // `2` is of type `Int`, so `x` is an `Int`
  val y = x + " items" // FUNDAMENTAL: `Int` + `String` = `String`, `y` is a `String`

  // The compiler is also able to infer the result type of a function by looking at its implementation:
  def successor(x: Int) = x + 1 // The result type of this function is `Int`

  // When explicitly writing type annotations, the compiler will still use type inference to do a type check:
  //val x: Int = "Hello, world!" // ERROR: type mismatch

  // The compiler is not always able to figure out the result type on its own:
  def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1)
  // The first branch yields an `Int`
  // The second branch yields a product of an `Int` by a function's result type to be determined
  // -> But this is the current function, which has not result type defined yet!
}
