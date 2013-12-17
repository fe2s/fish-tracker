package services.impl

import services.CatchServiceComponent
import models.Catch
import scala.concurrent.Future
import dao.CatchDaoComponent

/**
 * @author Oleksiy_Dyagilev
 */
trait CatchServiceComponentImpl extends CatchServiceComponent {
  self: CatchDaoComponent =>

  override val catchService = new CatchService {

    def findAll(): Future[List[Catch]] = catchDao.findAll()

    def create(c: Catch): Future[Catch] = catchDao.create(c)
  }
}
