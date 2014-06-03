package dao.impl

import _root_.dao.BaitDaoComponent
import models.Bait
import _root_.dao.impl.MongoClient._
import models.Bait
import com.novus.salat._
import models.Bait

/**
 * @author Oleksiy Dyagilev
 */
trait BaitDaoComponentImpl extends BaitDaoComponent {

  val baitDao = new BaitDao {

    override def create(b: Bait): Unit = baitColl.save(grater[Bait].asDBObject(b))

  }

}
