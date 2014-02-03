package controllers


import play.api.mvc.{Result, Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsString, JsPath, JsResult, Json}
import models.Catch
import services.impl.ServicesImpl.services._
import com.github.nscala_time.time.Imports._


/**
 * @author Oleksiy Dyagilev
 */
object WeatherController extends Controller {
  def findByDate(date:String) = Action { req =>
    println()
    weatherService.findByDate(new DateTime())
    println(req + " " + date)
    Ok("asdasd")
  }

//  def findByDate() = Action(parse.json) { req =>
//    println(req)
//    Ok("asdasd")
//  }

}
