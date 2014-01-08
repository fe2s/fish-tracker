package model.mongo

import model.{Record, Wind}
import com.github.nscala_time.time.Imports._

/**
 * @author Oleksiy_Dyagilev
 */
case class Weather(time:DateTime, condition:String, temp:Int, wind:Wind, pressure:Int, humidity:Int)

case object Weather {

  def apply(day:DateTime, record:Record):Weather = {
    val (h, min) = {
      val parts = record.time.split(":").map(_.toInt)
      (parts(0), parts(1))
    }
    val dateTime = day.withTime(h, min, 0, 0)
    Weather(dateTime, record.condition, record.temp, record.wind, record.pressure, record.humidity)
  }

}
