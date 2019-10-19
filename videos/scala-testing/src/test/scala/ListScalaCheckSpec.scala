import org.scalacheck.Properties
import org.scalacheck.Prop.{forAll, BooleanOperators}

object ListScalaCheckSpec extends Properties("List") { // `Properties` has a `main` method!

  property("concat") = forAll { (a: List[Int], b: List[Int]) =>
    a.size + b.size == (a ::: b).size
  }

  property("head") = forAll { a: Int =>
    (a >= 0 && a < 10000) ==> (List(a).head == a) // Generations can be
  }

}
