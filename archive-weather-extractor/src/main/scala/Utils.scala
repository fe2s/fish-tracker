import scala.util.{Failure, Success, Try}

/**
 * @author Oleksiy_Dyagilev
 */
object Utils {

  def toTry[T](opt: => Option[T], exceptionMsg:String = ""):Try[T] = {
    try {
      opt match {
        case Some(v) => Success(v)
        case _ => Failure(new Exception(exceptionMsg))
      }
    } catch {
      case e:Exception => Failure(new Exception(exceptionMsg, e))
    }

  }

}
