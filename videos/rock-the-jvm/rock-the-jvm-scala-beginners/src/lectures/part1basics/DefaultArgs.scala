package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def factorialWithAcc(n: Int, acc: Int): BigInt =
    if (n == 0) acc
    else factorialWithAcc(n - 1, n * acc)

  // In almost all cases, the only argument you want to assign to `acc` is `1` (since factorial(0) = 1)
  // -> The `acc` parameter spoils the function signature

  // Solutions:
  // - Define `factorial` with an auxiliary function
  // - Specify a /default value/ for the parameter in the function's signature:
  @tailrec
  def factorial(n: Int, acc: Int = 1): BigInt =
    if (n == 0) acc
    else factorial(n - 1, n * acc)

  // (Note that this concept was already used in earlier notes)
  // JS and Python also have /default parameter values/

  // Calls
  factorial(4)
  factorial(4, 2) // You can still provide an argument for the parameter with a default value

  // Default parameter values are useful when you call a function multiple times w/ the same arguments

  // FUNDAMENTAL: In Scala, parameters with default values are not necessarily positioned on the rightmost side
  // (Avoid this practice though)
  // -> Because it supports /named arguments/
  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture...")

  // Ugh...
  def savePictureWeird(format: String = "jpg", width: Int, height: Int = 1080): Unit = println("Saving picture...")

  savePicture()
  savePicture("png")
  //savePicture(800, 600) // ERROR: the types of arguments mismatch the types of the parameters

  // Solutions:
  // - Pass in every leading argument w/ the default values (but violates DRY)
  // - /Name the arguments/
  savePicture(width = 3840)

  // Make sure that a function call is still easy to reason about when you mix positional arguments with named arguments

  // As a consequence, named parameters can be passed in any order:
  savePicture(height = 48, format = "ico", width = 48)

  // INTELLIJ TIP: by pressing <M-RET> and having the cursor on an argument,
  // IntelliJ can convert it to a /named argument/
  // INTELLIJ TIP: you can omit the name for arguments if you already use the labels provided by IntelliJ
}
