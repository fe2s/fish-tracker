package controllers.model

import com.github.nscala_time.time.Imports._
import model.Wind
import model.mongo.Weather

/**
 * @author Oleksiy Dyagilev
 */
case class WeatherJson(time: String, condition: String, temp: Int, wind: WindJson, pressure: Int, humidity: Int) {

}

object WeatherJson {

  def convert(w: Weather):WeatherJson = {
    def refine(time:Int) = if (time == 0) "00" else time

    val (h, min) = refine(w.time.getHourOfDay) -> refine(w.time.getMinuteOfHour)

    val timeStr = s"$h:$min"
    WeatherJson(timeStr, w.condition, w.temp, new WindJson(w.wind), w.pressure, w.humidity)
  }

}

case class WindJson(direction: String, speed: Float) {
  def this(w: Wind) = this(w.direction, w.speed)
}