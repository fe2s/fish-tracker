import com.github.nscala_time.time.Imports._

/**
 * @author Oleksiy Dyagilev
 */
case class WeatherSource(url:String)

object WeatherSource {

  def apply(date:DateTime):WeatherSource = {
    WeatherSource(date.year().get(), date.monthOfYear().get(), date.dayOfMonth().get())
  }

  def apply(year:Int, month:Int, day:Int):WeatherSource = {
    WeatherSource(s"http://meteo.ua/archive/150/harkov/$year-$month-$day")
  }

}
