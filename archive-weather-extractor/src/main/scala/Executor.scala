import com.github.nscala_time.time.Imports._
import model.mongo._
import model.Record
import scala.concurrent.Future
import scala.util.{Success, Try}
import Utils._

/**
 * @author Oleksiy_Dyagilev
 */
class Executor(startDate: DateTime, endDate: DateTime) {

  def start() = {
    for (date <- dateRange(startDate, endDate)) {
      Future {
        val ws = WeatherSource(date)
        val html = new PageLoader().load(ws.url)
        val recordsOpt = new Extractor().extract(html)

        recordsOpt match {
          case None => println(s"No records found for date $date")
          case Some(records) => records foreach handleRecord
        }

        def handleRecord(recordTry:Try[Record]) = recordTry match {
          case Success(r) => new Persistence().save(Weather(date, r))
          case fail => println(s"failed record $fail")
        }
      }
    }
  }


}
