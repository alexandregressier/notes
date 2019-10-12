package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal { // Animal is abstract data type: an anonymous class (but could also have been a trait)
    def eat(): Unit
  }

  val funnyAnimal: Animal = new Animal { // Scala supports class literals, a.k.a. /anonymous classes/
    override def eat(): Unit = println("Ah ah ah aha")
  }
  // FUNDAMENTAL: you did not instantiate an abstract class, but created an ad-hoc implementation and instantiating it

  // IMPORTANT: `getClass` is a method from `Object.java` that enables you to get the class of an instance in Scala
  println(funnyAnimal.getClass) // Prints `AnonymousClasses$$anon$1`, the name of the above anonymous class

  // What the compiler does behind the scenes:
  class AnonymousClasses$$anon$1 extends Animal {
    override def eat(): Unit = println("Ah ah ah aha")
  }
  val funnierAnimal: Animal = new AnonymousClasses$$anon$1

  // Also works for traits:
  trait Doable {
    def accomplish(): Unit
  }
  val washTheDishes = new Doable {
    override def accomplish(): Unit = println("Let's wash the dishes!")
  }
  val mawTheLawn = new Doable {
    override def accomplish(): Unit = println("Let's maw the lawn!")
  }
  // FUNDAMENTAL: anonymous classes are mostly used to have a one-shot ad-hoc implementations

  // LEARN: if you do not need to access the member variable outside, you should use class parameters
  class Person(name: String) {
    // LEARN: methods w/ side effects (i.e., result type = `Unit`) should not be parameterless and have `()`
    def sayHi(): Unit = println(s"Hi, my name is $name, how can I help?")
  }
  // Anonymous classes can also extend a concrete data types
  val jim = new Person("Jim") {
    override def sayHi(): Unit = super.sayHi() // `super` can be used to refer to the superclass
    // Extending a class w/o calling a proper constructor is illegal
    // -> The same applies to anonymous classes
  }
  jim.sayHi()

  // IMPORTANT: an anonymous class w/ an empty body is still anonymous:
  val tim = new Person("Time") {}
  println(tim.getClass) // Prints `AnonymousClasses$$anon$3`

  // IMPORTANT: since an anonymous class is immediately instantiated, it cannot be abstract:
  // -> It must not have any abstract members left undefined
}
