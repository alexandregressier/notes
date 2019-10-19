import org.junit.Test

class ListJUnitTest {

  @Test // This denotes a test that can be run
  def testIsEmpty = { // The method name is the test name
    val list = List()
    assertTrue("list should be empty", list.isEmpty) // The string will be shown if the test fails
  }

  @Test
  def testSize = {
    val list = List()
    assertEquals(3, list.size) // Using basic assert
    // LEARN: first argument is the /expected value/ and the second the /actual value/
    assertThat(list.size, is(equalTo(3))) // Using Hamcrest matchers
    // Reads more like a specification (and better)
  }

  @Test(expected = classOf[NoSuchElementException])
  def testEmptyHead() { // FUNDAMENTAL: no `=` is required here
    List[Int]().head
  }

  @Test
  def testMock() {
    // Create and train mock
    val mockList = mock(classOf[List[Int]])
    when(mockList.head).thenReturn(1)

    assertThat(mockList.head, is(1)) // Hamcrest matcher for checking test value
    verify(mockList).head // Verify that this method is mocked
  }

}
