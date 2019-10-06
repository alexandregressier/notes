package lectures.part2oop

object Objects { // We choose to not extend app here
  // So far we have referred to /objects/ in the sense of OOP as /instances/
  // -> B/c objects are a separate concept in Scala

  // IMPORTANT: Fundamental aspect of OOP: /class-level functionality/ (as opposed to /instance-level functionality/)
  // -> Functionality that does not depend on a particular instance of a class

  // In Java: instance variables/methods (i.e., members) VS class variables/methods (i.e., members)
  // -> Marked w/ the `static` modifier

  // class Person {
  //   public static final int NB_OF_EYES = 2; // A /class variable/ (as opposed to an /instance variable/)
  // }
  // Usage: `Person.NB_OF_EYES`

  // FUNDAMENTAL: Scala does not have class-level functionality (-> no `static` modifier) but has even better: /objects/

  // In Scala:
  println(Person.NB_OF_EYES) // Members can be accessed above the object's definition

  object Person {
    val NB_OF_EYES = 2

    def canFly: Boolean = false

    println("Side effect of the person object!")

    "I am ignored"
  }
  println(Person.NB_OF_EYES)
  println(Person.canFly)

  // INTELLIJ TIP: copy and pasting Java code to a Scala file causes IntelliJ to ask you if you want to convert it

  // An object can have definition for vals, vars, methods, side effects... in its body
  // -> Same properties as a class body (side effects are executed as soon as the program is run)
  // Exception: objects are unable to receive parameters since they do not have a constructor (to build an instance)

  // IMPORTANT: differences between Scala's objects and Java's class members:
  // - Objects are used as singleton instances
  //   -> When defining the object `Person`, the type `Person` is defined as well as its only possible instance
  //   -> Thus the keyword `object` makes total sense
  val mary = Person // `Person` refers to the only instance of the type `Person` (note the absence of `new`)
  val john = Person
  println(mary == john) // true - `mary` and `john` refer to the same instance: the only one of the type `Person`

  // - Objects are singleton instances by definition (w/o any other code needed)
  //   -> The /singleton pattern/ in a single definition

  // Common pattern: the /companions pattern/:
  // Write objects and classes with the same name (in the same scope/file)
  // -> Can access each other's private members (just like `friend` classes in C++)
  // -> Goal: separate instance-level functionality from class-level functionality (i.e., `static`)
  class Person
  // "Companion class" and "companion object"

  // The class `Person` and the object `Person` are companions b/c they:
  // 1. Reside in the same scope
  // 2. Have the same name
  // -> The instance (regular or singleton) picked up to access a given member is abstracted away
  // -> The correct resolution is automatic

  // With the companions pattern, all the code that we write can only be accessed in an instance of something
  // -> Which makes Scala more object-oriented than most mainstream languages (including Java, b/c of `static` members)
  // -> But remember that Scala was designed as a functional language

  val jake = new Person // No error b/c the `new` operator is automatically applied to the class and not the object
  println(mary == jake) // false - `jake` is not the singleton instance of the `Person` object
  // -> + compiler warning: comparing unrelated types

  // In a nutshell: there is a single instance of `Person` that can be accessed w/o `new`
  // -> But you can still instantiate `Person` w/ `new` using automatically the companion class

  // In practice, you often have /factory methods/ within object's bodies:
  object Dog {
    def from(mother: Dog, father: Dog): Dog = // A factory method that build `Dog` instances
      new Dog(mother.name.substring(0, mother.name.length / 2) + father.name.substring(father.name.length / 2))

    def apply(mother: Dog, father: Dog): Dog = from(mother, father)
  }

  class Dog(val name: String) {
    override def toString: String = name // Auto-generated with IntelliJ
  }
  // Factory method: a method whose sole purpose is to build instances of the companion class given some parameters

  val daïla = new Dog("Daïla") // IMPORTANT: Scala supports Unicode characters in identifiers
  val toby = new Dog("Toby")
  println(s"$daïla + $toby = ${Dog.from(daïla, toby)}") // Prints "Daby"

  // In practice,factory methods are called in a convenient way using `apply` and its syntactic sugar:
  println(Dog.apply(daïla, toby))
  println(Dog(daïla, toby))
  // -> Resembles a constructor
  // -> Widely used pattern

  // `apply` is generally overloaded as many times as there are factory methods to be defined


  // Scala application: a Scala object w/ a special method `main` obtained by extending the `App` /trait/
  // def main(args: Array[String]): Unit

  // Scala applications are turned into JVM applications whose entry point have to be:
  // public static void main(String[] args)

  // `Unit` is translated into `void`
  // `static` is reproduced by using an object (extending the `App` trait

  // Can be written explicitly instead of extending `App`:
  def main(args: Array[String]): Unit = {
    println("The Objects body was executed before this statement")
  }

  println("I am even after the main member method")

  // In a nutshell: a Scala application is a Scala object with the exact `main` signature implemented

  // See scala.App for more implementation details
}
