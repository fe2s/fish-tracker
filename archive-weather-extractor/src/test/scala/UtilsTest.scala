import com.github.nscala_time.time.Imports._
import org.scalatest.FunSuite

/**
 * @author Oleksiy_Dyagilev
 */
class UtilsTest extends FunSuite {

  test("dateRange") {
    val today = new DateTime()
    val twoDaysEarlier = today.minusDays(2)
    val range = Utils.dateRange(twoDaysEarlier, today)
    println(s"range = $range")
    assert(range.size === 3)
  }

}
