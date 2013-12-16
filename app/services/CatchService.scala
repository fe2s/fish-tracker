package services

import models.Catch
import models.Catch._
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{Json, Writes, Reads, JsObject}
import scala.concurrent
import scala.concurrent.Future

/**
 * @author Oleksiy Dyagilev
 */
object CatchService {

  private def collection = ReactiveMongoPlugin.db.collection[JSONCollection]("catch")

  def findAll():Future[List[Catch]] = {
    val query = Json.obj()
    collection.find(query).cursor[Catch].toList()
  }


  def save(c:Catch) = {
    // TODO: proper result
    collection.save(c)
  }

}
