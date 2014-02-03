package services

import dao.WeatherDaoComponent
import model.mongo.Weather
import org.joda.time.DateTime

/**
 * @author Oleksiy Dyagilev
 */
trait WeatherServiceComponent {
  self: WeatherDaoComponent =>

  val weatherService:WeatherService

  trait WeatherService {
    def findByDate(date: DateTime):Seq[Weather]
  }

}
