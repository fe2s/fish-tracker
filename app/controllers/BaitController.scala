package controllers

import play.api.mvc.{SimpleResult, Controller, Action}
import scala.concurrent.Future
import play.api.libs.json.{Json, JsResult}
import controllers.model.{BaitJson, CatchJson}
import services.impl.ServicesImpl.services._
import play.api.libs.concurrent.Execution.Implicits._


/**
 * @author Oleksiy Dyagilev
 */
object BaitController extends Controller {

  implicit val baitJsonFormat = Json.format[BaitJson]

  def createBaitPage() = Action {
    Ok(views.html.createBait())
  }

  def create() = Action.async(parse.json) { req =>
    Future {
      println(req.body)
      val json: JsResult[BaitJson] = Json.fromJson(req.body)
      println("json" + json)
      json.fold[SimpleResult](
        invalid => BadRequest("Bad json"),
        validJson => {baitService.create(validJson.toBait);  Ok("Created") }
      )
    }
  }

}
