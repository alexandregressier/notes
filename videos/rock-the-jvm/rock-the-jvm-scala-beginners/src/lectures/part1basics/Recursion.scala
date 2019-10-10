package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  // To trace function calls, the JVM employs a /call stack/
  // -> To keep partial results so that it can get back to compute the final result

  // Each call of the recursive function uses a /stack frame/
  def factorial(n: Int): Int =
    if (n == 0) {
      println("Computing factorial(0) = 1 - Stop!")
      1 // IMPORTANT: when writing such statements in a code block, do not forget to "return" a value
    }
    else {
      println(s"Computing factorial($n) = $n * factorial(${n - 1})")
      val result = n * factorial(n - 1)
      println(s"Computed! factorial($n) = $result")
      result
    }
  println(factorial(10))

  // DEFINITION: a /stack frame/ is a frame of data that gets pushed onto the stack
  // -> In the case of a /call stack/, a stack frame would represent a function call and its argument data

  // In the case of recursion, stack frames are pushed onto the call stack and are then popped to compute the result

  // INTELLIJ TIP: expr.prtln -> println(expr)

  // The call stack of JVM has limited memory:
  //println(factorial(5000)) // ERROR: java.lang.StackOverflowError
  // Manages to go as deep as n = 403 on my machine

  // IMPORTANT: `StackOverflowError`s happens when the call stack size exceeds (almost always in the case of recursion)
  // -> "Recursive depth is too big"

  // OBSERVATION: The "point of contention" for `factorial` is not the max size of the call stack
  // -> But the maximum number of the `Int` type

  // What if you want to iterate more than the call stack's size permits?
  def factorialAlt(n: Int): BigInt = {
    def factorialHelper(n: Int, accumulator: BigInt): BigInt =
    // IMPORTANT: you can access the parent's arguments in the auxiliary's body
    // -> If you do not plan to use them you can mask them by masking the scope using same names
      if (n == 0) accumulator
      else factorialHelper(n - 1, n * accumulator)

    factorialHelper(n, 1)
  }
  // Recursive calls for n = 5:
  // factorialAlt(5) = factorialHelper(5, 1)
  // = factorialHelper(4, 5 * 1)
  // = factorialHelper(3, 4 * 5 * 1)
  // = factorialHelper(2, 3 * 4 * 5 * 1)
  // = factorialHelper(1, 2 * 3 * 4 * 5 * 1)
  // = factorialHelper(0, 2 * 3 * 4 * 5 * 1)

  // Max computable factorial using `Long`s: n = 25
  // IMPORTANT: `Int` and `Long` are for amateurs: `BigInt` enables you to compute large factorials!
  // -> `BigInt` (and `BigDecimal`) enables you to have numbers as large as you want b/c it uses `int[]` under the hood
  // (Scala uses the Java equivalents under the hood)
  // -> Thus there are no such thing as `BigInt.MaxValue`
  println(factorialAlt(5000))

  // Clean up using a default parameter value instead of an auxiliary function
  def goldenFactorial(n: Int, acc: BigInt = 1): BigInt =
    if (n == 0) acc
    else goldenFactorial(n - 1, n * acc)

  println(goldenFactorial(20000))

  // ELEMENTAL: /tail recursion/
  // -> The result value of any given recursive step is the same as the result value of the next recursive call

  // On the contrary to classic recursion, there are no partial results to be memorized (here the RHS of `n *`)
  // -> Thus the current stack frame can be dropped to be replaced by the next one
  // (Uses registers instead of call stack)
  // FUNDAMENTAL: with /tail recursion/, the recursion is optimized to a loop

  @tailrec // Tell the compiler to ensure that this function is tail recursive to benefit from optimizations
  def sum(n: BigInt, acc: BigInt = 0): BigInt =
    if (n == 0) acc
    else sum(n - 1, n + acc) // Notice that the recursive call occurs as the last expression on the /code path/
  // -> Hence the name tail recursion
  println(sum(32767))

  // /Code path/: the path/flow the code branching follows (think of it as moving in a flowchart)

  // INTELLIJ TIP: Consider referring to the recursion-related icons in the gutter to improve your recursions

  // Note that the previous `isPrimeUntil` function was tail recursive as well

  // In a nutshell: when you need to iterate, use *tail* recursion

  // FUNDAMENTAL: any recursive function can be turned into a tail recursive function

  // Algorithm: just move the computations around the recursive call to the accumulator
  // -> Treat the accumulator as the old recursive call (but the other arguments do not move)
  // -> You will need as many accumulators as you have recursive calls on your code path
  // FUNDAMENTAL: each accumulator should  have a computation applied to it to be

  // Accumulator designate the same purpose as accumulator collections (e.g., accumulator lists)

  // Exercises
  // def concatNTimes(str: String, n: Int): String =
  //   if (n == 0) ""
  //   else str + concatNTimes(str, n - 1)
  @tailrec
  def concatNTimes(str: String, n: Int, acc: String = ""): String =
    if (n == 0) acc
    else concatNTimes(str, n - 1, str + acc)

  println(concatNTimes("yo", 5))

  // def isPrime(n: Int): Boolean = {
  //   def isPrimeUntil(t: Int): Boolean =
  //     if (t == 1) true
  //     else n % t != 0 && isPrimeUntil(t - 1)
  //
  //   isPrimeUntil(n / 2)
  // }
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeRec(i: Int, isStillPrime: Boolean = true): Boolean =
      if (!isStillPrime) false // Optimization, but maybe useless because of the ones that already occur with `&&`
      else if (i == 1) isStillPrime // MISTAKE: you do not yield the usual default/base values but your accumulators!!
      else isPrimeRec(i - 1, n % i != 0 && isStillPrime)

    isPrimeRec(n / 2) // IMPORTANT: In Scala and other typed languages like C, 5 / 2 = 2
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(8096))

  // def fibonacci(n: Int): Int =
  //   if (n == 0) 0
  //   else if (n == 1) 1
  //   else fibonacci(n - 1) + fibonacci(n - 2)
  @tailrec
  def fibonacci(n: Int, nextToLast: Int = 0, last: Int = 1): Int =
    if (n == 0) nextToLast
    else if (n == 1) last
    else fibonacci(n - 1, last, nextToLast + last) // FUNDAMENTAL: you should have to shift both term to the next term

  println(fibonacci(10))

  // IMPORTANT: the recursion (w/ or w/o acc) can (also?) be read as going from stop conditions to solution
  // -> The default values previously yielded for each stopping condition are applied to the accumulators
}
