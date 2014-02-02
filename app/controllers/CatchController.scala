package controllers

import play.api.mvc.{SimpleResult, Result, Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsString, JsPath, JsResult, Json}
import models.Catch
import services.impl.ServicesImpl.services._
import scala.concurrent.Future


/**
 * @author Oleksiy Dyagilev
 */
object CatchController extends Controller {

  case class CatchForm(place: String, fish: String) {
    def toCatch = Catch(fish, place)
  }

  implicit val catchFormFormat = Json.format[CatchForm]

  def create() = Action.async(parse.json) { req =>
      Future {
        println(req)
        val json: JsResult[CatchForm] = Json.fromJson(req.body)
        json.fold[SimpleResult](
          invalid => BadRequest("Bad json"),
          form => {catchService.create(form.toCatch);  Ok("Created") }
        )
      }
  }

  def findAll() = Action.async { req =>
      Future {
        val catches = catchService.findAll()
        Ok(Json.toJson(catches))
      }
  }

}
