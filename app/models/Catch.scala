package models

import play.api.libs.json.Json

/**
 * @author Oleksiy Dyagilev
 */
case class Catch(fish:String, place:String) {

}

object Catch {
  implicit val format = Json.format[Catch]
}
