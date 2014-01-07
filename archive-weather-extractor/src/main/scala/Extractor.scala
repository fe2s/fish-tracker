import java.util.regex.Pattern
import model._
import scala.io.Source
import scala.util.matching.Regex
import scala.util.{Success, Failure, Try}
import Utils._

/**
 * @author Oleksiy Dyagilev
 */
class Extractor {

  def extract(html: String): Option[Seq[Try[Record]]] = {
    //        val tds = for {table <- extractTable(html)
    //                       trs <- extractTrs(table)
    //                       tdsl = extractTds(trs)
    //                       td <- tds
    //        } yield td

    //    val trs = extractTable(html) map extractTrs

    for {
      table <- extractTable(html)
    } yield for {
        tr <- extractTrs(table).tail // first tr is a header
        tds = extractTds(tr)
    } yield extractRecord(tds)
  }

  def extractTable(html: String): Option[String] = {
    val regex = """(?s)<table class=\"archive_table table\">(.*?)</table>""".r
    val table = regex.findFirstIn(html)
//    println(s"table = $table")
    table
  }

  def extractTrs(table: String): Seq[String] = {
    val regex = """(?s)<tr>(.*?)</tr>""".r
    val trs = regex.findAllIn(table).matchData.map(_.group(1)).toList
//    println(s"trs = $trs")
    trs
  }

  def extractTds(tr: String): Seq[String] = {
    val regex = """(?s)<td[^>]*>(.*?)</td>""".r
    regex.findAllIn(tr).matchData.map(_.group(1)).toSeq
  }

  def extractSpan(td: String): Option[String] = {
    val regex = """(?s)<div class=\"vl_parent\"><span class=\"vl_child\"><span>(.*?)</span>""".r
    val value = regex.findFirstMatchIn(td).map(_.group(1))
//    println(s"span value $value")
    value
  }

  def extractCondition(conditionTd: String): Option[String] = {
    val regex = """(?s)<div class="ov_hide vl_parent"><span class="vl_child"><span>(.*?)</span>""".r
    regex.findFirstMatchIn(conditionTd).map(_.group(1))
  }

  def extractTemp(tempTd:String): Option[Int] = {
    val span = extractSpan(tempTd)
    span.flatMap(_.split("&deg;C").headOption.map(_.toInt))
  }

  def extractWind(windTd:String): Option[Wind] = {
    val directionRegex = """alt="(.*?)"""".r
    val directionOpt = directionRegex.findFirstMatchIn(windTd).map(_.group(1))
//    println(s"directionOpt = $directionOpt")
    val speedRegex = """(?s)<img[^>]*>(.*?)</span>""".r
    val speedOpt = speedRegex.findFirstMatchIn(windTd).map(_.group(1))
//    println(s"speedOpt = $speedOpt")

    for {
      directionStr <- directionOpt
      direction <- WindDirection.byRusName(directionStr)
      speed <- speedOpt
    } yield Wind(direction, speed.toFloat)
  }

  def extractRecord(tds: Seq[String]): Try[Record] = tds match {
    case Seq(timeTd, conditionTd, tempTd, windTd, pressureTd, humidityTd) =>

      for {time <- toTry(extractSpan(timeTd), s"time not found $timeTd")
           condition <- toTry(extractCondition(conditionTd))
           temp <- toTry(extractTemp(tempTd), s"temp not found in $tempTd")
           pressure <- toTry(extractSpan(pressureTd).map(_.toInt), s"pressure not found $pressureTd")
           wind <- toTry(extractWind(windTd), s"wind not found $windTd")
           humidity <- toTry(extractSpan(humidityTd).map(_.toInt), s"humidity not found $humidityTd")
      } yield Record(time, condition, temp, wind, pressure, humidity)

    case other => Failure(new Exception(s"not enough TDs to parse record: $other"))
  }

}

