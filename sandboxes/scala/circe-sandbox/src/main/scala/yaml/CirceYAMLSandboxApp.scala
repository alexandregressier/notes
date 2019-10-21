package yaml

import cats.syntax.either._
import io.circe.generic.auto._
import io.circe.{yaml, _}

case class Nested(one: String, two: BigDecimal)
case class Foo(foo: String, bar: Nested, baz: List[String])

/**
 * circe-yaml sandbox application.
 */
object CirceYAMLSandboxApp extends App {
  val json = yaml.parser.parse(
    """
      |foo: Hello, World
      |bar:
      |    one: One Third
      |    two: 33.333333
      |baz:
      |    - Hello
      |    - World
      |""".stripMargin)

  val foo = json
    .leftMap(err => err: Error)
    .flatMap(_.as[Foo])
    .valueOr(throw _)

  println(foo)
}
