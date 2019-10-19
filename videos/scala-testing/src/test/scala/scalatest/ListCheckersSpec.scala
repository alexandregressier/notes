package scalatest

import org.scalatest.FunSuite
import org.scalatestplus.scalacheck.Checkers

class ListCheckersSpec extends FunSuite with Checkers {
  // `Checkers` enables to use ScalaCheck under ScalaTest

  test("Concat of 2 Lists should have a size equal to the sum of sizes") {
    check((a: List[Int], b: List[Int]) => a.size + b.size == (a ::: b).size)
  }

}
