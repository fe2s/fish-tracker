package controllers

import play.api.mvc.{Result, Action, Controller}
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsString, JsPath, JsResult, Json}
import play.api.data.validation.ValidationError
import models.Catch
import services.CatchService


/**
 * @author Oleksiy Dyagilev
 */
object CatchController extends Controller {

  case class CatchForm(place:String, fish:String) {
    def toCatch = Catch(fish, place)
  }

  implicit val catchFormFormat = Json.format[CatchForm]

  def test() = Action(parse.json) { req =>
      val json: JsResult[CatchForm] = Json.fromJson(req.body)
      json.fold[Result] (
        invalid => BadRequest("Bad json"),
        form => Async {
          CatchService.save(form.toCatch).map(_ => Ok)
        }
      )
  }

  def findAll() = Action { req =>
    Async {
      CatchService.findAll().map(_ => Ok)
    }
  }

//    val jsres: JsResult[String] = JsString("toto").validate[String]
//    jsres.fold(
//      errors: Seq[(JsPath, Seq[ValidationError])] => 1,
//        s: String => 2
//    )

  }

}
