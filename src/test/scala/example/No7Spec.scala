package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class No7Spec extends AnyFlatSpec with Matchers {
  "test" should "do sleep and pass" in {
    Thread.sleep(10000)
    true shouldBe true
  }
}
