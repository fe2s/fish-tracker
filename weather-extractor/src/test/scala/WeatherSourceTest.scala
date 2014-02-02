import org.scalatest.FunSuite
import com.github.nscala_time.time.Imports._


/**
 * @author Oleksiy Dyagilev
 */
class WeatherSourceTest extends FunSuite {

  test("url") {
    val s = WeatherSource(2012, 2, 15)
    assert(s.url === "http://meteo.ua/archive/150/harkov/2012-2-15")
    println(WeatherSource(DateTime.now))
  }

}
