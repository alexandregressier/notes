package lectures.part2oop

object OOBasics extends App {
  // A /class/ organizes data and behavior that is code (often called a "blueprint")
  // /Instantiation/ (=/= Object) means concrete realizations in memory that are actual /memory spaces/ that conform to
  // the code and data structure that class describe

  val simplePerson = new SimplePerson // LEARN: to instantiate a class, you use the operator `new`
  // As usual, `()` are optional
  // By convention, class names use the UpperCamelCase notation whereas instantiations use lowerCamelCase

  println(simplePerson) // Prints the object's string representation (the object reference by default)
  // Example: "lectures.part2oop.Person@7f560810"class

  val person1 = new PersonWithoutMembers("Alex", age = 22) // Named parameters make the code more readable here

  // FUNDAMENTAL: In Scala, the terms "members", "member/private variable", "method" are used

  // Access member variables
  //person2.age // ERROR: cannot resolve symbol
  // IMPORTANT: `name` and `age` are /class parameters/, but not /class members/ (accessible w/ the `.` operator)
  val person2 = new Person(name = "John", age = 19)
  // The body and all the side effects within are executed (and not evaluated) at each instantiation of a class

  println(person2.age)
  println(person2.x)

  person2.greet("Daniel")

}

// Class definitions can sit on the top-level code (instead of being nested in the main class)
class SimplePerson // That is all

class PersonWithoutMembers(name: String, age: Int) // A /constructor/ (abbreviated /ctor/)
// A constructor requires you to specify value for parameters when instantiating the class
// This how you pass parameters to a class
// This resembles the way that we pass parameters to functions but the syntax has a different meaning

// Use the `val` and `var` keywords to define the matching class member for each class parameter
// IMPORTANT: It is not always the case that you want to convert class parameters to class members
class Person(val name: String, var age: Int) { // `{}` delimit the class' body (which is not an expression, no `=`)
  // IMPORTANT: the value of the class' body is ignored: its only purpose is being an implementation

  // The body can contain the same things that a block expression (i.e., code block) does:
  // vals, vars, methods, expressions, definitions (e.g., classes, packages)...

  // Define a member variable w/o the ctor
  val x = 2 // Variables defined inside the body are member variables

  println(1 + 3) // IMPORTANT: printed when the ctor is called (i.e., using `new`)

  // /Methods/ are member functions
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")
  // The `name` parameter is masking (i.e., resolving at another scope) the `name` member variable
  // Solutions:
  // - Use another name for the parameter
  // - Use the `this` keyword, which enables you to access members of the current instance

  // Using string interpolation, `${}` is required to access members via `this`

  // LEARN: if `name` was only a class parameter (and not member), it would have been still accessible via `this.name`
  // -> FUNDAMENTAL: `this` enables you to access both class members and class parameters

  def greetAlt(otherName: String): Unit = println(s"$name says: Hi $otherName") // `name` is resolved to `this.named`

  // Just like Java, Scala also have function/method /overloading/ (via erasures)
  // Function/method overloading: defining function/methods w/ the same name but w/ different signatures (i.e., erasure)
  // -> Different signatures means different number of parameters or at least one different type for a parameter
  // FUNDAMENTAL: the result type is not subject to overloading
  def greet(): Unit = println(s"$name says: Hi")

  // Constructor overloading
  def this(name: String) = this(name, 0) // Calls the /primary constructor/

  // `this()` refers to a constructor

  // FUNDAMENTAL:
  // class Cat {
  //   val color = ""
  //
  //   def this(color: String) = {
  //     this.color = color // ERROR: the implementation /auxiliary constructor/ has to be another constructor
  // }

  // This limitation makes auxiliary ctors impractical, except for using them for default parameter values
  def this() = this("John Doe")

  // -> You are better off using default parameters values
  // IMPORTANT: you can mix default parameters values with ctor overloading to get unique combinations

  // LEARN: a constructor has no result type

  "Hey" // Ignored
}
