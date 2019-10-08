package lectures.part2oop

import lectures.part2oop.InheritanceAndTraits.Fish

object InheritanceAndTraits extends App {

  // Scala's /inheritance model/:
  // /Single class inheritance/ but no /multiple class inheritance/ (just like Java, and unlike C++ for example)
  // -> But much more intricate, notably b/c of /traits/
  class Animal {
    private def sleep = println("Zzz") // `private` is an /access modifier/

    // Members w/o an access modifier are public by default (but there is no public modifier in Scala)
    def drink = println("Sip")

    // The `protected` access modifier enables members to be accessed within:
    // - The body of this class
    // - The body of every subclass (including subsubclasses, and on)
    // -> But not outside!
    protected def eat = println("Nom nom nom")

    val creatureType = "Wild"

    final def jump = println("Hop")
  }

  class Cat extends Animal // The `extends` keyword denotes inheritance (just like Java)
  // `Cat` is a subclass of `Animal` (or /derived class/ or /child class/)
  // `Animal is superclass of `Cat` (or /parent class/)

  class Duck extends Animal {
    def crunch = {
      eat // Access to a protected method
      // IMPORTANT: you could have explicitly written `super`
      // `super` is used when you want to reference a member from a superclass
      // -> Just like `this`, `super` is sometimes required because a name is masking the same one in another scope
      println("Crunch crunch")
    }
  }

  val cat = new Cat
  val duck = new Duck
  // REMEMBER: inheritance enables a subclass to inherit all the non-private members of the given superclass
  //cat.sleep // ERROR: symbol `sleep` is inaccessible from this place
  cat.drink // Prints `Slurp`
  //dog.eat // ERROR: same as above
  duck.crunch // Prints `Nom Nom Nom\nCrunch crunch`

  // Constructors and inheritance
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0) // REMEMBER: this is how you define an auxiliary ctor
  }
  //class Adult(name: String, age: Int, idCard: String) extends Person
  // ERROR: unspecified value parameters: `name` and `age
  // The compiler is unable to find the corresponding ctor fot the call `Person` (i.e., no parameterless/default ctor)

  // REMEMBER: the class signature is the primary constructor
  // JVM rule: when a derived class is instantiated, the JVM will need to call the ctor of the parent class first
  // FUNDAMENTAL: the Scala compiler forces you to guarantee that there is a correct super constructor call
  // -> Thus you must pass in the correct arguments when extending a class with parameters:
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  // IMPORTANT: you can also use any auxiliary (i.e., overloaded) ctor

  // Overriding - Supplying a different member implementation in derived classes
  // You can override any non-private member (not only methods) by using the keyword `override` followed by redefinition
  class Dog extends Animal {
    override def eat: Unit = println("Munch munch")
    // IMPORTANT: you can use an access modifier between `override` and `def`
    // (Not carried over from the superclass when overriding)

    // INTELLIJ TIP: IntelliJ provides autocompletion when overriding

    // FUNDAMENTAL: in Scala, you can override vals and vars just as well
    override val creatureType = "Domestic" // Using `_` will not use the parent's value for `creatureType`

    // FUNDAMENTAL: `_` is the /placeholder parameter/ and it must be bound

    override def drink = println("Slurp")
  }
  val dog = new Dog
  dog.eat // The access modifier of the `eat` method was also changed to public
  println(dog.creatureType) // Prints `Domestic`
  println(duck.creatureType) // Prints `Wild` (not overridden)

  // FUNDAMENTAL: Scala offers member variable overriding

  // INTELLIJ TIP: use icons w/ arrows in the gutter to explore members from parent or child classes

  // SHORTHAND:
  // Unlike methods, member variables have the special property that they can be overridden directly in the ctor
  // -> The overriding value must be passed as argument (if no default parameter value is set)
  class Bird(override val creatureType: String) extends Animal // (do not forget to extend `Animal`)

  // Same thing using an intermediate parameter:
  class BirdAlt(birdType: String) extends Animal {
    override val creatureType = birdType
  }
  val bird1 = new Bird("Tamed")
  val bird2 = new BirdAlt("Tamed")

  // Type substitution (i.e., polymorphism, in a broader sense)
  val unknownAnimal1: Animal = new Dog // Explicitly declared as `Animal`
  val unknownAnimal2: Animal = new Cat
  unknownAnimal1.drink // Prints `Slurp` from `Dog`
  unknownAnimal2.drink // Prints `Sip` from `Animal` (b/c not overridden in `Cat`)

  // ESSENTIAL:
  // like other OO languages, an instance type annotated with a superclass is stored in memory under its original class
  // -> Thus the implementation of overridden members is used when members of the superclass are accessed

  // CAUTION:
  // Overriding (i.e., supplying a different member implementation in derived classes)
  // IS NOT
  // Overloading (i.e., supplying multiple methods with the same name w/ different signatures in the same class)

  // Prevent overriding:
  // 1. Use `final` on the wanted members (
  final class Monkey extends Animal {
    //override protected def jump = println("Hop hop") // ERROR: method `jump` cannot override a final member
  }
  // 2. Use `final` on a class that one would not like to see extended
  // -> "Illegal inheritance"
  // For example: in Scala, numerical classes and `String` are `final`
  // IMPORTANT: `final` modifier is redundant for `final` class/object members
  //
  // 3. /Seal/ the class (i.e., mark it as `sealed`)
  // Seal: restriction that prevents the extension (i.e., inheritance) of a class *outside* of the file it resides in
  // -> Used when you want to be exhaustive in your type hierarchy (e.g,, /sealed traits/)
  sealed class Fish extends Animal
  // (`sealed` is useless here because the class is only accessible within the scope of the current "main" class)
  // -> I.e., `Fish` is unresolved outside this file

  class GoldFish extends Fish
}

class ClownFish extends Fish // Can be written outside the "main" class but not outside this file
