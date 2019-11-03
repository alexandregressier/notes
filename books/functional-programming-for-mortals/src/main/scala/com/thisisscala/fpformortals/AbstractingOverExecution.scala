package com.thisisscala.fpformortals

import scala.concurrent.Future

object AbstractingOverExecution extends App {

  trait Foo[C[_]] {
    def create(i: Int): C[Int]
  }

  object FooList extends Foo[List] {
    override def create(i: Int): List[Int] = List(i)
  }

  // Trick the compiler w/ a type alias to use a type w/ more than one parameter
  type EitherString[T] = Either[String, T]

  object FooEitherStringNaive extends Foo[EitherString] {
    override def create(i: Int): Either[String, Int] = Right(i)
  }

  // Or use the kind projector compiler plugin
  object FooEitherString extends Foo[Either[String, ?]] {
    override def create(i: Int): Either[String, Int] = Right(i)
  }

  // Ignore the type constructor `C[_]`
  type Id[T] = T

  object FooId extends Foo[Id] {
    override def create(i: Int): Int = i
  }

  // Abstract over the synchronous and asynchronous execution contexts
  trait Terminal[C[_]] {
    def read: C[String]
    def write(t: String): C[Unit]
  }

  type Now[X] = X

  trait TerminalSync extends Terminal[Now] { // `Now` as opposed to `Future`
    override def read: String = ???
    override def write(t: String): Unit = ???
  }

  trait TerminalAsync extends Terminal[Future] {
    override def read: Future[String] = ???
    override def write(t: String): Future[Unit] = ???
  }

  // Define an execution environment
  trait Execution[C[_]] {
    def chain[A, B](c: C[A])(f: A => C[B]): C[B]
    def create[B](b: B): C[B]
  }

  def echo[C[_]](t: Terminal[C], e: Execution[C]): C[String] = // Terminal
    e.chain(t.read) { in: String =>
      e.chain(t.write(in)) { _: Unit =>
        e.create(in)
      }
    }

  // Define the execution monad
  object Execution {
    implicit class Ops[A, C[_]](c: C[A]) {
      def flatMap[B](f: A => C[B])(implicit e: Execution[C]): C[B] =
        e.chain(c)(f)
      def map[B](f: A => B)(implicit e: Execution[C]): C[B] =
        e.chain(c)(f andThen e.create)
    }
  }

  // Using the monad
  def echo[C[_]](implicit t: Terminal[C], e: Execution[C]): C[String] =
    t.read.flatMap { in: String =>
      t.write(in).map { _: Unit =>
        in
      }
    }

  // Using a `for` comprehensino
  def echo[C[_]](implicit t: Terminal[C], e: Execution[C]): C[String] =
    for {
      in <- t.read
       _ <- t.write(in)
    } yield in
}
