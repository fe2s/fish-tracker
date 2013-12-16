package services

import dao.CatchDaoComponent

/**
 * @author Oleksiy Dyagilev
 */
trait LiveServices extends CatchServiceComponent with CatchDaoComponent {
  val catchDao = new CatchDaoImpl
  val catchService = new CatchServiceImpl
}
