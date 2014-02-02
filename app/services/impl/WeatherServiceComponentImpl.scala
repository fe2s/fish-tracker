package services.impl

import services.WeatherServiceComponent
import model.mongo.Weather
import dao.WeatherDaoComponent

/**
 * @author Oleksiy Dyagilev
 */
trait WeatherServiceComponentImpl extends WeatherServiceComponent {
  self: WeatherDaoComponent =>

  val weatherService: WeatherService = new WeatherService {
    def findByDate(): Seq[Weather] = weatherDao.findByDate()
  }
}
