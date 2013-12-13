package controllers

import play.api.mvc.{Result, Action, Controller}
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsString, JsPath, JsResult, Json}
import play.api.data.validation.ValidationError


/**
 * @author Oleksiy Dyagilev
 */
object CatchController extends Controller {

  case class CatchForm(place:String, fish:String)

  implicit val catchFormFormat = Json.format[CatchForm]

  def test() = Action(parse.json) { req =>
    Async {
      val json: JsResult[CatchForm] = Json.fromJson(req.body)

//      val res:Result = json.fold[Result] {
//
//      }

      val res = Ok

      Future {res}
    }
  }

  def myTest(a: Int => Int, b: Int => Int):Int = {
    a(3) + b(4)
  }

  def myTest2(a: Int => Int):Int = {
    a(3)
  }

  def myTest3(a:Int):Int = a

  def tt() = {

    val f: Int => Int = x => 3

    myTest2 {
      f
    }

    myTest {
      f, f
    }

//    val jsres: JsResult[String] = JsString("toto").validate[String]
//    jsres.fold(
//      errors: Seq[(JsPath, Seq[ValidationError])] => 1,
//        s: String => 2
//    )

  }

}
