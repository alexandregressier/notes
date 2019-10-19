package scalatest

import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.mockito.MockitoSugar

class ListWordSpec extends WordSpec with Matchers with MockitoSugar {
  // Enables tests to be written in a BDD style: more descriptive and almost exactly like a specification
  // `Matchers` provides `should` and `shouldBe`
  // When the tests are ran, it keeps the hierarchy of the tests

  "Calling isEmpty" should { // Using what is looking like a DSL
    "return true for a List with 0 elements" in {
      List().isEmpty shouldBe true
    }
    "return false for List with > 0 elements" in {
      List(1, 2, 3).isEmpty shouldBe false
    }
  }

  "Calling size" should {
    "return 3 for a List with 3 elements" in {
      val list = List(1, 2, 3)
      list.size shouldBe 3
    }
  }

  "Calling head" should {
    "throw an exception for an empty List" in {
      intercept[NoSuchElementException] {
        List[Int]().head
      }
    }
  }

  "Mocking a List" should {
    "return trained value for head" in {
      // Create mock
      val mockList = mock[List[Int]]
      when(mockList.head).thenReturn(1)

      mockList.head shouldBe 1
      verify(mockList).head
    }
  }

}
