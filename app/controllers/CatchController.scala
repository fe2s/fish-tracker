package controllers

import play.api.mvc.{SimpleResult, Result, Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsString, JsPath, JsResult, Json}
import services.impl.ServicesImpl.services._
import scala.concurrent.Future
import controllers.model.CatchJson


/**
 * @author Oleksiy Dyagilev
 */
object CatchController extends Controller {

  implicit val catchJsonFormat = Json.format[CatchJson]

  def createCatchPage() = Action {
    Ok(views.html.createCatch())
  }

  def create() = Action.async(parse.json) { req =>
      Future {
        println(req.body)
        val json: JsResult[CatchJson] = Json.fromJson(req.body)
        json.fold[SimpleResult](
          invalid => BadRequest("Bad json"),
          validJson => {catchService.create(validJson.toCatch);  Ok("Created") }
        )
      }
  }

  def findAll() = Action.async { req =>
      Future {
        val catches = catchService.findAll()
        val catchesJson = catches.map(new CatchJson(_))
        Ok(Json.toJson(catchesJson))
      }
  }

}