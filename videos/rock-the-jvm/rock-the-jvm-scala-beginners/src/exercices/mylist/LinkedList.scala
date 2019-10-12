package exercices.mylist

import scala.annotation.tailrec

/**
 * Singly linked list.
 * <p>
 * This is a rework of [[exercices.mylist.solution.MyList]].
 */
abstract class LinkedList[+A] {
  def head: A

  def tail: LinkedList[A]

  def last: A

  def init: LinkedList[A]

  def isEmpty: Boolean

  def add[B >: A](x: B): LinkedList[B]

  def append[B >: A](x: B): LinkedList[B] = concat(Cons(x))

  //@tailrec // TODO: find out why `@tailrec` is no longer working by adding a type parameter
  final def addAll[B >: A](xs: LinkedList[B]): LinkedList[B] =
    if (xs.isEmpty) this
    else add(xs.head).addAll(xs.tail)

  def concat[B >: A](xs: LinkedList[B]): LinkedList[B]

  final def ++[B >: A](xs: LinkedList[B]): LinkedList[B] = concat(xs)

  def reverse: LinkedList[A]

  def map[B](t: MyTransformer[A, B]): LinkedList[B]

  // FUNDAMENTAL: you can use named types (e.g., `LinkedList`) the list of type parameters
  def flatMap[B](t: MyTransformer[A, LinkedList[B]]): LinkedList[B] // Results in the concatenation of small sublists

  def filter(p: MyPredicate[A]): LinkedList[A]

  protected def mkString(accumulator: String = ""): String

  override def toString: String = s"[${mkString()}]"
}

/**
 * Nil.
 * <p>
 * Represents an empty cons cell.
 */
final case object Nil extends LinkedList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: LinkedList[Nothing] = throw new NoSuchElementException

  override def last: Nothing = throw new NoSuchElementException

  override def init: LinkedList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](x: B): LinkedList[B] = Cons(x)

  override def concat[B >: Nothing](xs: LinkedList[B]): LinkedList[B] = xs

  override def reverse: LinkedList[Nothing] = this

  override def map[B](t: MyTransformer[Nothing, B]): LinkedList[B] = this

  override def flatMap[B](t: MyTransformer[Nothing, LinkedList[B]]): LinkedList[B] = this

  override def filter(p: MyPredicate[Nothing]): LinkedList[Nothing] = this

  override protected def mkString(accumulator: String): String = ""
}

/**
 * Cons cell.
 *
 * @param head the value stored in the cons cell.
 * @param tail the cons cell following that value.
 */
final case class Cons[+A](override val head: A,
                          override val tail: LinkedList[A] = Nil) extends LinkedList[A] {
  @tailrec
  override def last: A =
    if (tail.isEmpty) head
    else tail.asInstanceOf[Cons[A]].last // `asInstanceOf` permits `@tailrec` usage

  override def init: LinkedList[A] = // TODO: use to use tail recursion for the `init` method
    if (tail.isEmpty) Nil
    else Cons(head, tail.init)

  override def isEmpty: Boolean = false

  override def add[B >: A](x: B): LinkedList[B] = Cons(x, this)

  def concat[B >: A](xs: LinkedList[B]): LinkedList[B] =
    Cons(head, tail.concat(xs)) // BRILLIANT: you recurse until you find an empty tail: then you set that tail to be xs

  override def reverse: LinkedList[A] = Nil.addAll(this)

  override def map[B](t: MyTransformer[A, B]): LinkedList[B] =
  /*if (tail.isEmpty) Cons(t(head)) // ELEMENTAL: this is useless if you define how `Nil` behaves
  else*/ Cons(t(head), tail.map(t))

  override def flatMap[B](t: MyTransformer[A, LinkedList[B]]): LinkedList[B] =
  /*if (tail.isEmpty) t(head)
  else*/ t(head) ++ tail.flatMap(t)

  override def filter(p: MyPredicate[A]): LinkedList[A] =
  /*if (tail.isEmpty) if (p(head)) Cons(head) else Nil
  else*/ if (p(head)) Cons(head, tail.filter(p))
  else tail.filter(p)

  @tailrec
  override protected def mkString(accumulator: String): String =
    if (tail.isEmpty) s"$accumulator$head"
    else tail.asInstanceOf[Cons[A]].mkString(s"$accumulator$head, ")
}

/**
 * Cons companion object.
 */
object Cons {
  // LEARN: in practice, the type parameter of `apply` will always be set implicitly via the arguments
  def apply[A](head: A, tail: LinkedList[A] = Nil) = new Cons(head, tail)
}

/**
 * Predicate on `T`.
 *
 * @tparam T the type to be tested.
 */
trait MyPredicate[-T] {
  def apply(x: T): Boolean
} // `MyPredicate` must be contravariant in the type `T` to solve the variance problem in the `LinkedList`'s definitions

/**
 * Transformer from type `A` to type `B`.
 *
 * @tparam A the type to input.
 * @tparam B the type to output.
 */
trait MyTransformer[-A, B] {
  def apply(x: A): B
} // For the same reason, `MyTransformer` must be contravariant in the type `A`

/**
 * Singly linked list application.
 */
object LinkedListApp extends App {
  val empty: LinkedList[Int] = Nil
  val singleton = Cons(1)
  val couple: LinkedList[Int] = Cons(2, singleton)
  val lost = Cons(4, Cons(8, Cons(15, Cons(16, Cons(23, Cons(42))))))

  println(empty)
  println(singleton)
  println(Cons(127, Cons(0, Cons(0, Cons(1, Nil)))))
  println(couple add 3)
  println
  println(lost)
  println(s"Head: ${lost.head}")
  println(s"Tail: ${lost.tail}")
  println(s"Last: ${lost.last}")
  println(s"Init: ${lost.init}")
  println
  println(lost.addAll(couple add 3))
  println(Nil.reverse.addAll(Nil).add(3).addAll(lost).reverse)
  println(lost.reverse)
  println

  println(Cons(1, Cons(2) ++ Cons(3, Cons(4, Cons(5)))))
  // [1,2] ++ [3,4,5]
  // = Cons(1, [2] ++ [3,4,5])
  // = Cons(1, Cons(2, Nil ++ [3,4,5])
  // = Cons(1, Cons(2, [3,4,5])
  // = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5)))))
  println(lost.append("Yeah!"))

  // Can be heterogeneous
  val fun: LinkedList[Any] = Cons("A", Cons("B", Cons("B"))) ++ Cons(4, Cons(5, Cons(6)))
  println(fun)
  println

  val isEvenP = new MyPredicate[Int] {
    override def apply(x: Int): Boolean = x % 2 == 0
  }

  class StringToIntT extends MyTransformer[String, Int] {
    override def apply(x: String): Int = x.toInt
  }

  // Higher-order functions
  println(Cons(1, Cons(2, Cons(3)))
    .map(new MyTransformer[Int, Int] {
      override def apply(x: Int): Int = x * 5
    }))
  // [1,2,3].map(n * 5)
  // = Cons(5, [2,3].map(n * 5))
  // = Cons(5, Cons(10, [3].map(n * 5)))
  // = Cons(5, Cons(10, Cons(15, Nil.map(n * 5))))
  // = Cons(5, Cons(10, Cons(15)))

  println(Cons(1, Cons(2, Cons(3)))
    .flatMap(new MyTransformer[Int, LinkedList[Int]] {
      override def apply(x: Int): LinkedList[Int] = Cons(x, Cons(x + 1))
    }))
  println(Cons("1", Cons("2", Cons("3", Cons("4", Cons("5", Cons("6", Cons("7", Cons("8"))))))))
    .map(new StringToIntT)
    .filter(isEvenP)
    .map(new MyTransformer[Int, String] {
      override def apply(x: Int): String = s"'$x'"
    }))
  println(Nil
    .map(new StringToIntT)
    .filter(isEvenP)
    .flatMap(new MyTransformer[Int, LinkedList[Any]] {
      override def apply(x: Int): LinkedList[Any] = Cons(x, Cons(s"'$x'"))
    }))

  // Auto implemented `equal` using case classes/objects
  val foo = Cons(1, Cons(2, Cons(3)))
  val bar = Cons(5, Cons(10, Cons(15)))
    .map(new MyTransformer[Int, Int] {
      override def apply(x: Int): Int = x / 5
    })
  println(s"$foo == $bar? ${foo == bar}")
}
