package dao.impl

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala.{RegisterJodaTimeConversionHelpers, RegisterConversionHelpers}

/**
 * @author Oleksiy Dyagilev
 */
object MongoClient {
  val mongoClient = com.mongodb.casbah.MongoClient("localhost", 27017)

  val db = mongoClient("fish")

  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()


  val catchColl = db("catch")
  val weatherColl = db("weather")



}
