import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._
import model.mongo._
import com.mongodb.casbah.commons.conversions.scala._


/**
 * @author Oleksiy_Dyagilev
 */
object Persistence {
  val mongoClient = MongoClient(Config.host, Config.port)
  val db = mongoClient("fish")
  val weather = db("weather")

  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()

  def save(o:MongoDBObject) = {
    weather.save(o)
  }

  def save(w:Weather) = {
    val dbOject = grater[Weather].asDBObject(w)
    weather.save(dbOject)
  }

}
