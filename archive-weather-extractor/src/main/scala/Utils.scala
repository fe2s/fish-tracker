import org.joda.time.Days
import scala.util.{Failure, Success, Try}
import com.github.nscala_time.time.Imports._


/**
 * @author Oleksiy_Dyagilev
 */
object Utils {

  def toTry[T](opt: => Option[T], exceptionMsg: String = ""): Try[T] = {
    try {
      opt match {
        case Some(v) => Success(v)
        case _ => Failure(new Exception(exceptionMsg))
      }
    } catch {
      case e: Exception => Failure(new Exception(exceptionMsg + ". Cause " + e))
    }
  }

  def dateRange(start: DateTime, end: DateTime):Seq[DateTime] = {
    val days = Days.daysBetween(start, end).getDays
    (0 to days).map(d => start.plusDays(d))
  }

}
