import model.mongo.Weather
import scala.concurrent._
import com.github.nscala_time.time.Imports._
import com.mongodb.casbah.Imports._



object Main extends App {

  val today = new DateTime()

  new Executor(today.minusDays(1), today).start()

}
