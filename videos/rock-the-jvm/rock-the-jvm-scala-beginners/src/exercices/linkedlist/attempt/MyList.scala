package exercices.linkedlist.attempt

import scala.annotation.tailrec

// REMEMBER: `head` or `tail` called on an empty list must throw an error
abstract class AbstractList {
  def head: Int

  def tail: AbstractList

  def isEmpty: Boolean

  def add(x: Int): AbstractList

  def toString: String
}

object AbstractList {
  val empty: Int = -1
}

// IMPORTANT: `final` on derived classes is an underused practice
final class Cons(override val head: Int = AbstractList.empty,
                 override val tail: Cons = null) extends AbstractList {
  // ESSENTIAL: a member variable can override a parameterless method (by having the same name):
  // |     | W/ ()                     | W/o ()                    |
  // |-----+---------------------------+---------------------------|
  // |     |                           | - Runs at construction    |
  // | val | n/a                       | - Override w/ val         |
  // |     |                           | - Access w/ no ()         |
  // |-----+---------------------------+---------------------------|
  // |     | - Runs at at every access | - Runs at at every access |
  // | def | - Override w/ val or def  | - Override w/ val or def  |
  // |     | - Access w/ () or w/o ()  | - Access w/ no ()         |

  // See https://blog.jessitron.com/2012/07/10/choices-with-def-and-val-in-scala/ for more details

  override def isEmpty: Boolean = this.tail == null

  //@tailrec // IMPORTANT: `@tailrec` is to be placed only on the implementation
  override def add(x: Int): Cons =
    if (isEmpty) Cons(head, Cons(x))
    else Cons(head, tail.add(x))
  // REMEMBER: we are in FP, we cannot mutate the inner state of the last node
  // -> You must construct a new list while reading the old one

  // LEARN: when you feel the need to have an additional parameter that has the same type that the current class;
  // -> Just use `this`

  override def toString: String = s"[${mkString()}]"// `toString` is a special method name
  // LEARN: `()` are required to call a method w/ all its parameters set to their default values

  // def mkString(sep: Char = ','): String = {
  //   if (isEmpty) head.toString
  //   else s"$head$sep ${tail.mkString(sep)}"
  // }

  @tailrec
  def mkString(sep: Char = ',', acc: String = ""): String = {
    if (isEmpty) acc + head
    else tail.mkString(sep, s"$acc$head$sep ")
  }
  // FUNDAMENTAL: since it is tail recursion, the recursion happens at the tail/end (???)
  // -> Thus you must use an accumulator to move the recursion result at the elsewhere (e.g., at beginning)
}

object Cons { // No `new` keyword needed
  def apply(x: Int = AbstractList.empty, xs: Cons = null): Cons = new Cons(x, xs)
}

object MyListApp extends App {
  println(Cons())
  println(Cons(11))
  println(Cons(11, Cons(22)))
  println(Cons(4, Cons(8, Cons(15, Cons(16, Cons(23, Cons(42)))))))
  println(Cons(0) add 1 add 2 add 3 add 4 add 5)
}
