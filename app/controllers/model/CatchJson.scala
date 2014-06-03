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
                     hourRating1: String,
                     hourRatingStart2: String,
                     hourRatingEnd2: String,
                     hourRating2: String) {
  def this(c: Catch) = this(c.place, c.fish, c.date, c.dayRating.toString, "", "", "", "", "", "")

  def toCatch = {

    def maybeHourRating(start: String, end: String, rating: String) =
      if (Seq(start, end, rating).exists(_.isEmpty)) {
        None
      } else {
        Some(HourRating(start, end, rating.toInt))
      }

    val hourRatings = Seq(
      maybeHourRating(hourRatingStart1, hourRatingEnd1, hourRating1),
      maybeHourRating(hourRatingStart2, hourRatingEnd2, hourRating2)
    ).flatten

    Catch(fish, place, date, dayRating.toInt, hourRatings)
  }
}


