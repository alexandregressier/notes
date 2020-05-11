package _2_forcomprehensions

import cats.data.OptionT

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import cats.implicits._
import mouse.all._

object `package` {
  // ELEMENTAL: for comprehensions is the ideal FP abstraction for **sequential** programs that interact w/ the world
  // Sequential: that happens in sequence
  // Reify: make (something abstract) more concrete or real

  val a, b, c = Option(1)
  // val a: Option[Int] = Some(1)
  // val b: Option[Int] = Some(1)
  // val c: Option[Int] = Some(1)

  // SCALA: `()` of the `yield` are optional

  // > import scala.reflect.runtime.universe._
  // > show { reify {
  for { i <- a; j <- b; k <- c } yield (i + j + k) // Single line
  // } }
  //  val res1: String = Expr[Option[Int]](
  //    a.flatMap(((i) =>
  //      b.flatMap(((j) =>
  //        c.map(((k) => i.$plus(j).$plus(k))))))))
  // Reified:
  a.flatMap { i =>
    b.flatMap { j =>
      c.map { k =>
        i + j + k }}}

  // RULE OF THUMB: every `<-` (called a _generator_) is a nested flatMap call w/ the last generate a map containing the
  // `yield` body


  // `val` doesn't have to assign to a single value, it can be anything that works as a cas pattern match
  val (first, second) = ("hello", "world")
  val list = List(1, 2, 3)
  // first: String = "hello", second: String = "world"
  val x :: xs = list
  // x: Int = 1, xs: List[Int] = List(2, 3)

  // The same is true for assignment in for comprehensions
  val maybe = Option(("hello", "world"))
  for {
    entry <- maybe
    (first, _) = entry
  } yield first
  // res: Some(hello)

  // Be careful to not to miss any cases or there will be a RUNTIME exception (a totality failure)
  val y :: ys = List()
  // If run: `caught: scala.MatchError: List()` b/c the nil case is not handled


  // You can assign value inline w/o a `val` keyword
  // -> IMPORTANT: this means that you can yield two values out of a single `flatMap`
  for {
    // #1
    i <- a
    // #2
    j <- b
    ij = i + j // `ij` is flat-mapped along w/ the `j`
    // #3
    k <- c
  } yield ij + k
  // Reified:
  a.flatMap { i =>
 // b.flatMap { j =>
    b.map { j => (j, i + j) }.flatMap { case (j, ij) => // LEARN THIS: YIELDING MULTIPLE VALUES
      c.map { k =>
        ij + k }}}

  // Value assignments cannot take place before any generators:
  for {
    //initial = 1 // Won't compile
    i <- a
  } yield i //+ initial

  // Workarounds:
  // (1) Use a val
  val initial = 1
  for {
    i <- a
  } yield i + initial

  // (2) Create an Option out of the initial assignment
  for {
    initial <- Option(1) // Not using `=`
    i <- a
  } yield initial + i


  // It is possible to put `if` statements after a generator to _filter_ values by a predicate:
  for {
    i <- a
    j <- b
    ij = i + j
    k <- c
  } yield i + j + k
  // Reified:
  a.flatMap { i =>
    // b.flatMap { j =>
    b.withFilter { j => i > j }.flatMap { j =>
      c.map { k =>
        i + j + k }}}

  // Older versions of Scala used `filter`, but `Traversable.filter` creates new collections for every predicate, so
  // `withFilter` was introduced as the more performant alternative

  // Combine assignment with filtering
  for {
    // #1
    i <- a
    // #2
    j <- b
    ij = i + j
    // #3
    if i > j
    // #4
    k <- c
  } yield ij + k
  // a.flatMap { (i =>
  //   b.map { j => {
  //     val ij = i + j
  //     Tuple2.apply(j, ij)
  //   }}.withFilter { (x$1 => x$1: @unchecked match { case Tuple2((j@_), (ij@_)) => i > j }
  //   }).flatMap { (x$2 => x$2: @unchecked match { case Tuple2((j@_), (ij@_)) =>
  //     c.map(((k) => ij + k)) }}}

  for {
    // #1
    i <- a
    // #2
    j <- b
    // #3
    if i > j
    ij = i + j
    // #4
    k <- c
  } yield ij + k
  // a.flatMap { (i =>
  //   b.withFilter(j => i > j)).map(j => {
  //     val ij = i + j
  //     Tuple2.apply(j, ij)
  //   }).flatMap { (x$1 => x$1: @unchecked match { case Tuple2((j@_), (ij@_)) =>
  //     c.map(k => ij + k)}}}

  // We can accidentally trigger a `withFilter` by providing type information, interpreted as a pattern match:
  for {
    i: Int <- a
  } yield i
  // Reified:
  a.withFilter {
    case i: Int => true
    case _      => false
  }.map { case i: Int => i}
  // Like assignments, a generator can use a pattern match on LHS (in the case of `if`)
  // -> But unlike assignments which throws `MatchError` on failure, generators are _filtered_ & will not fail at runtime
  // IMPORTANT: there is an inefficient double application of the pattern


  // WARNING: inline `Future` calculations in a for comprehension do not run in parallel:
  def expensiveCalc() = 42
  def anotherExpensiveCalc() = 42
  for {
    i <- Future { expensiveCalc() }
    j <- Future { anotherExpensiveCalc() }
  } yield i + j

  // To make them start in parallel, start them outside the for comprehension
  val f1 = Future { expensiveCalc() }
  val f2 = Future { anotherExpensiveCalc() }
  for { i <- f1; j <- f2 } yield i + j
  // FUNDAMENTAL: for comprehensions are fundamentally for defining SEQUENTIAL PROGRAMS
  // We will see a far superior way of defining parallel computations in a later chapter (no `Future` involved

  // If there is no `yield`, the compiler uses `foreach` instead of `flatMap`, which is only useful for its side-effects
  for {
    i <- a
    j <- b
  } println(s"$i $j")
  // Reified:
  a.foreach { i =>
    b.foreach { j =>
      println(s"$i $j")
    }
  }

  // The full set of methods supported by for comprehensions do not share a common super type
  // But if it was a trait:
  trait ForComprehensible[C[_]] { // Still the context `C` (is a context a category?)
    def map[I, O](f: I => O): C[O]
    def flatMap[I, O](f: I => C[O]): C[O]
    def withFilter[I](f: I => Boolean): C[I] // We do not change the type here
    def foreach[I](f: I => Unit): Unit
  }
  // IMPORTANT: if the context `C[_]` of a for comprehension doesn't provide its own `map` & `flatMap` (-> not usable
  // in the for comprehension), all is not lost
  // If an implicit `cats.FlatMap[T]` is available for `T`, it will provide its own `map` & `flatMap`


  // Do not make function w/ parameters as Option[T] (which makes lying signatures):
  def namedThingsBad(someName: Option[String], someNumber: Option[Int]): Option[String] = for {
    name   <- someName
    number <- someNumber
  } yield s"$number ${name}s"
  // Clunky, verbose, and bad style: if a function requires every input then it should make this requirement explicit,
  // pushing the responsibility of dealing with optional parameters to its caller
  def namedThingsBad(name: String, number: Int): String = s"$number ${name}s"
  // (Could be implement with the `Option` result type)

  // Consider what happens when the `for` context decides that it cannot proceed any further
  // Ex: it met a `None` which is not further flat-mapped
  for {
    i <- a
    j <- b
    k <- c
  } yield i + j + k
  // IMPORTANT: if any of a, b, c are `None`, the comprehension short-circuits w/ `None` but it doesn't tell us what went wrong

  // Using `Either`, then a `Left` will cause the `for` comprehension to short circuit w/ extra information, much better than `Option` for error reporting:
  val foo = Right(1)
  val bar = Right(2) // IMPORTANT: inferred type is `Right[Nothing, Int]` but it is not an issue
  val baz: Either[String, Int] = Left("Sorry, no `baz`") // IMPORTANT: type inference is required here (why?)
  for {
    i <- foo
    j <- bar
    k <- baz
  } yield i + j + k // => `Left(Sorry, no `baz`)`

  // W/ a `Future` that fails
  val f = for {
    i <- Future.failed[Int](new Throwable)
    j <- Future { println("hello"); 1 }
  } yield i + j
  Await.result(f, Duration.Inf) // Caught `java.lang.Throwable`
  // Since here `Future`s are executed in sequence, b/c the first `Future` short circuits

  // "Short circuiting for the unhappy path" -> a common & important theme
  // _Happy path_ VS _Unhappy path_
  // `for` comprehensions cannot express resource cleanup (-> no `try`/`finally`)
  // -> Good b/c in FP it puts a clear ownership of responsibility for unexpected error recovery & resource cleanup onto
  // the **context** (usually a Monad), not the business logic


  // Gymnastics

  // Fallback Logic:
  // We are calling a method that result in an Option, but we want to fallback on another method if it is `None`
  // Note: you can prefix those methods w/ `get` since there are no getter/setter
  def getFromRedis(s: String): Future[Option[String]] = ???
  def getFromDb(s: String): Future[Option[String]] = ??? // The last resort: if it returns `None` we return an error

  val key: String = ???
  //getFromRedis(key) orElse getFromDb(key)

  // WARNING: the following would run both queries:
  for {
    cache <- getFromRedis(key)
    sql   <- getFromDb(key)
  } yield cache orElse sql // SCALA: `orElse` returns an `Option` in any case

  // FUNDAMENTAL: since we flatten all the time w/ all the `flatMap`, it looks like the context `C` (here future) is
  // absent inside the for
  for {
    cache <- getFromRedis(key)
    res <- cache match {
      case Some(_) => Future.successful(cache)// Make sure to not return `cache` directly, which is not a `Future` but an `Option`
      case None    => getFromDb(key)
    }
  } yield res // Same as before: `res` is an `Option`

  // Early exit
  // Say we have some condition that should exit early w/ a succesful value
  // If we want to exit early w/ an error, it is std OOP practice to throw an exception

  // Scala: `require` throws an `IllegalArgumentException` if its condition is not met
  // Synchronous version w/o early exit:
  {
    def getN: Int = 1

    val n = getN
    require(n > 0, s"$n must be positive")
    n * 10
  }
  // Asynchronous version w/o early exit:
    def getN: Future[Int] = Future.successful(1)
    def error(msg: String): Future[Nothing] = Future.failed(new RuntimeException)

    for {
      n <- getN
      posN <- if (n <= 0) error(s"$n must be positive")
              else Future.successful(n)
    } yield posN * 1000

  // Synchronous version w/ early exit:
  {
    def getN: Int = 1
    def getM: Int = 1

    val n = getN
    if (n <= 0) 0
    else n * getM
  }
  // Asynchronous version w/o early exit (translates into nested for comprehensions):
  def getM: Future[Int] = Future.successful(2)
  // ELEMENTAL: THE FOR DOES RETURN THE VALUE IN CONTEXT (HERE `FUTURE`) B/C IT IS A MAP AT THE END
  // IT ALSO MEANS THAT THE YIELDED EXPRESSION IS NOT IN CONTEXT
  // THEREFORE, IF AN EARLY EXIT MUST HAPPEN, IT MUST BE WRAPPED IN THE CONTEXT B/C IT IS A FLATMAP
  for {
    n <- getN
    posN <- if (n <= 0) Future.successful(0)
            else for { m <- getM } yield n * m
  } yield posN * 100

  // INTELLIJ TIP: `A-=` show the result type of current expression

  // REMEMBER: if there is an implicit `Monad[T]` for `T[_]` (-> `T` is therefore monadic), `pure` enables us to
  // create/lift a `T[A]` from a value `a: A`

  // Cats provides `Monad[Future]`, & `.pure[Future]` calls `Future.successful` behind the scenes
  // -> It is a general concept that works beyond `Future` and is therefore recommended
  for {
    n <- getN
    posN <- if (n <= 0) 0.pure[Future]
            else for { m <- getM } yield n * m
  } yield posN * 100


  // Incomprehensible

  // FUNDAMENTAL: the context we are comprehending over must stay the same: we cannot mix contexts
  // -> Nothing can help us b/c the meaning is not well defined
  // REMEMBER: since we flatten all the time w/ all the `flatMap`, it looks like the context `C` (e.g., future) is
  // absent inside the for
  def option: Option[Int] = ???
  def future: Future[Int] = ???
  for {
    a <- option
    //b <- future // Compile error: type mismatch
  } yield a //* b // The `yield` that we want to achieve

  // We can try to have nested contexts but we have to unnest/flatten twice
  def findA: Future[Option[Int]] = ???
  def findB: Future[Option[Int]] = ???
  for {
    a <- findA
    b <- findB
  } yield a //* b // Error: `*` has no meaning for Option[T]

  // We want `for` to take care of the outer context & let us write our code on the inner `Option`
  // LEARN: hiding the outer context is exactly what a _monad transformer_ does
  // - A monad transformer gather **two** nested context called the _outer_ & _inner context_
  // - It unnest 2 contexts by putting the inner context one aside the type of the inner value
  // -> Ex: `Future[Option[_]]` into `OptionT[Future, _]` (note the inversion)
  // Cats provides `OptionT` (Option Transformer) & `EitherT` (Either Transformer) for their respective analog types
  // WARNING: for ex, use `OptionT` when `Option` is the nested type

  // FUNDAMENTAL: the outer context can be anything that normally works in a for comprehension
  // -> But it needs to stay the same throughout (-> no mix)

  // We change the context of the `for` from `Future[Option[_]]` to `OptionT[Future, _]`
  val result1 = for {
    a <- OptionT(findA)
    b <- OptionT(findB)
  } yield a * b // Success!
  // `:t result1` => `OptionT[Future, _]`

  // `result1.value` returns the original context `Future[Option[_]]`
  val t1: OptionT[Future, Int] = result1
  val t2: Future[Option[Int]] = result1.value

  // Conversions
  // If you want to use a method resulting only in the topmost context (here `Future`), you can use `liftF` to integrate
  // it in the current context
  def findC: Future[Int] = ???
  def findD: Option[Int] = ???
  // Q: what is the `liftM` mentioned by the author?

  // Enable us to write a single comprehension w/ multiple contexts grouped into one
  val result2 = for {
    a <- OptionT(findA)
    b <- OptionT(findB)
    c <- OptionT.liftF(findC) // F stands for Functor
    d <- OptionT(findD.pure[Future]) // We transform the result type into the original context and monad transform it after
    e <- OptionT(Option(10).pure[Future])
  } yield (a * b) / (c * d) * e

  // Let's build up a DSL to that handles all the required conversions into `OptionT[Future, _]`
  def liftFutureOption[A](fut: Future[Option[A]]): OptionT[Future, A] = OptionT(fut)
  def liftFuture      [A](fut: Future[A])        : OptionT[Future, A] = OptionT.liftF(fut)
  def liftOption      [A](opt: Option[A])        : OptionT[Future, A] = OptionT(opt.pure[Future])
  def lift            [A](a: A)                  : OptionT[Future, A] = liftOption(Option(a))

  // _Thrush_: a bird (_le muguet_)

  // `|>` is informally called the _thrush operator_ (a term also used in Clojure) b/c it resembles to a bird
  // - `|>` comes from Typelevel's Mouse extensions to Cats
  // - Applies the function on the RHS to the value on the LHS -> Visually separate the logic from the transformers
  // - You can use `.thrush` if you do not like symbolic operators (like most of the symbols in Scala)

  val result3 = for {
    a <- findA |> liftFutureOption
    b <- findB |> liftFutureOption
    c <- findC |> liftFuture
    d <- findD |> liftOption
    e <- 10    |> lift
  } yield (a * b) / (c * d) * e

  // REMEMBER: this is still bad for parallelization b/c `Future`s run in sequence

  // This approach also works for `Either`... as the inner context, but their lifting methods are more complex & require
  // more parameters
}
