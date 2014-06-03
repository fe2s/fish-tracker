package services

import dao.BaitDaoComponent
import models.Bait

/**
 * @author Oleksiy Dyagilev
 */
trait BaitServiceComponent {
  self:BaitDaoComponent =>

  val baitService:BaitService

  trait BaitService {
    def create(b:Bait)
  }

}
