import org.testng.annotations.{BeforeGroups, Test}

class ListTestNGTest {

  @Test
  def testIsEmpty = {
    // Similar to JUnit
    val list = List()
    assertTrue(list.isEmpty, "list should be empty") // CAUTION: the parameters are inverted when compared to JUnit
  }

  @BeforeGroups(Array("sizeTests")) // Using TestNG, you can group tests together
  def setupSizeTests() {
    println("Setting up for size tests")
  }

  @Test(groups = Array("sizeTests"))
  def testSizeOfEmpty = {
    val list = List()
    assertEquals(0, list.size) // Basic assert
  }

  @Test(groups = Array("sizeTests"))
  def testSizeOfThree = {
    val list = List(1, 2, 3)
    assertThat(list.size, is(equalTo(3))) // Using Hamcrest matchers
  }

}
