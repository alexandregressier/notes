package lectures.part2oop

object MethodNotations extends App {

  // To avoid naming conflicts with the classes created earlier, we nest our classes here (better off using /packages/)
  class Person(val name: String, val favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    // LEARN: the `==` method does throw a `NullPointerException` on a basic test if a String is null
    def hangsOutWith(person: Person): String = s"$name is hanging out with ${person.name}"

    // IMPORTANT: Scala has no operator overloading but has an extremely permissive function/method naming scheme
    def +(person: Person): String = hangsOutWith(person)
    // This freedom would allow one to easily create a language in Scala

    def unary_! : String = s"$name!!!" // Result type could have been omitted
    // IMPORTANT: the ' ' between '!' and ':' is required o/w the compiler will think that ':' is part of the method name
    // -> But you could in theory create a method named `unary_!:` (which would still required that ' ')

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and my favorite movie is $favoriteMovie."
    // IMPORTANT: the method name `apply` and the `()` (i.e., the signature) matter
    // `apply` can be overloaded with as many signatures as you need

    // Exercises
    def +(nickname: String) = new Person(s"[$name ($nickname)]", favoriteMovie)

    def unary_+ = new Person(name, favoriteMovie, age + 1)

    def learns(topic: String) = s"$name learns $topic."

    def learnsScala: String = learns("Scala")

    def apply(n: Int) = s"""$name watched the movie "$favoriteMovie" $n times."""
  }
  val mary = new Person("Mary", "Inception")
  val tom = new Person("Tom", "Fight Club", 22)

  // Scala /syntactic sugars/ that makes it resemble natural language:

  // Infix notation
  println(mary.likes("Inception"))
  println(mary likes "Inception") // This is so natural language
  // Called /infix notation/ (or /operator notation/ b/c it resembles an operator)
  // FUNDAMENTAL: it only works w/ methods that have a single parameter

  // `hangOutWith` also takes a single parameter
  println(mary hangsOutWith tom)
  println(mary + tom)
  println(mary.+(tom)) // Desugared equivalent
  // Make sure to understand that the value passed to `println` is the String yielded by the method in the middle

  // FUNDAMENTAL: in Scala, basic operators are actually methods as well (just almost always used w/ the infix notation)
  println(1.+(4) + 5) // Prints 10
  // ESSENTIAL: all operators are methods in Scala
  // Example: Akka actors have the `!` (tell pattern) and `?` (ask pattern) operators

  // INTELLIJ TIP: when unsure about an operator (i.e., method using the infix notation), visit the source of the method
  // INTELLIJ TIP: use <C-SPC> (or <M-/> w/ the Emacs layout) after the `.` operator to access all the available methods
  // -> Also works on the ' ' when using infix notation


  // Prefix notation (another syntactic sugar)
  // Related to unary operators
  val x = -1 // The unary operator `-` is also a method
  val y = 1.unary_- // Desugared equivalent

  // IMPORTANT: the prefix notation works by prefixing a method name with `unary_`
  // -> This prefix only supports 4 operators: + - ~ !
  println(!mary)
  println(mary.unary_!)


  // Postfix (suffix) notation
  // FUNDAMENTAL: it only works w/ methods that are parameterless
  println(mary isAlive)
  println(mary.isAlive) // Desugared equivalent

  // In practice, the postfix notation is rarely used b/c the only difference is the ' ' character
  // -> Introduces potential readability ambiguities (not to the compiler though)


  // Apply
  println(mary())
  println(mary.apply()) // Desugared equivalent
  // Call the instance as if it was a function, since it has an `apply` definition
  // `unapply` is also a special method that can be defined

  // FUNDAMENTAL: a instance called like a function (i.e., with `()`) is really calling the `apply` method

  // ESSENTIAL: the `apply` syntactic sugar basically breaks the barrier between FP and OOP

  // FUNDAMENTAL: the mix between FP and OOP in Scala seems to translate into classes w/o setters (i.e., mutations)
  // -> Yields a new instance on each mutation

  // Exercises
  println((mary + "The rockstar" + "The legend")()) // IMPORTANT: `()` are required around the resulting Person instance
  // Since methods calls (here `+()`) have right associativity, another nickname is given to Mary w/ a nickname
  println(s"${tom.name} is ${tom.age} years old.")
  println(s"${tom.name} will be ${(+tom).age} years old.")
  // IMPORTANT: `()` are required around `+tom` o/w `age` is called before `+` (on the `Int` `age`)
  println(mary learns "functional programming")
  println(mary learnsScala)
  println(mary(5))
}
