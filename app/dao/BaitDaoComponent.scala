package dao

import models.Bait

/**
 * @author Oleksiy Dyagilev
 */
trait BaitDaoComponent {

  val baitDao:BaitDao

  trait BaitDao {
    def create(b:Bait)
  }

}
