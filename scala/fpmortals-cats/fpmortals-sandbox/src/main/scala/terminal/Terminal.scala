package terminal

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.StdIn

// `map`     = unwrap a type, apply a transformation on it, then rewrap
// `flatMap` = unwrap a type, apply a transformation that wraps (and discard that wrap?)
// THE FLATTEN COMES FROM THE LACK OF REWRAP: AND SINCE THE LAMBDA ACTS DIRECTLY ON THE CONTAINED TYPE, IT LOOKS LIKE IT HAS BEEN FLATTENED
// For example, the following does not compile b/c x is not a method of `Int` (the wrap to option is not seen in the lambda, by contrast to flatmap which requires a lambda that wraps)
// Option(1).map(x => Option(x.get()))

// FUNDAMENTAL: `Option` behave strictly like a one item list
// Seq(4, 8, 15, 16, 23, 42).flatMap(x => Option(List(x * 2)))
// But the following does not compile
// Option(1).flatMap(x => List(Option(x)))
// THEREFORE, FLATMAP ON A OPTION DOES NOT SEEM VERY USEFUL BECAUSE ITS LAMBDA MUST RETURN ANOTHER OPTION (as defined by flatMap)

// With a `flatMap` approximately defined as `t(head) ++ tail.flatMap(t)`:
// ELEMENTAL:
// `Option(1) ++ List(2,3)` => `List(1, 2, 3)`
// `Option(1) ++ Option(2)` => `List(1, 2)`
// `Option(1) ++ None`      => `List(1)`

// `flatMap` for `List(1, 2, 3)` and a transformer `val t: Int => Int =  List(_ * 2)`:
// `t(1) ++ t(2) ++ t(3) ++ []`
// `[1 * 2] ++ [2 * 2] ++ [3 * 2] ++ []`
// `[2] ++ [4] ++ [6] ++ []`
// `[2, 4, 6]

// `map` for `List(1, 2, 3)` and a transformer `val t: Int => Int =  List(_ * 2)`:
// `[t(1), t(2), t(3)]`
// `t(1) :: t(2) :: t(3) :: nil`
// `[2] :: [4] :: [6] :: nil`
// `[[2], [4], [6]]

// `flatMap` & `map` applies to `Seq`, `Option`, `Future`...

// Functors applies to kinds
// Each HKT represents a category
// The coolness of Option comes when we think about it as a collection that can hold at most one value and compose on this idea
trait TerminalSyncExplicit {
  def read(): String
  def write(t: String): Unit
}
trait TerminalAsyncExplicit {
  def read(): Future[String]
  def write(t: String): Future[Unit]
}

// Write generic code that prints the user's input sync/asynchronously depending on the runtime implementation
trait Terminal[C[_]] { // `C` means context: in the context of executing `Now` or in the `Future`
  def read: C[String]
  def write(t: String): C[Unit]
}

// Thus we want the HKT C to be either ignored or `Future`
// Types cannot by defined at top-level
// Using backticks, you can more or less give any name to an identifier
object `package` { // FUNDAMENTAL: applies on the current package `terminal` (simulate top-level)
  type Now[X] = X // Same as `Id[T]`
}

// The following is the same thing as writing:
// override def read: Now[String] = ???
// override def write(t: String): Now[Unit] = ???
object TerminalSync extends Terminal[Now] {
  override def read: String = StdIn.readLine
  override def write(t: String): Unit = println(t)
}
class TerminalAsync(implicit ec: ExecutionContext) extends Terminal[Future] {
  override def read: Future[String] = Future { StdIn.readLine }
  override def write(t: String): Future[Unit] = Future { println(t) }
}

// Using Terminal, we know nothing about `C` cannot do anything w/ the `C[String]` of `Terminal`
// We need a kind of execution env that:
// - Let us call a function resulting in `C[T]`
// - And then be able to do something w/ the `T`, including another method on `Terminal`
trait Execution[C[_]] {
  def doAndThen[I, O](c: C[I])(f: I => C[O]): C[O] // Allow to unwrap, then transform to rewrap
  def         wrap[O](o: O)                 : C[O] // Wraps in the HKT (originally called `create`)
}

// The `implicit class` Scala language features enables us to attach some methods to `C` (acts like extension methods):
object Execution {
  implicit class Ops[I, C[_]](c: C[I]) { // We factorize `c`
    def flatMap[O](f: I => C[O])(implicit e: Execution[C]): C[O] = e.doAndThen(c)(f) // Same as `chain`
    def     map[O](f: I => O   )(implicit e: Execution[C]): C[O] = e.doAndThen(c)(f andThen e.wrap) // `e.wrap` method can be composed
  }
  implicit val now: Execution[Now] =
    new Execution[Now] {
      def doAndThen[I, O](c: I)(f: I => O): O = f(c)
      def         wrap[O](o: O)           : O = o
    }
  implicit def future(implicit EC: ExecutionContext): Execution[Future] =
    new Execution[Future] {
      def doAndThen[I, O](c: Future[I])(f: I => Future[O]): Future[O] = c.flatMap(f)
      def         wrap[O](o: O)                            : Future[O] = Future.successful(o)
    }
}

object Runner {
  import Execution.Ops

  import ExecutionContext.Implicits._

  // We can now write a mock implementation of `Terminal[Now]` and use it in our tests w/o any timeouts
  // We can now share the `echo` implementation between synchronous and asynchronous codepaths
  // _echo_ as the reflection of sound waves
  def echoRaw[C[_]](t: Terminal[C], e: Execution[C]): C[String] =
    e.doAndThen(t.read) { in: String =>
      e.doAndThen(t.write(in)) { _: Unit => // `Unit` b/c write produced a future and is passed as an argument to the lambda
        e.wrap(in) // FUNDAMENTAL: this is the value returned by the function
      }
    }
  // - Naming them `map` & `flatMap` enables us to use for comprehensions
  // - Implicit context is inspired from the implicit `ExecutionContext` of `Future`
  def echoVerbose[C[_]](implicit t: Terminal[C], e: Execution[C]): C[String] =
    t.read.flatMap { in: String =>
      t.write(in).map { _: Unit =>
        in
      }
    }
  // for comprehensions are just syntax sugar over nested `flatMap` & `map`
  def echo[C[_]](implicit t: Terminal[C], e: Execution[C]): C[String] =
    for {
      in <- t.read
      _  <- t.write(in)
    } yield in

  implicit val now: Terminal[Now] = TerminalSync
  implicit val future: Terminal[Future] = new TerminalAsync

  def main(args: Array[String]): Unit = {
    println("Write text to echo it back:")
    echo[Now]: Now[String]

    val running: Future[String] = echo[Future]
    Await.result(running, Duration.Inf)
  }
}

// The trait `Execution` has the same signature than Cats's `Monad`:
trait MyMonad[F[_]] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B] // <=> `doAndThen`/`chain`
  def       pure[B](x: B)                  : F[B] // <=> `wrap`/`create` (lift the value `x` into the applicative functor)
}
// - Monad: allows composition of dependent effectful functions
// - flatMap: allows us to have a value in a context `F[A]` and then feed that into a function that takes/ a normal value
//   and returns a value in a context `A => F[B]`
// - pure: lifts any value into the Applicative Functor `F`
//   -> Belongs to the Applicative Functor typeclass

// > import cats.implicits._
// > Applicative[List].pure(10)
// val res1: List[Int] = List(10)
// > Applicative[Option].pure(10)
// val res2: Option[Int] = Some(10)
// Every Applicative is a Functor

// We say that `C` is _monadic_ when there is an implicit `Monad[C]` available (like `Execution[C]`)
// Cats also has the `Id` type alias

// TAKEAWAY: if we write methods that operate on monadic types (like `C`), then we can write sequential code that
// abstracts over its execution context (via a for comprehension)
// Here, we have shown abstraction over sync/async execution but it could be replaced for:
// - More rigorous error handling (`C[_]` = `Either[Error, _]`
// - Managing access to volatile state
// - Performing I/O (remember, phone apps are I/O clients)
// - Auditing of the session
