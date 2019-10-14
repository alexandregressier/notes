package exercices.linkedlist.solution

// This class demonstrates the real power of Scala by combining FP w/ OOP

/**
 * Singly linked list
 */
abstract class MyList {
  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(x: Int): MyList

  def mkString: String // Implementation delegated to the derived classes
  // Could have been `protected`

  // Polymorphic call
  override def toString: String = s"[$mkString]"
  // `override` here b/c already defined in `AnyRef`
  // -> Defines among others `toString`, `equals`, `hashCode`
}

// BRILLIANT:
// LEARN: `Empty` is the perfect use case for an `object`: it only needs a single instance (-> could be `static`)
object Empty extends MyList { // IMPORTANT: `object` can extend classes
  // If you choose `???` (the bottom) to be the implementation of a method, a `NotImplementedError` will be thrown

  // REMEMBER: `head` or `tail` called on an empty list must throw an error
  override def head: Int = throw new NoSuchElementException

  // IMPORTANT: /exceptions/ have the result type `Nothing`
  override def tail: MyList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  // LEARN: think of `object` as static: you do not yield `this` but `Empty`
  override def add(x: Int): MyList = new Cons(x, Empty)
  // You must yield the one of the two `MyList` instances that is not empty: `Cons`

  override def mkString: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h // Shorter names are used to avoid conflicts

  override def tail: MyList = t

  override def isEmpty: Boolean = false

  override def add(x: Int): MyList = new Cons(x, this)

  override def mkString: String =
    if (t.isEmpty) h.toString
    else s"$h ${t.mkString}"
  // FUNDAMENTAL:
  // `mkString` of `t` is inaccessible b/c if you pay close attention we are NOT in the same class defining it:
  // -> `t` is of type MyList (but really is always `Cons` because of the `if` above)
  // It can be casted to its real type `Cons` to:
  // - Qualify this method for recursion
  // - Access the protected/private members of `t` (b/c we are in the body of the class defining it)
  //   -> The `protected` access modifier would be a better fit for `mkString` (in `MyList`)
}

object MyListApp extends App {
  val list: MyList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list) // `toString` call is implicit
  // -> FUNDAMENTAL: called from the parent abstract method and not provided by `AnyRef`
}

// TAKEAWAYS:
// - Grab the opportunity to use recursion in data structures!
// - Do not hesitate to make objects extending an abstract class to define special cases (e.g., `Empty`)
// - Your version of `add` was appending whereas the solution was prepending (easier)
