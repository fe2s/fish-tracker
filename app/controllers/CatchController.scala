package controllers

import play.api.mvc.{Action, Controller}
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._


/**
 * @author Oleksiy Dyagilev
 */
object CatchController extends Controller {

  def test() = Action { implicit req =>
    Async {
      println("yahoo " + req)
      Future{Ok}
    }
  }

}
