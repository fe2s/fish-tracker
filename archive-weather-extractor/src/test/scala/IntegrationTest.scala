import org.scalatest.FunSuite
import model._
import scala.util.Success

/**
 * @author Oleksiy Dyagilev
 */
class IntegrationTest extends FunSuite {

  test("integration") {
    val ws = WeatherSource(2012, 2, 15)
    val loader = new PageLoader()
    val html = loader.load(ws.url)
    val extractor = new Extractor()
    val records = extractor.extract(html)

    val expected = Some(List(
      Success(Record("00:00", "Сплошная облачность", 0, Wind(South, 8.0f), 739, 86)),
      Success(Record("01:30", "Сплошная облачность", -3, Wind(South, 9.0f), 739, 86)),
      Success(Record("02:30", "Сплошная облачность, слабый снег", -5, Wind(South, 10.0f), 738, 80)),
      Success(Record("04:00", "Преимущественно облачно, снег", -7, Wind(South, 9.0f), 738, 84)),
      Success(Record("04:30", "Преимущественно облачно, снег", -7, Wind(South, 7.0f), 738, 84)),
      Success(Record("05:00", "Преимущественно облачно, снег", -7, Wind(South, 7.0f), 738, 84)),
      Success(Record("05:30", "Преимущественно облачно, снег", -7, Wind(South, 6.0f), 738, 84)),
      Success(Record("06:00", "Преимущественно облачно, снег", -7, Wind(South, 6.0f), 738, 84)),
      Success(Record("06:30", "Преимущественно облачно, снег", -7, Wind(South, 6.0f), 739, 84)),
      Success(Record("07:00", "Сплошная облачность, сильный снег", -8, Wind(South, 5.0f), 739, 92)),
      Success(Record("07:30", "Сплошная облачность, слабый снег", -7, Wind(South, 5.0f), 739, 84)),
      Success(Record("08:30", "Преимущественно облачно, слабый снег", -7, Wind(South, 7.0f), 739, 77)),
      Success(Record("09:30", "Преимущественно облачно", -7, Wind(South, 6.0f), 739, 70)),
      Success(Record("11:00", "Преимущественно облачно, слабый снег", -7, Wind(South, 5.0f), 739, 70)),
      Success(Record("11:30", "Преимущественно облачно", -7, Wind(SouthWest, 4.0f), 739, 64)),
      Success(Record("13:00", "Преимущественно облачно", -6, Wind(South, 3.0f), 739, 64)),
      Success(Record("14:00", "Преимущественно облачно", -6, Wind(South, 3.0f), 739, 64)),
      Success(Record("16:00", "Ясно", -7, Wind(South, 3.0f), 739, 64)),
      Success(Record("17:30", "Ясно", -9, Wind(North, 0.0f), 740, 70)),
      Success(Record("19:30", "Ясно", -9, Wind(North, 0.0f), 739, 76)),
      Success(Record("20:30", "Ясно", -10, Wind(North, 0.0f), 739, 84)),
      Success(Record("21:30", "Ясно", -10, Wind(East, 2.0f), 739, 84)),
      Success(Record("22:30", "Ясно", -10, Wind(NorthEast, 1.0f), 739, 84)),
      Success(Record("23:30", "Ясно", -9, Wind(North, 2.0f), 739, 76))
    ))

    assert(records == expected)

    println(records)
  }

}
