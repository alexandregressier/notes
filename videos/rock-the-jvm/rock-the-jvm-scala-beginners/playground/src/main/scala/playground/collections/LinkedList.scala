package playground.collections

import scala.annotation.tailrec

/**
 * Immutable linked list.
 *
 * Represents an ordered playground.collections of elements of type `A`.
 *
 * @tparam A the type of the list elements.
 */
@SerialVersionUID(1L)
sealed abstract class LinkedList[+A] extends Serializable {
  def isEmpty: Boolean
  def head: A
  def tail: LinkedList[A]
  def last: A
  def init: LinkedList[A]

  def ::[B >: A] (x: B): ::[B] = playground.collections.::(x, this)
  def :::[B >: A] (x: B): :::[B] = playground.collections.:::(x, this)

  def +:[B >: A] (x: B): NonEmptyLinkedList[B] = x :: this
  def :+[B >: A] (x: B): NonEmptyLinkedList[B] = playground.collections.::(x, this)
  def ++:[B >: A] (xs: LinkedList[B]): LinkedList[B]
  def ++[B >: A] (xs: LinkedList[B]): LinkedList[B]

  def reverse: LinkedList[A] = this ++: Nil

  def join(sep: String = ", "): String
  override def toString = s"[${join()}]"
}

/**
 * The empty linked list.
 */
@SerialVersionUID(1L)
case object Nil extends LinkedList[Nothing] {
  override def isEmpty: Boolean = true
  override def head: Nothing = throw new NoSuchElementException("Empty list has no head")
  override def tail: LinkedList[Nothing] = throw new UnsupportedOperationException("Empty list has no tail")
  override def last: Nothing = throw new NoSuchElementException("Empty list has no last")
  override def init: LinkedList[Nothing] = throw new UnsupportedOperationException("Empty list has no init")

  override def ++:[B >: Nothing](xs: LinkedList[B]): LinkedList[B] = xs
  override def ++[B >: Nothing](xs: LinkedList[B]): LinkedList[B] = xs

  override def join(sep: String): String = ""
}

/**
 * Non-empty linked list.
 *
 * @tparam B the type of the list elements.
 */
@SerialVersionUID(1L)
sealed trait NonEmptyLinkedList[B] extends LinkedList[B] {
  override def isEmpty: Boolean = false
}

/**
 * Non-empty linked list.
 *
 * @param head the first element of the list.
 * @param tail the linked list containing the remaining elements of this list after the first one.
 * @tparam B the type of the list elements.
 */
@SerialVersionUID(1L)
final case class ::[B] (override val head: B,
                        override val tail: LinkedList[B] = Nil) extends NonEmptyLinkedList[B] {
  override def last: B = tail match {
    case Nil => head
    case _ :: _ => tail.last
  }

  override def init: LinkedList[B] = tail match {
    case Nil => Nil
    case _ :: _ => head :: tail.init
  }

  override def ++:[C >: B](xs: LinkedList[C]): LinkedList[C] = xs match {
    case Nil => this
    case x :: xs => xs ++: (x :: this)
  }

  override def ++[C >: B](xs: LinkedList[C]): LinkedList[C] =
    head :: tail ++ xs

  override def join(sep: String): String =
    if (tail.isEmpty) head.toString
    else s"$head$sep${tail.join(sep)}"
}

/**
 * Non-empty linked list.
 *
 * This concrete class uses tail recursion in the implementation of most methods.
 *
 * @param head the first element of the list.
 * @param tail the linked list containing the remaining elements of this list after the first one.
 * @tparam B the type of the list elements.
 */
@SerialVersionUID(1L)
final case class :::[B] (override val head: B,
                        override val tail: LinkedList[B] = Nil) extends NonEmptyLinkedList[B] {
  @tailrec
  override def last: B = tail match {
    case Nil => head
    case tail: :::[B] => tail.last
  }

  override def init: LinkedList[B] =
      if (tail.isEmpty) Nil
      else head :: tail.init

  @tailrec
  override def ++:[C >: B](xs: LinkedList[C]): LinkedList[C] =
    if (xs.tail.isEmpty) xs.head :: this
    else xs.tail ++: (xs.head :: this).asInstanceOf[:::[B]]

  override def ++[C >: B](xs: LinkedList[C]): LinkedList[C] =
    head :: tail ++ xs
  
  override def join(sep: String): String = join(sep, "")

  @tailrec
  private def join(sep: String, acc: String): String = tail match {
    case Nil => s"$acc$head"
    case tail: :::[B] => tail.join(sep, s"$acc$head$sep")
  }
}

object LinkedList {
  def apply[A] (xs: A*): LinkedList[A] = Nil
}

object Main extends App {
  println(("b" :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: "c" :: Nil) ++: ("a" :: -2 :: -3 :: Nil))
  println(("b" :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: "c" :: Nil).init)
}
