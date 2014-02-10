package controllers.model

import models.Catch

/**
 * @author Oleksiy Dyagilev
 */
case class CatchJson(place: String, fish: String) {
  def this(c:Catch) = this(c.place, c.fish)

  def toCatch = Catch(fish, place)
}


