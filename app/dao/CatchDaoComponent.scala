package dao

import models.Catch
import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._


/**
 * @author Oleksiy Dyagilev
 */
trait CatchDaoComponent {

  val catchDao:CatchDao

  trait CatchDao {
    def findAll(): List[Catch]

    def create(c: Catch): Catch
  }

}
