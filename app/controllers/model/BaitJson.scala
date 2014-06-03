package controllers.model

import models.Bait

/**
 * @author Oleksiy Dyagilev
 */
case class BaitJson(brand:String) {

  def toBait: Bait = Bait(brand)


}
