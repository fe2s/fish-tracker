package controllers.model

import models.{HourRating, Catch}

/**
 * @author Oleksiy Dyagilev
 */
case class CatchJson(place: String,
                     fish: String,
                     date: Long,
                     dayRating: String,
                     hourRatingStart1: String,
                     hourRatingEnd1: String,
                     hourRating1: String) {
  def this(c: Catch) = this(c.place, c.fish, c.date, c.dayRating.toString, "", "", "")

  def toCatch = {
    val hourRatings = if (Seq(hourRatingStart1, hourRatingEnd1, hourRating1).exists(_.isEmpty)) {
      Seq()
    } else {
      Seq(HourRating(hourRatingStart1, hourRatingEnd1, hourRating1.toInt))
    }

    Catch(fish, place, date, dayRating.toInt, hourRatings)
  }
}


