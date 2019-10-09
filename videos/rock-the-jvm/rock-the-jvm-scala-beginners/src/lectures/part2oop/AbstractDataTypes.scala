package lectures.part2oop

object AbstractDataTypes extends App {
  // There are situations where you need to leave some class members unimplemented
  // -> /Abstract members/
  // A class containing abstract members is an /abstract class/ and must be marked w/ `abstract`
  // -> (Although, it can really have 0 to n abstract members and/or 0 to n /concrete members/)
  abstract class Animal {
    val creatureType: String // Abstract member variable - No value supplied (great feature of Scala!)
    def eat(): Unit // Abstract method - No implementation supplied
  }
  // IMPORTANT: an abstract classes cannot be instantiated
  // -> No implementation to run

  // Abstract classes are meant to be instantiated by providing abstract members implementations in the extending class
  // IMPORTANT: a class extending an abstract class that does not implement all abstract members is still abstract
  abstract class Canine extends Animal { // Still abstract b/c `eat` is unimplemented
    // FUNDAMENTAL: although not required, implementation of inherited abstract members should be marked w/ `override`
    // -> Not required because there is no prior implementation to override
    override val creatureType: String = "Canine"
  }
  class Dog extends Canine { // No longer abstract
    override def eat(): Unit = println("Crunch crunch")
  }
  // WEIRD: these are valid class definitions `class Dog extends` and `class Dog extends {}`

  // Traits - The ultimate abstract data type in Scala
  // ESSENTIAL: just like an abstract class, a trait is an abstract type
  // Similar to interfaces in other languages (e.g., Java)
  trait Carnivore { // Just like abstract class definitions, traits have abstract members
    def eat(animal: Animal): Unit
  }
  trait ColdBlooded // No implementation yet

  // FUNDAMENTAL: unlike abstract classes, traits can be inherited along classes (reminiscent of multiple inheritance)
  class Crocodile extends Animal with Carnivore with ColdBlooded { // `with` denotes trait implementation
    // A single class can inherit multiple traits by adding another `with` keyword
    // -> This is how you bypass the single class inheritance restriction
    // "You can /mix in/ as many traits as you want" (rather than /implementing/ as for Java interfaces)
    // /Mixins/ are traits which are used to compose a class

    // All the inherited abstract members (from parent class and traits) must be implemented to have a /concrete class/
    override val creatureType: String = "Reptile"

    override def eat(): Unit = println("Munch munch")

    override def eat(animal: Animal): Unit = // Overloaded via inheritance
      println(s"I'm a croc and I am eating a ${animal.creatureType}")
  }
  // INTELLIJ TIP: <C-i> to implement abstract members
  // MISC: /K9/ is a homophone of "canine" that generally designate police dogs
  val dog = new Dog // REMEMBER: `()` are optional for default (i.e., parameterless) constructors
  val crocodile = new Crocodile
  crocodile.eat(dog) // Prints `I'm a crocodile and I am eating a Canine`

  // IMPORTANT: a class and a trait can have the same name in the same scope
  // -> But a class and a variable can have the same name in the same scope (when breaking one of the casing rules)

  // Traits VS abstract classes
  // Common:
  // - Both can have abstract members and can have concrete members
  abstract class Human {
    val eyesColor: String
    val nbOfEyes = 2
    // QUESTION: why having concrete members in an abstract class when you have companion objects?
  }
  abstract class Omnivore {
    def playWith(animal: Animal): Unit
    def preferredMeal: String = "Bananas"
  }

  // Differences:
  // 1. Traits cannot have constructor parameters
  //class Carnivore(nbOfTeeth: Int) // ERROR
  // 2. A class can only extend one class but can *mix in* multiple traits
  // 3. More subtle (but a matter of choice):
  // Traits are meant to describe a type of *behavior* (what they do)
  // WHEREAS
  // Abstract classes are meant to describe type of *being* (= what they are)

  // Scala's type hierarchy:
  // - `scala.Any` is the parent of all types
  //
  // - `scala.AnyRef` (derived from `Any`): every classes (e.g, String, List, Set, Animal) is implicitly derived from
  // `AnyRef` unless another class is explicitly extended
  //   -> Mapped to Java's `Object`
  //
  // - `scala.Null` is derived from all of these, and its only instance/value is `null`
  //   -> FUNDAMENTAL: `null` means no reference (-> `null` can be assigned to any (i.e., `Any`) class type)
  //
  // - `scala.AnyVal` (derived from `Any`): all the primitive types (e.g., Int, Unit, Boolean, Float) and some (very
  // rare) classes that would extend `AnyVal` (maybe for memory optimizations reasons)
  //
  // - `scala.Nothing` is derived from everything (even `Null`), and thus can replace anything (useful for /exceptions/
  // and expressions returning nothing)
  //   -> FUNDAMENTAL: `Nothing` is not instantiable and is represented as `???` (unusable expression, the /bottom/)
  //   Implementation:
  //   def ???: Nothing = throw new NotImplementedError

  val nada: Any = null // Widest gap possible

  // See https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg for more details
}
