package dao

import model.mongo.Weather
import com.github.nscala_time.time.Imports._

/**
 * @author Oleksiy Dyagilev
 */
trait WeatherDaoComponent {

  val weatherDao:WeatherDao

  trait WeatherDao {
    def findByDate(date: DateTime): Seq[Weather]
  }

}
