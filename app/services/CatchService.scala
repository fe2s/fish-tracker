package services

import models.Catch
import models.Catch._
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import reactivemongo.bson.BSONDocument
import reactivemongo.api.collections.GenericQueryBuilder
import play.api.libs.json.{Json, Writes, Reads, JsObject}

/**
 * @author Oleksiy Dyagilev
 */
object CatchService {

  private def collection = ReactiveMongoPlugin.db.collection[JSONCollection]("catch")

  def findAll() = {
    val query = Json.obj()
    val cursor = collection.find(query).cursor[Catch]
    cursor.toList().map(println(_))
  }


  def save(c:Catch) = {
    // TODO: proper result
    collection.save(c)
  }

}
