package model

/**
 * @author Oleksiy_Dyagilev
 */
sealed trait WindDirection{
  def rusName():String
}

object WindDirection {
  var values = Seq(West, East, South, North, NorthEast, SouthEast, SouthWest, NorthWest)

  def byRusName(name:String):Option[WindDirection] = {
    val nameLowCase = name.toLowerCase
    values.find(_.rusName() == nameLowCase)
  }

}

case object West extends WindDirection {
  def rusName() = "западный"
}
case object East extends WindDirection{
  def rusName() = "восточный"
}
case object South extends WindDirection{
  def rusName() = "южный"
}
case object North extends WindDirection{
  def rusName() = "северный"
}
case object NorthEast extends WindDirection{
  def rusName() = "северо-восточный"
}
case object SouthEast extends WindDirection{
  def rusName() = "юго-восточный"
}
case object SouthWest extends WindDirection{
  def rusName() = "юго-западный"
}
case object NorthWest extends WindDirection{
  def rusName() = "северо-западный"
}

case class Wind(direction:WindDirection, speed:Float) {

}
