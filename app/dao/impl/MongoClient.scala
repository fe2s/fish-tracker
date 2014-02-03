package dao.impl

import com.mongodb.casbah.commons.conversions.scala.{RegisterJodaTimeConversionHelpers, RegisterConversionHelpers}
import com.novus.salat._
import play.api.Play.current

/**
 * @author Oleksiy Dyagilev
 */
object MongoClient {

  // play + salat integration
  implicit val ctx = new Context {
    val name = "PlaySalatContext"
  }
  ctx.registerClassLoader(current.classloader)


  val mongoClient = com.mongodb.casbah.MongoClient("localhost", 27017)

  val db = mongoClient("fish")

  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()



  val catchColl = db("catch")
  val weatherColl = db("weather")



}
