package models

import play.api.libs.json.Json

/**
 * @author Oleksiy Dyagilev
 */
case class Catch(fish:String,
                 place:String,
                 date: Long,
                 dayRating: Int,
                 hourRatings: Seq[HourRating]) {

}

case class HourRating(start:String, end:String, rating:Int){
}
