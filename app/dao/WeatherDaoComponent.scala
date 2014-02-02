package dao

import model.mongo.Weather

/**
 * @author Oleksiy Dyagilev
 */
trait WeatherDaoComponent {

  val weatherDao:WeatherDao

  trait WeatherDao {
    def findByDate(): Seq[Weather]
  }

}
