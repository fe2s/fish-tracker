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

}
