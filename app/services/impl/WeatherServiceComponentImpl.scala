package services.impl

import services.WeatherServiceComponent
import model.mongo.Weather
import dao.WeatherDaoComponent
import com.github.nscala_time.time.Imports._

/**
 * @author Oleksiy Dyagilev
 */
trait WeatherServiceComponentImpl extends WeatherServiceComponent {
  self: WeatherDaoComponent =>

  val weatherService: WeatherService = new WeatherService {
    def findByDate(date: DateTime): Seq[Weather] = weatherDao.findByDate(date)
  }
}
