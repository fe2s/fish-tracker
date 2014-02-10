package controllers


import play.api.mvc.{Result, Action, Controller}
import play.api.libs.json.{JsString, JsPath, JsResult, Json}
import models.Catch
import services.impl.ServicesImpl.services._
import controllers.model.{WindJson, WeatherJson}


/**
 * @author Oleksiy Dyagilev
 */
object WeatherController extends Controller {

  implicit val windFormat = Json.format[WindJson]
  implicit val weatherFormat = Json.format[WeatherJson]

  def findByDate(time: Long) = Action { req =>
      println(req + " " + time)

      val weatherList = weatherService.findByDate(time)
      val weatherJson = weatherList.map(WeatherJson.convert)
      Ok(Json.toJson(weatherJson))
  }


}
