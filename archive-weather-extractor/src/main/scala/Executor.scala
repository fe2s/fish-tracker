import com.github.nscala_time.time.Imports._
import java.util.concurrent.Executors
import model.mongo._
import model.Record
import scala.concurrent.{ExecutionContext, Await, Future}
import scala.util.{Success, Try}
import Utils._
import scala.concurrent.duration.{Duration => Dur}
import ExecutionContext.Implicits.global

/**
 * @author Oleksiy_Dyagilev
 */
class Executor(startDate: DateTime, endDate: DateTime) {

//  implicit val ec = new ExecutionContext {
//    val threadPool = Executors.newFixedThreadPool(100)
//
//    def reportFailure(t: Throwable): Unit = println(t)
//
//    def execute(runnable: Runnable): Unit = threadPool.submit(runnable)
//  }


  def start() = {
    val futures = for (date <- dateRange(startDate, endDate)) yield {
      Future {
        val ws = WeatherSource(date)
        val html = new PageLoader().load(ws.url)
        val recordsOpt = new Extractor().extract(html)

        recordsOpt match {
          case None => println(s"No records found for date $date")
          case Some(records) => records foreach handleRecord
        }

        def handleRecord(recordTry:Try[Record]) = recordTry match {
          case Success(r) => Persistence.save(Weather(date, r))
          case fail => println(s"failed record $fail")
        }
      }
    }
    Await.ready(Future.sequence(futures), Dur.Inf)
  }
}


