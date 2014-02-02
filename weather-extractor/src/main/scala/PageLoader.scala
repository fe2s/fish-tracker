import java.io.{InputStreamReader, BufferedReader}
import java.net.{HttpURLConnection, URL}
import scala.io.Source

/**
 * @author Oleksiy Dyagilev
 */
class PageLoader {

  def load(url:String):String = {
    val connection = new URL(url).openConnection().asInstanceOf[HttpURLConnection]
    Source.fromInputStream(connection.getInputStream).mkString
  }

}
