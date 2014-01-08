import model.mongo.Weather
import scala.concurrent._
import com.github.nscala_time.time.Imports._
import com.mongodb.casbah.Imports._



object Main extends App {

  val today = new DateTime()
  val earlier = today.minusDays(2)

  val ws = WeatherSource(earlier)
  val loader = new PageLoader()
  val html = loader.load(ws.url)
  val extractor = new Extractor()
  val records = extractor.extract(html)
//  println(s"records = $records")

  val record = records.get.head.get
  val weather = Weather(earlier, record)
  println(s"record = $record")

  val persistence = new Persistence()
  persistence.save(weather)


}
