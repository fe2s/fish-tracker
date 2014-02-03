package dao.impl

import dao.CatchDaoComponent
import models.Catch
import MongoClient._
import com.novus.salat._
import com.mongodb.casbah.Imports._


/**
 * @author Oleksiy_Dyagilev
 */
trait CatchDaoComponentImpl extends CatchDaoComponent {

  val catchDao = new CatchDao {

    def findAll(): List[Catch] = {
        catchColl.find().map(grater[Catch].asObject(_)).toList
    }

    def create(c: Catch): Catch = {
      catchColl.save(grater[Catch].asDBObject(c))
      c
    }
  }

}
