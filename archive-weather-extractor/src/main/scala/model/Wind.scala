package model

/**
 * @author Oleksiy_Dyagilev
 */
sealed trait WindDirection{
  def rusName():String
}
case object West extends WindDirection {
  def rusName(): String = "западный"
}
case object East extends WindDirection{
  def rusName(): String = "восточный"
}
case object South extends WindDirection{
  def rusName(): String = "южный"
}
case object North extends WindDirection{
  def rusName(): String = "северный"
}
case object NorthEast extends WindDirection{
  def rusName(): String = "северо-восточный"
}
case object SouthEast extends WindDirection{
  def rusName(): String = "юго-восточный"
}
case object SouthWest extends WindDirection{
  def rusName(): String = "юго-западный"
}
case object NorthWest extends WindDirection{
  def rusName(): String = "северо-западный"
}

case class Wind(direction:WindDirection, speed:Float) {

}
