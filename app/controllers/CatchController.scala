package controllers

import play.api.mvc.{Result, Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsString, JsPath, JsResult, Json}
import models.Catch
import services.impl.ServicesImpl.services._


/**
 * @author Oleksiy Dyagilev
 */
object CatchController extends Controller {

  case class CatchForm(place:String, fish:String) {
    def toCatch = Catch(fish, place)
  }

  implicit val catchFormFormat = Json.format[CatchForm]

  def create() = Action(parse.json) { req =>
      println(req)
      val json: JsResult[CatchForm] = Json.fromJson(req.body)
      json.fold[Result] (
        invalid => BadRequest("Bad json"),
        form => Async {
          catchService.create(form.toCatch).map(_ => Ok)
        }
      )
  }

  def findAll() = Action { req =>
    Async {
      catchService.findAll().map(catches => Ok(Json.toJson(catches)))
    }
  }

}
