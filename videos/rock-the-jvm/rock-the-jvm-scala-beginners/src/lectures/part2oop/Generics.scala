package lectures.part2oop

object Generics extends App {
  // /Generics/ are part of the Scala type system
  // Goal: use the same code for multiple (potentially unrelated) types
  // "Type parameterization"

  // Example: enables to variablize the type of the elements held within the linked list
  // (Else not possible w/o duplicating the code for each wanted type w/ the features seen until now)
  class MyList[A] { // `MyList` is /parameterized/ w/ type `A`
    // Enables you to use the type `A` within the class body

    // `A` denotes a generic type
    // `A` makes the class reusable for any given type

    // VOCABULARY:
    // - `MyList` is a /generic class/
    // - `A` is a /type parameter/
    // - The type replacing `A` when instantiating `MyList` is a /type argument/

    // `A` is a common name for a single type parameter in Scala
    // Generally, type parameters are named with a single letter
    // -> But really they can be replaced w/ any desired name

    // IMPORTANT: the type parameters are placed before the class parameters: `class MyList[A](val foo: Int)`
  }
  val listOfIntegers = new MyList[Int] // The type argument `Int` will replace the type parameter `A` for this instance
  val listOfStrings = new MyList[String]

  // VOCABULARY: a class such as `MyList` is called a /container class/

  // A generic class can have multiple type parameters
  class MyMap[Key, Value]

  // You can also have /generic traits/
  trait Doable[A]

  // FUNDAMENTAL: Scala's objects cannot be type parameterized
  // -> Makes sense b/c there will always be a single instance

  // Generic methods
  object MyList {
    // Construct an empty `MyList` instance parameterized with the given type `A`:
    def empty[A]: MyList[A] = new MyList[A] // A /generic method/
    // Enables you to use the type `A` within the parameter list, the result type, and the body of the method
    // The type parameter must follow the name of the method and be placed before the method parameters
  }
  val emptyListOfInts = MyList.empty[Int] // Inferred type: `Generics.MyList[Int]`

  // Variances
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // The variance problem: if `B` extends `A`, should `Container[A]` extend `Container[B]`?
  // Example: if `Cat` extends `Animal`, should `MyList[Cat]` extend `MyList[Animal]`?
  // 3 possible answers:

  // 1. Yes: `MyList[Cat]` does extend `MyList[Animal]` (i.e., specific type arguments can substitute the generic ones)
  // -> /Covariance/
  class CovariantList[+A] // `+` makes the type `A` covariant

  // You use a `CovariantList` in the same style that you would use `Cat` for `Animal`:
  val animal: Animal = new Cat
  val covariantAnimals: CovariantList[Animal] = new CovariantList[Cat]
  // Then, would `covariantAnimals.add(new Dog)` be acceptable? -> Non-trivial exercise
  // -> In theory: yes, but it would actually "pollute" the `CovariantList[Cat]` instance referenced by `animals`

  // 2. No: `MyList[Cat]` and `MyList[Animal]` are distinct and cannot substitute one for another
  // -> /Invariance/
  class InvariantList[A] // IMPORTANT: type parameters are invariant by default
  //val invariantAnimals: InvariantList[Animal] = new InvariantList[Cat] // ERROR: type mismatch

  // 3. Hell no! (counter-intuitive concept): (i.e., generic type arguments can substitute the specific ones)
  // -> /Contravariance/
  // IMPORTANT: contravariance is the opposite /relation/ of covariance
  class ContravariantList[-A]  // `-` makes the type `A` contravariant
  val contravariantAnimals: ContravariantList[Cat] = new ContravariantList[Animal] // Would not compile without the `-`
  // Might not make sense b/c the list of `Animal`s can contain `Dog`s for example
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  // A trainer of `Animal` is "better suited" in that case: it can train any `Animal` (e.g, `Dog`s), including `Cat`s

  // VOCABULARY: a type belongs to a class

  // Bounded types
  // /Bounded types/ enable you to use your generic classes only for certain types that are either:
  // 1. A subtype of a given type (or the given type itself)
  class Cage[A <: Animal](val animal: A) // `Animal` is an /upper bounded type/
  // The `<:` operator indicates that `A` can only be replaced by subtypes of `Animal` (or `Animal` itself)
  val cage = new Cage(new Cat)
  class Car
  //val carCage = new Cage(new Car)
  // ERROR (unreported by IntelliJ):
  // inferred type arguments [Generics.Car] do not conform to class Cage's type parameter bounds [A <: Generics.Animal]

  // VOCABULARY: `[A <: Animal]` are /the class' type parameter bounds/

  // LEARN:
  // the generic type parameter you are defining is placed on the LHS whereas the constraining type is placed on the RHS

  // ESSENTIAL: just like Java, you do not have to provide a type argument between `[]` if this type argument is already
  // replacing the type parameter of a class/method parameter

  // 2. A supertype of a given type (or the given type itself)
  class Shelter[A >: Cat](val cat: A) // `Cat` is a /lower bounded type/
  // The `>:` operator indicates that `A` can only be replaced by supertypes of `Cat` (or `Cat` itself)
  val shelter = new Shelter(new Dog) // `Dog` shares a common supertype with `Cat`: `Animal`
  // `Dog` conforms to the type parameter bounds b/c it is a subtype of `Animal`, which is a supertype of `Cat`
  // -> ESSENTIAL: but the member variable `cat` will store an instance of Dog (it will not be casted!!!)

  // IMPORTANT: bounded types solve an annoying variance problem that occurs when writing covariant collections
  class SimpleList[+A] {
    //def add(element: A): SimpleList[A] = new SimpleList[A]
    // ERROR: covariant type `A` occurs in contravariant position in type `A` of value `element`
    // -> Technical version of the non-trivial exercise: given
    val animals: SimpleList[Animal] = new SimpleList[Cat]
    // what happens if `animals.add(new Dog)`?
    // -> Solution: yield a more generic list with the element added
    // Example: the new `SimpleList` resulting from calling `add` must have its type changed from the specific
    // `SimpleList[Cat]` to the more generic `SimpleList[Animal]`

    // Implementation:
    def add[B >: A](element: B): SimpleList[B] = new SimpleList[B]
    // We introduce a new type `B` which is a supertype of `A` and replace all the previous occurrences of `A` w/ `B`
    // FUNDAMENTAL: if to a list of `A`, you add in a `B` which is a supertype of `A`, then this list will turn into a
    // list of `B` (and not a list of `A`)

    // TAKEAWAY: use this technique to have heterogeneous container classes instances, that is container classes
    // instances that are made more and more generic
  }
  // How to generify a class using covariance:
  // 1. Add a covariant type parameter `[+A]` to an existing container class and all its subclasses
  // 2. Replace all the occurrences of the contained non-generic type w/ `A`
  // 3. Replace all the occurrences of the class' name with the class' name followed by a type argument `A` appended
  // 4. Implement the covariance problem's solution by type parameterizing all the methods w/ a at least one parameter
  // of type `A` and resulting in a new instance of the class type parameterized w/ `A` w/ `[B >: A]`

  // IMPORTANT: how to deal w/ an object like `Nil`, which should be a correct value for both `List[Int]` and
  // `List[String]` for example?
  // -> ESSENTIAL: since `Nothing` type is a proper substitute for any type, the object `Nil` should be a proper
  // substitute of a list of any type
  // -> `object Nil extends MyList[Nothing]` (and solve the covariance problem w/ `add[B >: Nothing](x: B): MyList[B]`)

  // 5. Replace all the occurrences of `A` occurring in such companion objects w/ `Nothing`
  // 6. Implement the covariance problem's solution by type parameterizing all the methods w/ a at least one parameter
  // of type `Nothing` and resulting in a new instance of the class type parameterized w/ `Nothing` w/ `[B >: Nothing]`
}
