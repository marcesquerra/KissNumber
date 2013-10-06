package bryghts.tests

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import com.bryghts.kissnumber._

@RunWith(classOf[JUnitRunner])
class KissnumberSpecs extends Specification
{

	"KissNumber equality" should {

		"be true for IntegerNumber(2) and IntegerNumber(2)" in {
			IntegerNumber(2) mustEqual IntegerNumber(2)
		}

		"be true for IntegerNumber(2) and RealNumber(2)" in {
			IntegerNumber(2) mustEqual RealNumber(2)
		}

		"be true for RealNumber(2) and IntegerNumber(2)" in {
			RealNumber(2) mustEqual IntegerNumber(2)
		}

		"be true for RealNumber(2) and RealNumber(2)" in {
			RealNumber(2) mustEqual RealNumber(2)
		}

		"be true for IntegerNumber(2) and 2" in {
			IntegerNumber(2) mustEqual 2
		}

		"be true for RealNumber(2) and 2" in {
			RealNumber(2) mustEqual 2
		}



		"be false for IntegerNumber(2) and IntegerNumber(3)" in {
			IntegerNumber(2) mustNotEqual IntegerNumber(3)
		}

		"be false for IntegerNumber(2) and RealNumber(3)" in {
			IntegerNumber(2) mustNotEqual RealNumber(3)
		}

		"be false for RealNumber(2) and IntegerNumber(3)" in {
			RealNumber(2) mustNotEqual IntegerNumber(3)
		}

		"be false for RealNumber(2) and RealNumber(3)" in {
			RealNumber(2) mustNotEqual RealNumber(3)
		}

		"be false for IntegerNumber(2) and 3" in {
			IntegerNumber(2) mustNotEqual 3
		}

		"be false for RealNumber(2) and 3" in {
			RealNumber(2) mustNotEqual 3
		}



		"be true for RealNumber(2.2) and RealNumber(2.2)" in {
			RealNumber(2.2) mustEqual RealNumber(2.2)
		}

		"be true for RealNumber(2.2) and 2.2" in {
			RealNumber(2.2) mustEqual 2.2
		}



		"be false for RealNumber(2.2) and RealNumber(3.2)" in {
			RealNumber(2.2) mustNotEqual RealNumber(3.2)
		}

		"be false for RealNumber(2.2) and 3.2" in {
			RealNumber(2.2) mustNotEqual 3.2
		}

	}

}