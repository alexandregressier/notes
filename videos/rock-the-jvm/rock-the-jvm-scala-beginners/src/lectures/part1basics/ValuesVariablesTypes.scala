package lectures.part1basics

object ValuesVariablesTypes extends App {
  // vals - Immutable variables
  val foo: Int = 42 // /Type inference/ enables the /type annotation/ to be omitted
  println(foo)
  // FUNDAMENTAL: the word /variable/ does not necessarily imply /mutability/
  // -> But rather the ability to be assigned multiple values

  //x = 2 // ERROR!
  // IMPORTANT: vals should not be interpreted as constants (in the sense of the `#define` directive in C)
  // -> But more like intermediate computation results intended for later use

  // FUNDAMENTAL: immutability is essential to FP

  // In practice, type annotations are only used when clarity is needed
  // Obviously, the type annotation must match the type of the assigned expression if one is present

  val aString: String = "hello"; // "" delimit `String` literals
  // Ending `;` are optional, unlike most programming languages
  // Use case (discouraged): writing multiple statements on a single line

  val aBoolean: Boolean = true // LEARN: the name used in Scala for the boolean data type is `Boolean` (and not `Bool`)
  val aFalseBoolean: Boolean = false

  // The syntax for literals in Scala is the same in Java:
  val aChar: Char = 'a' // '' delimit `Char` literals
  val anInt: Int = foo // (4 bytes long)
  val aShort: Short = 32767 // A `Short` is the half of an `Int` (2 bytes long)
  val aLong: Long = 9223372036854775807L // A `Long` is the double of an `Int` (8 bytes long)
  // LEARN:: `Long` literals end with the `L` suffix

  val aFloat: Float = 3.4028235E38f
  // LEARN: `Float` literals end with the `f` suffix
  val aDouble: Double = 1.7976931348623157E308

  // By convention, type names are always Capitalized

  // vars - Mutable variables
  var bar: Int = 4
  bar = 5
  // `var`s are /mutable/ and are used in FP for side effects

  // FUNDAMENTAL: side effects are useful b/c they allow us to see what the program is doing (i.e., its state)
  // Examples: mutating a variable, printing something to the console, displaying something on screen
  // Programs w/o side effects are easier to understand and reason about (i.e., elegant)
  // -> B/c no state to keep track of
  // Side effects cannot be completely eliminated b/c the program must do something useful to the world
  // -> But be mindful about theme

  // TAKEAWAY: prefer vals over vars
}
