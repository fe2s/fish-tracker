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

    def findByDate(time: Long): Seq[Weather] = {
      val date = new DateTime(time)
      val fromDate = date.withTimeAtStartOfDay()
      val toDate = fromDate.withHour(11).withMinute(59)

      println(s"date $date fromDate $fromDate toDate $toDate")

      weatherDao.findByDate(fromDate, toDate)
    }

  }
}
