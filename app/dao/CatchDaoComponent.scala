package dao

import models.Catch
import scala.concurrent.Future
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.libs.json.Json
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._


/**
 * @author Oleksiy Dyagilev
 */
trait CatchDaoComponent {

  val catchDao:CatchDao

  trait CatchDao {
    def findAll(): Future[List[Catch]]

    def create(c: Catch): Future[Catch]
  }

  class CatchDaoImpl extends CatchDao {

    private def collection = ReactiveMongoPlugin.db.collection[JSONCollection]("catch")

    def findAll(): Future[List[Catch]] = {
      val query = Json.obj()
      collection.find(query).cursor[Catch].toList()
    }

    def create(c: Catch): Future[Catch] = {
        collection.save(c).map {
          case ok if ok.ok => c
          case error => throw new RuntimeException(error.message)
        }
    }
  }

}
