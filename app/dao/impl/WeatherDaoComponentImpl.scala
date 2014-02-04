package dao.impl

import dao.WeatherDaoComponent
import model.mongo.Weather
import com.mongodb.casbah.Imports._
import com.novus.salat._
import MongoClient._
import com.github.nscala_time.time.Imports._


/**
 * @author Oleksiy Dyagilev
 */
trait WeatherDaoComponentImpl extends WeatherDaoComponent {

  val weatherDao = new WeatherDao {

    def findByDate(fromDate: DateTime, toDate: DateTime): Seq[Weather] = {
      val found = weatherColl.find("time" $gte fromDate $lte toDate).toList

      found map (o => println("found" + o))

      found.map(grater[Weather].asObject(_)).toSeq
    }
  }

}
