package dev.gressier

import scala.concurrent._

object Main extends App {
  // ZIO:
  // - Is a huge library
  // - Was written in one year

  // To be useful, a program has to do I/O (i.e., side effects)
  // - E.g., printing to the screen
  //   To be efficient, a program should probably use asynchronicity
  //   To be fast, a program probably need to use concurrency


  // -> Scala's answer: `scala.concurrent.Future[T]`
  // - A data type that represents a running computation that will end at some point end w/ either:
  //   - A result with type `T`
  // - Or an exception/failure

  implicit val ec: ExecutionContext = ExecutionContext.global

  // `Future` does not block your thread:
  val background = Future { // This `Future` will never be executed if the programs ends before `millis`
    Thread.sleep(8000)
    println("Done sleeping")
  }
  println("I am not blocked")

  def cpuBound(n: Int): Int = {
    val rs = n * 1000
    Thread.sleep(rs)
    rs
  }

  // `Future` lets you do things concurrently
  val firstComputation: Future[Int] = Future(cpuBound(5))
  val secondComputation: Future[Int] = Future(cpuBound(10))

  val zipped: Future[(Int, Int)] = firstComputation zip secondComputation // 2 cores are used concurrently

  println(zipped) // Prints `Future(Success((5000,10000)))` after 10,000 milliseconds

  // The problems with `Future`:
  // 1. It is eager
  // Consider a DB request, and a fallback;
  // def runRequests(userId: String): Future[Option[User]] = {
  //   val mainRequest: Future[Option[User]] = getUser(mainDb, userId)
  //   val fallbackRequest: Future[Option[User]] = getUser(fallbackDb, userId)

  //   // The `fallback` request is submitted even if `mainRequest` might succeed
  //   mainRequest.recoverWith {
  //     case e =>
  //       log.warn("Main DB failed. Falling back to secondary")
  //       fallbackRequest
  //   }
  // }

  // IMPORTANT: you have to reason about `Future`s using `val`, `lazy val`, or `def`

  // 2. No cancellation
  // def runRequests(userId: String): Future[Option[User]] =
  //   Future.firstCompletedOf( // IMPORTANT: `firstCompletedOf` is called a /combinator/
  //     List(
  //       getUser(firstReplicaDb, userId),
  //       getUser(secondReplicaDb, userId))) // Yields the first `Future` that succeeded

  // "If one request succeeds, I except the others to be cancelled"


  // ZIO is data type with 3 type parameters:
  // ZIO[R,E,A]
  // - `R`: environment type - what environment does this ZIO need to work
  // - `E`: error type - how this ZIO might fail
  // - `A`: value type - the type of the value that this ZIO will yield

  // IN A NUTSHELL:
  // A program with the type `ZIO[R,E,A]` is a description (i.e., it does not run) of a program that requires an
  // environment of type `R` to run, and when it will run, it may fail with an `E` or yield a value of type `A`

  // More concretely:
  // val program: ZIO[Config, AppError, String]
  // - A *lazy* `Future` that does not necessarily fail with `Throwable` (you may define your own exception)
  // - A purely functional *description* for asynchronous, concurrent programs
  // - Something that is kind-of like `Config => Either[AppError, String]`
  //   (but computing this may require additional effects (e.g., threads, cores)
}
