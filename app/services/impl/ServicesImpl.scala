package services.impl

import dao.impl.{BaitDaoComponentImpl, WeatherDaoComponentImpl, CatchDaoComponentImpl}

/**
 * @author Oleksiy Dyagilev
 */

object ServicesImpl {
  lazy val services =
    new CatchServiceComponentImpl with CatchDaoComponentImpl with
    WeatherServiceComponentImpl with WeatherDaoComponentImpl with
    BaitServiceComponentImpl with BaitDaoComponentImpl
}