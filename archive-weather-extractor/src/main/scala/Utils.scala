import scala.util.{Failure, Success, Try}

/**
 * @author Oleksiy_Dyagilev
 */
object Utils {

  def tryOpt[T](opt:Option[T], exceptionMsg:String):Try[T] = {
    opt match {
      case Some(v) => Success(v)
      case _ => Failure(new Exception(exceptionMsg))
    }
  }

}
