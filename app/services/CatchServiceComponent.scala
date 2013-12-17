package services

import models.Catch
import models.Catch._
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{Json, Writes, Reads, JsObject}
import scala.concurrent.Future
import dao.CatchDaoComponent


/**
 * @author Oleksiy Dyagilev
 */
trait CatchServiceComponent {
  self:CatchDaoComponent =>

  val catchService: CatchService

  trait CatchService {
    def findAll(): Future[List[Catch]]

    def create(c: Catch): Future[Catch]
  }

}
