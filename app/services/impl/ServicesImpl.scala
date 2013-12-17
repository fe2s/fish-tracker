package services.impl

import dao.impl.CatchDaoComponentImpl

/**
 * @author Oleksiy Dyagilev
 */

object ServicesImpl {
  lazy val services = new CatchServiceComponentImpl with CatchDaoComponentImpl
}