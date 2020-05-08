package terminal

object Hkt { // Type aliases cannot be written at the top-level

  trait Foo[C[_]] { // `C` is a HKT
    def create(i: Int): C[Int]
  }

  // `List` is a _type constructor_ b/c it matches `C[Int]`
  object FooList extends Foo[List] {
    def create(i: Int): List[Int] = List(i)
  }

  // You can use types w/ multiple types parameters using the _type parameter hole_ `_`
  type EitherString[T] = Either[String, T] // Type aliases don't define new types: they are just like a `#define`

  // The compiler is tricked:
  object FooEitherString1 extends Foo[EitherString] {
    override def create(i: Int): Either[String, Int] = Right(i)
  }

  // Alternatively, we can use the kind projector plugin
  object FooEitherString2 extends Foo[Either[String, *]] { // Use `*` instead of `?`
    override def create(i: Int): Either[String, Int] = Right(i)
  }

  // You can even ignore the type constructor by defining
  type Id[T] = T // `Id[Int]` == `Int`, by substituting `Int` into T

  object FooId extends Foo[Id] {
    override def create(i: Int): Id[Int] = i
  }
  // Thus any type can be a HKT
}
