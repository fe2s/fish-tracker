package dao.impl

import dao.WeatherDaoComponent
import model.mongo.Weather

/**
 * @author Oleksiy Dyagilev
 */
trait WeatherDaoComponentImpl extends WeatherDaoComponent {

  val weatherDao = new WeatherDao {
    def findByDate(): Seq[Weather] = Seq()
  }

}
