package lectures.part2oop

import scala.annotation.tailrec

object OOBasics extends App {
  // OOP: associate data (i.e., a state) to a behavior

  // A /class/ organizes data and behavior that is code (often called a "blueprint")
  // /Instantiation/ (=/= Object) means concrete realizations in memory that are actual /memory spaces/ that conform to
  // the code and data structure that class describe

  val simplePerson = new SimplePerson // LEARN: to instantiate a class, you use the operator `new`
  // As usual, `()` are optional for parameterless methods/functions
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

  // Exercises
  val martin = new Writer("Martin", "Odersky", 1958)
  println(martin.fullName) // LEARN: `()` can be omitted for your own methods

  val programmingInScala = new Novel("Programming in Scala", 2008, martin)
  println(programmingInScala.authorAgeAtYearOfRelease)
  println(programmingInScala.isWrittenBy(new Writer("Martin", "Odersky", 1958)))
  println(programmingInScala.isWrittenBy(new Writer("Martin", "Fowler", 1963)))

  val programmingInScala3rdEdition = programmingInScala.releaseNewEdition(2016)
  println(programmingInScala3rdEdition.authorAgeAtYearOfRelease)
  println(programmingInScala3rdEdition.isWrittenBy(programmingInScala.author))

  val c1 = new Counter()
  println(c1.currentCount)
  // The dot operator can be omitted
  val c2 = c1++()++() // Blank before the method name can be omitted
  println(c2.currentCount)
  //println(c2--().currentCount()) // ERROR: `c2--()` has no `currentCount` method
  println(c2.--().currentCount)

  val cx = new GoldenCounter
  println(cx.inc.i) // Not the same as `cx inc i`
  println(cx inc 5 dec 3 inc 7 i) // 9 - Using the post fix operator notation
}

// Class definitions can sit on the top-level code (instead of being nested in the main class)
class SimplePerson // That is all

class PersonWithoutMembers(name: String, age: Int) // A /constructor/ (abbreviated /ctor/)
// A constructor requires you to specify value for parameters when instantiating the class
// This how you pass parameters to a class
// This resembles the way that we pass parameters to functions but the syntax has a different meaning

// Use the `val` and `var` keywords to define the matching class member for each class parameter
// FUNDAMENTAL: It is not always the case that you want to convert class parameters to class members
class Person(val name: String, var age: Int) { // `{}` delimit the class' body (which is not an expression, no `=`)
  // IMPORTANT: the value of the class' body is ignored: its only purpose is being an implementation

  // The body can contain the same things that a block expression (i.e., code block) does:
  // vals, vars, methods, expressions, definitions (e.g., classes, packages)...

  // Define a member variable w/o the ctor
  val x = 2 // Variables defined inside the body are member variables

  println(1 + 3) // IMPORTANT: printed when the ctor is called (i.e., using `new`)

  // /Methods/ are member functions
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  // IMPORTANT: you should write type annotations explicitly for public methods

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

// Exercices
class Writer(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  // ELEMENTAL: for parameterless methods, `()` are optional as well in the signature as the call
  // -> Not recommended when the result type is `Unit` though
  // -> Not working when the name of the method does not contain letters or digits (e.g., `++()`)

  // ELEMENTAL:
  // Called without `()`, a method actually yields the result of its application (i.e., the calling of the method)
  def fullName = s"$firstName $lastName"

  // The default implementation of `equals` (from Java) compares the object references
  // Such implementation depends on your needs, but it does not correspond to ours
  override def equals(other: Any): Boolean = other match {
    case that: Writer =>
      (that canEqual this) &&
        firstName == that.firstName &&
        lastName == that.lastName &&
        yearOfBirth == that.yearOfBirth
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Writer]
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
  def authorAgeAtYearOfRelease: Int = yearOfRelease - author.yearOfBirth

  def isWrittenBy(writer: Writer): Boolean = author equals writer

  def releaseNewEdition(yearOfRelease: Int) = new Novel(this.name, yearOfRelease, this.author)
  // Prefer to write `this` explicitly in this case to avoid confusion
  // IMPORTANT We are in FP: we do not mutate the inner state! (But create new objects!)!
}

class Counter(val current: Int = 0) {
  // IMPORTANT: we are in FP, so we do not use `var` nor `private` state
  // -> The same principle as declaring vals but extended to instances and classes
  // ELEMENTAL: instances are fixed: their inner state cannot be mutated
  // Whenever you need to mutate inner state of an instance, you actually to yield a new instance

  // Note that the `current` member variable is already accessible prior the definition of `currentCount`
  def currentCount: Int = current // IMPORTANT: methods cannot have the same names as field (b/c `()` can be omitted)
  def ++(n: Int): Counter = new Counter(current + n) // IMPORTANT: there are no restrictions in regards to method names
  def ++(): Counter = ++(1) // Use method overloading instead of default parameter values for variety
  def --(n: Int = 1): Counter = new Counter(current - n)

  // Recursive version
  def inc = new Counter(current + 1)

  // IMPORTANT: this method must be final (or the class) b/c overloading is forbidden for tail recursive methods
  @tailrec
  final def incRec(n: Int): Counter =
    if (n == 0) this // LEARN: `this` refers to the last `incRec` call in the call stack
    else inc.incRec(n - 1) // ELEMENTAL: you can contextualize the recursive call by calling it from the result of `inc`
  // -> Could have been called from `this`

  // For n = 3: inc.incRec(2)
  // For n = 2: inc.inc.incRec(1)
  // For n = 1: inc.inc.inc.incRec(0)
  // For n = 0: inc.inc.inc(.this) = inc.inc.inc <- ELEMENTAL: yielding `this` enables to end the call chain
}

// ELEMENTAL: this is how you chain method calls recursively
// -> This is also how you define atomic steps (atomic mutations) and their repetition via recursion
final class GoldenCounter(val i: Int = 0) {
  def inc: GoldenCounter = {
    println("++")
    new GoldenCounter(i + 1)
  }

  def dec: GoldenCounter = {
    println("--")
    new GoldenCounter(i - 1)
  }

  @tailrec
  def inc(n: Int): GoldenCounter = // Since tailrec is optimized ot a loop, this is comparatively efficient to adding n
    if (n == 0) this
    else inc.inc(n - 1)
  // Syntax: atomicStep().recurse(n - 1)
  //                      +-> atomicStep().recurse(n - 1) OR this

  @tailrec
  def dec(n: Int): GoldenCounter =
    if (n == 0) this
    else dec.dec(n - 1)
}
