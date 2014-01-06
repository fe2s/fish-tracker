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

  def extract(html: String): String = {
    //        val tds = for {table <- extractTable(html)
    //                       trs <- extractTrs(table)
    //                       tdsl = extractTds(trs)
    //                       td <- tds
    //        } yield td

    val trls = extractTable(html) map extractTrs




    ""
  }

  def extractTable(html: String): Option[String] = {
    val regex = """(?s)<table class=\"archive_table table\">.*</table>""".r
    regex.findFirstIn(html)
  }

  def extractTrs(table: String): Seq[String] = {
    val regex = """(?s)<tr>(.*?)</tr>""".r
    regex.findAllIn(table).matchData.map(_.group(1)).toSeq
  }

  def extractTds(tr: String): Seq[String] = {
    val regex = """(?s)<td[^>]*>(.*?)</td>""".r
    regex.findAllIn(tr).matchData.map(_.group(1)).toSeq
  }

  def extractSpan(td: String): Option[String] = {
    val regex = """(?s)<div class=\"vl_parent\"><span class=\"vl_child\"><span>(.*?)</span>""".r
    regex.findFirstIn(td)
  }

  def extractCondition(conditionTd: String): Option[String] = {
    val regex = """(?s)<div class="ov_hide vl_parent"><span class="vl_child"><span>(.*?)</span>""".r
    regex.findFirstIn(conditionTd)
  }

  def extractRecord(tds: Seq[String]): Try[Record] = tds match {
    case Seq(timeTd, conditionTd, tempTd, windTd, pressureTd, humidityTd) =>

      for {time <- toTry(extractSpan(timeTd), s"time not found $timeTd")
           condition <- toTry(extractCondition(conditionTd))
           temp <- toTry(extractSpan(tempTd).map(_.toInt), s"temp not $tempTd")
           pressure <- toTry(extractSpan(pressureTd).map(_.toInt), s"pressure not found $pressureTd")
           humidity <- toTry(extractSpan(humidityTd).map(_.toInt), s"humidity not found $humidityTd")
      } yield Record(time, condition, temp, Wind(East, 5), pressure, humidity)

    case _ => Failure(new Exception("not enough TDs to parse record"))
  }

}

