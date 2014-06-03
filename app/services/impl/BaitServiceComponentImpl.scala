package services.impl

import services.BaitServiceComponent
import dao.BaitDaoComponent
import models.Bait

/**
 * @author Oleksiy Dyagilev
 */
trait BaitServiceComponentImpl extends BaitServiceComponent {
  self: BaitDaoComponent =>

  val baitService = new BaitService {
    override def create(b: Bait): Unit = self.baitDao.create(b)
  }

}
