package lectures.part2oop

object Generics extends App {
  // /Generics/ are part of the Scala type system
  // "Type parameterization"

  // -> For example, enables to variablize the type of the elements held within the linked list (but still homogeneous)
  // (Else not possible w/ the tools seen until now)
  class MyList[A] { // `A` denotes a generic type
    // `A` can be used within the class body
    // `A` makes our class reusable for any given type that we can store inside the generic list

    // VOCABULARY:
    // - `MyList` is a /generic class/
    // - `A` is a /type parameter/

    // `A` can really be replaced w/ any desired name
  }
  val listOfIntegers = new MyList[Int] // FUNDAMENTAL: `Int` will replace the generic type `A` for this instance
  val listOfStrings = new MyList[String]

  // A generic class can have multiple type parameters
  class MyMap[Key, Value]

  // You can also have /generic traits/
  trait Doable[A]
}
