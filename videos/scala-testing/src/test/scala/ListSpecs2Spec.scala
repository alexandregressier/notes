import org.junit.runner.RunWith
import org.mockito.Mockito
import org.scalatestplus.junit.JUnitRunner
import org.specs2.Specification

@RunWith(classOf[JUnitRunner]) // Make it runnable in IDE w/o installing a plugin
class ListSpecs2Spec extends Specification with Mockito {

  "Calling isEmpty" should {
    "return true for a List with 0 elements" in {
      List().isEmpty must beTrue
    }
    "return false for List with > 0 elements" in {
      List(1, 2, 3).isEmpty must beFalse
    }
  }

  "Calling size" should {
    "return 3 for a List with 3 elements" in {
      val list = List(1, 2, 3)
      list.size must beEqualTo(3)
    }
  }

  "Calling head" should {
    "throw an exception for an empty List" in {
      List[Int]().head must throwA[NoSuchElementException]
    }
  }

  "Mocking a List" should {
    "return trained value for head" in {
      // Create mock
      val mockList = mock[List[Int]]
      mockList.head returns 1

      mockList.head mustEqual 1
      there was one(mockList).head
    }
  }
}
