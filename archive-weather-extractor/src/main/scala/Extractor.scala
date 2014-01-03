import java.util.regex.Pattern
import model._
import scala.io.Source
import scala.util.matching.Regex
import scala.util.{Failure, Try}
import Utils._

/**
 * @author Oleksiy Dyagilev
 */
class Extractor {

  def extract(html: String): String = {
    //    val tds = for {table <- extractTable(html)
    //                   trs <- extractTrs(table)
    //                   tds = extractTds(trs)
    //                   td <- tds
    //    } yield td


    //    val time = tds.toSeq match {
    //      case Seq(timeTd, conditionTd, tempTd, windTd, pressureTd, humidityTd) =>
    //        for (time <- tryOpt(extractOrdinarySpan(timeTd), "time not found");
    //             condition <- extractCondition(conditionTd);
    //             temp <- extractTemp(tempTd);
    //             pressure <- extractOrdinarySpan(pressureTd).map(_.toInt);
    //             humidity <- extractOrdinarySpan(humidityTd).map(_.toInt)
    //        ) yield Record(time, condition, temp, Wind(East, 5), pressure, humidity)
    //      case _ => None
    //    }

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

  def extractOrdinarySpan(td: String): Option[String] = {
    val regex = """(?s)<div class=\"vl_parent\"><span class=\"vl_child\"><span>(.*?)</span>""".r
    regex.findFirstIn(td)
  }

  def extractCondition(conditionTd: String): Option[String] = {
    val regex = """(?s)<div class="ov_hide vl_parent"><span class="vl_child"><span>(.*?)</span>""".r
    regex.findFirstIn(conditionTd)
  }

  def extractTemp(td: String): Try[Int] = {
    extractOrdinarySpan(td) match {
      case Some(temp) => Try(temp.toInt)
      case _ => Failure(new Exception("temperature not found"))
    }
  }


}

