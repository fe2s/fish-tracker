package controllers.model

import models.Catch

/**
 * @author Oleksiy Dyagilev
 */
case class CatchJson(place: String, fish: String, date: Long) {
  def this(c:Catch) = this(c.place, c.fish, c.date)

  def toCatch = Catch(fish, place, date)
}


