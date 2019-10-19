package scalatest

import org.scalatest.FunSuite

class ListFunSuite extends FunSuite { // A suite of tests in which each test is represented as a function value
  // Enables tests to be written in a JUnit/TestNG style

  test("An empty list should return true for is empty") { // The test name is not the method name
    assert(List().isEmpty)
  }

  test("size should return the correct the number of elements in a list") {
    assert(List(1, 2, 3).size == 3)
  }

  test("Calling head on an empty list should produce NoSuchElementException") {
    intercept([NoSuchElementException]) {
      List.empty.head
    }
  }

}
