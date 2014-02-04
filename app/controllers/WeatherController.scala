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

  def findByDate(time: Long) = Action { req =>

    weatherService.findByDate(time)
    println(req + " " + time)
    Ok("asdasd")
  }


}
