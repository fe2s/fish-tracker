package services

import dao.WeatherDaoComponent
import model.mongo.Weather

/**
 * @author Oleksiy Dyagilev
 */
trait WeatherServiceComponent {
  self: WeatherDaoComponent =>

  val weatherService:WeatherService

  trait WeatherService {
    def findByDate():Seq[Weather]
  }

}
