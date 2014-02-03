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

    def findByDate(date:DateTime): Seq[Weather] = {
      println("=======")
      val startTime = new DateTime(2014, 1, 1, 0, 0)
      val endTime = new DateTime(2014, 1, 1, 0, 0)

      val found = weatherColl.find("time" $gte startTime $lte endTime).toList

      found map (o => println("found" + o))

      found.map(grater[Weather].asObject(_)).toSeq
    }
  }

}
