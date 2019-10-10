package lectures.part2oop

object Generics extends App {
  // /Generics/ are part of the Scala type system
  // "Type parameterization"

  // -> For example, enables to variablize the type of the elements held within the linked list (but still homogeneous)
  // (Else not possible w/o duplicating the code for each wanted type w/ the tools seen until now)
  class MyList[A] { // `MyList` is /parameterized/ w/ type `A`
    // `A` denotes a generic type
    // `A` can be used within the class body
    // `A` makes the class reusable for any given type that we can store inside this generic list

    // VOCABULARY:
    // - `MyList` is a /generic class/
    // - `A` is a /type parameter/
    // - The type replacing `A` when instantiating `MyList` is a /type argument/

    // `A` is a common name for a single type parameter in Scala
    // -> But `A` can really be replaced w/ any desired name

    // IMPORTANT: class parameters are placed after the types: `class MyList[A](val foo: Int)`
  }
  val listOfIntegers = new MyList[Int] // FUNDAMENTAL: `Int` will replace the generic type `A` for this instance
  val listOfStrings = new MyList[String]

  // A generic class can have multiple type parameters
  class MyMap[Key, Value]

  // You can also have /generic traits/
  trait Doable[A]

  // FUNDAMENTAL: Scala's objects cannot be type parameterized

  // Generic methods
  object MyList {
    // Construct an empty `MyList` instance parameterized with a given type `A`
    def empty[A]: MyList[A] = new MyList[A]
    // The type parameter must follow the name of the method to be used in the parameter list, the result type, or the
    // body of the method
  }
  val emptyListOfInts = MyList.empty[Int] // Inferred type: `Generics.MyList[Int]`

  // Variances
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // The variance problem: if `Cat` extends `Animal`, does a `MyList[Cat]` also extends `MyList[Animal]`?
  // 3 possible answers:

  // 1. Yes: `MyList[Cat]` does extend `MyList[Animal]` -> /Covariance/
  class CovariantList[+A] // `+` makes the type `A` covariant

  // You use a `CovariantList` in the same style that you would use `Cat` for `Animal`:
  val animal: Animal = new Cat
  val covariantAnimals: CovariantList[Animal] = new CovariantList[Cat]
  // Then, would `animals.add(new Dog)` be OK ? -> Non-trivial question
  // -> In theory: yes, but it would actually "pollute" the `CovariantList[Cat]` instance referenced by `animals`

  // 2. No: `MyList[Cat]` and `MyList[Animal]` are distinct and cannot substitute one for another  -> /Invariance/
  class InvariantList[A] // Type parameters are invariant by default
  //val invariantAnimals: InvariantList[Animal] = new InvariantList[Cat] // ERROR: type mismatch

  // 3. Hell no! (Counter-intuitive concept) -> /Contravariance/
  // IMPORTANT: contravariance is the opposite /relation/ of covariance
  class ContravariantList[-A]  // `-` makes the type `A` contravariant
  val contravariantAnimals: ContravariantList[Cat] = new ContravariantList[Animal] // Would not compile without the `-`
  // Might not make sense at first glance b/c the list of `Animal`s can contain `Dog`s for example
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  // A trainer of `Animal` is better suited in that case: it can train any `Animal` (e.g, `Dog`s), including `Cat`s

  // Bounded types
  // /Bounded types/ enable you to use your generic classes only for certain types that are either:
  // 1. A subtype of a given type (or the given type itself)
  class Cage[A <: Animal](animal: A) // `Animal` is an /upper bounded type/
  // The `<:` operator indicates that `A` can only be replaced by subtypes of `Animal`
  val cage = new Cage(new Cat)
  class Car
  //val carCage = new Cage(new Car)
  // ERROR (not reported by IntelliJ):
  // inferred type arguments [Generics.Car] do not conform to class Cage's type parameter bounds [A <: Generics.Animal]

  // IMPORTANT: just like Java, you do have to provide a type argument between `[]` if this type argument is already
  // replacing a the type parameter of a method/class parameter

  // 2. A supertype of a given type (or the given type itself)
  class Shelter[A >: Cat](cat: A) // `Cat` is a /lower bounded type/
  val shelter = new Shelter(new Dog) // `Dog` shares a common supertype with `Cat`: `Animal`
}
