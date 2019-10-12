package lectures.part2oop

object CaseClasses extends App {
  // For lightweight data structures (e.g., the class `Person`), there is a lot of boilerplate to implement:
  // - Companion objects
  // - Common methods for serializing and pretty-printing (e.g., equals, hashCode, toString)

  // /Case classes/ are a Scala-specific concept that address this problem
  // -> A shorthand for defining a class, a companion object, and a lot of sensible defaults in one go
  case class Person(name: String, age: Int)

  // Case classes are named as such because they are involved in /pattern matching/

  val jim = new Person("Jim", 34)
  // Features:
  // 1. All class parameters are promoted to member variables
  println(s"His name is ${jim.name} and he is ${jim.age} years old.")

  // 2. Case classes implement a sensible `toString` method
  println(jim) // Print `Person(Jim,34)` (instead of `CaseClasses$Person@69d9c55`)
  // REMEMBER: Java implicitly calls the method `toString` on methods requiring a string representation of an argument

  // 3. Case classes implement sensible `equals` and `hashCode` methods
  // -> Especially useful when used in collections
  val copycat = new Person("Jim", 34)
  // LEARN: you can safely use `==` for comparing instances in Scala
  println(s"jim(${jim.hashCode}) == copycat(${copycat.hashCode})? -> ${jim == copycat}") // Prints `true`
  // FUNDAMENTAL: `false` would have been printed o/w (i.e., when using regular classes)
  // -> Explanation: when `equals` and `hashCode` are unimplemented, the implementation from `AnyRef` is used
  // -> They are based on the instance's reference, thus yielding `false`
  // LEARN: you can safely use `==` for comparing instances in Scala

  // 4. Case classes implement a handy `copy` method (a.k.a. cloning)
  val antiJim = jim.copy()
  println(s"jim(${jim.hashCode}) == antiJim(${antiJim.hashCode})? -> ${jim == antiJim}") // Prints `true`

  // FUNDAMENTAL: nevertheless, all those equal instances have their own instance's reference

  // Copy offers customization over the copied object via arguments:
  val oldJim = jim.copy(age = 45)
  println(oldJim) // Prints `Person(Jim,45)`

  // 5. Case classes have companion objects
  val anon = Person // `Person` refers to the class `Person`'s companion object
  // Remember: when placing trailing `()`, you are really calling the `apply` method

  // Those companion objects have some handy factory methods:
  val mary = Person("Mary", 23) // Delegates to the object `Person`'s `apply`, which does the same thing as the ctor
  // -> In practice, this enables you to omit the keyword `new` when instantiating a case class

  // 6. Case classes are serializable
  // -> Especially useful when dealing w/ distributed systems: exchanging classes over the network or in between JVMs
  // -> For example, the /Akka framework/ deals w/ exchanging serializable messages through the network

  // 7. Case classes implement extractor patterns
  // -> Enable case classes to be used in pattern matching (one of the most powerful Scala features)


  // There are also /case objects/
  // -> Same properties as case classes, except they are objects (+ they cannot have a companion object)
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  // Example: making `Cons` and `Nil` a case class and a case object respectively enables `LinkedList` to:
  // - Compare 2 instances w/ the same content (w/o having to implement a recursive `equals`)
  // - Be used in other collections, b/c of `hashCode` and `equals`
  // - Be used in distributed systems, b/c it is serializable

  // INTELLIJ TIP: case classes and case objects can be selected when creating a new Scala file/class

  // TAKEAWAY: case classes and case objects are a gold mine
}
