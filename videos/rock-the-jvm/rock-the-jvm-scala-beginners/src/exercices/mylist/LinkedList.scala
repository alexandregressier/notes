package exercices.mylist

import scala.annotation.tailrec

/**
 * Singly linked list.
 */
abstract class LinkedList {
  def head: Int

  def tail: LinkedList

  def last: Int

  def init: LinkedList

  def isEmpty: Boolean

  def add(x: Int): LinkedList

  def append(x: Int): LinkedList = appendAll(Cons(x))

  @tailrec
  final def addAll(xs: LinkedList): LinkedList =
    if (xs.isEmpty) this
    else add(xs.head).addAll(xs.tail)

  final def appendAll(xs: LinkedList): LinkedList = // TODO: use to use tail recursion for the `appendAll` method
    if (isEmpty) xs
    else Cons(head, tail.appendAll(xs))

  final def +(xs: LinkedList): LinkedList = appendAll(xs)

  def reverse: LinkedList

  protected def mkString(accumulator: String = ""): String

  override def toString: String = s"[${mkString()}]"
}

/**
 * Nil.
 * <p>
 * Represents an empty cons cell.
 */
object Nil extends LinkedList {
  override def head: Int = throw new NoSuchElementException

  override def tail: LinkedList = throw new NoSuchElementException

  override def last: Int = throw new NoSuchElementException

  override def init: LinkedList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(x: Int): LinkedList = Cons(x)

  override def reverse: LinkedList = this

  override protected def mkString(accumulator: String): String = ""
}

/**
 * Cons cell.
 *
 * @param head the value stored in the cons cell.
 * @param tail the cons cell following that value.
 */
final class Cons(override val head: Int,
                 override val tail: LinkedList = Nil) extends LinkedList {
  @tailrec
  override def last: Int =
    if (tail.isEmpty) head
    else tail.asInstanceOf[Cons].last // `asInstanceOf` permits `@tailrec` usage

  override def init: LinkedList = // TODO: use to use tail recursion for the `init` method
    if (tail.isEmpty) Nil
    else Cons(head, tail.asInstanceOf[Cons].init)

  override def isEmpty: Boolean = false

  override def add(x: Int): LinkedList = Cons(x, this)

  override def reverse: LinkedList = Nil.addAll(this)

  @tailrec
  override protected def mkString(accumulator: String): String =
    if (tail.isEmpty) s"$accumulator$head"
    else tail.asInstanceOf[Cons].mkString(s"$accumulator$head, ")
}

/**
 * Cons companion object.
 */
object Cons {
  def apply(head: Int, tail: LinkedList = Nil) = new Cons(head, tail)
}

/**
 * Singly linked list application.
 */
object LinkedListApp extends App {
  val empty: LinkedList = Nil
  val singleton: LinkedList = Cons(1)
  val couple: LinkedList = Cons(2, singleton)
  val lost = Cons(4, Cons(8, Cons(15, Cons(16, Cons(23, Cons(42))))))

  println(empty)
  println(singleton)
  println(Cons(127, Cons(0, Cons(0, Cons(1)))))
  println(couple add 3)
  println(lost)
  println
  println(lost.head)
  println(lost.tail)
  println(lost.last)
  println(lost.init)
  println
  println(lost.addAll(couple add 3))
  println(Nil.reverse.addAll(Nil).add(3).addAll(lost).reverse)
  println(lost.reverse)
  println
  println(lost.appendAll(lost))
  println(lost.append(57))
  println(Cons(1, Cons(2, Cons(3))) + Cons(4, Cons(5, Cons(6))))
}
