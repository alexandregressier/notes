package lectures.part1basics

object StringOps extends App {
  val str = "Hello, I am learning Scala"

  // Since FP is omnipresent in Scala, Strings are obviously immutable

  // Standard String operations (methods)
  // Scala is using `java.lang.String` behind the scenes, so you are really using the methods from Java
  println(str.charAt(2)) // 'l' - strings uses 0-based index (pronounced "car at")
  println(str.substring(7, 11)) // "I am" - `beginIndex` is inclusive, `endIndex` is exclusive
  println(str.split(' ').toList) // Results type is `Array[String]`, can take 1 to n `Char`s or `String`s

  // IMPORTANT: Just like functions, parameterless methods can (and sometimes must) be called w/o `()` (e.g., .toList)

  println(str.startsWith("Hello")) // true
  println(str.replace(' ', '-'))
  println(str.toLowerCase)
  println(str.toUpperCase)
  println(str.length) // LEARN: `length`, not `size`

  // Scala also has its own String utilities
  val aNumberString = "45"
  val aNumber = aNumberString.toInt // `String` to `Int`

  println('a' +: aNumberString :+ 'z') // "a45z"
  // IMPORTANT: `+:` and `:+` are just a method name, not a new operator:
  println(aNumberString.+:('a').:+('z'))

  // (Still Scala-specific niceties)
  println(str.reverse)
  println(str.take(2)) // 'He' - `str.take 2` does not work

  // Scala String interpolators
  val name = "Alex"
  val age = 22

  // S-interpolator
  val greeting = s"Hello, my name is $name and I am $age years old." // /s-interpolated String/
  // W/o the `s`, the String is taken /literally/
  // IMPORTANT: `s` is also really a method! (Check the sources)

  // S-interpolated Strings can also contain expressions to evaluate before being expanded
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old."
  println(anotherGreeting)

  // INTELLIJ TIP:
  // <M-RET> can be used on a String literal w/ multiple constituents to switch between concatenation and interpolation

  // F-interpolator
  // Same as s-interpolator, but can also contain /format specifiers/ after the values to be expanded
  val speed = 3.2f
  println(f"$name can eat $speed%5.2f burgers per minute") // Prints " 3.20" (note the leading space), 5 characters long
  println(f"$name can eat $speed%09f burgers per minute") // Prints 03.200000, 9 characters long
  // %.2f truncates a decimal number to 2 decimal places
  // %5f pads the number with ' ' to reach the minimum length of 5 characters
  // %05 same but pads the number with '0'
  // See printf(3) for more information about format specifiers

  // %d are for decimal numbers
  // %s are for strings
  // Note that format specifiers are optional using the f-interpolator

  // IMPORTANT: f-interpolated Strings have the property to check for type correctness for the value that they expand
  val x = 1.1f
  //val xString = f"$x%3d" // ERROR: type mismatch

  // Raw interpolator
  // Same as s-interpolator, but can also print characters literally
  println(raw"This is a \n new line") // Prints a single line

  val escaped = "This is a \n new line" // Blanks around '\n' are still printed
  println(raw"$escaped") // Prints two lines - the '\n' is escaped
  // -> Use the raw interpolator on the expanded String if you want '\n' to be expanded
  // (The raw interpolator within `println` is useless)
}
