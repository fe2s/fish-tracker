import java.util.regex.Pattern
import Extractor._

/**
 * @author Oleksiy Dyagilev
 */
class Extractor {

//  def extract(html:String): String = {
//
//  }

  def extractTable(html:String): Option[String] = {
    val regex = """(?s)<table class=\"archive_table table\">.*</table>""".r
    regex.findFirstIn(html)
  }

}

object Extractor {

  val tablePattern = Pattern.compile("""<table class="archive_table table">.*</table>""", Pattern.DOTALL)
}