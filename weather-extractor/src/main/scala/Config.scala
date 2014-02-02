import java.net.URL
import java.util.Properties
import scala.io.Source

/**
 * @author Oleksiy_Dyagilev
 */
object Config {

  val (host,port) = {
    val p = new Properties()
    val url = getClass.getResource("/app.config")
    p.load(Source.fromURL(url).reader())
    (p.getProperty("mongo.host"), p.getProperty("mongo.port").toInt)
  }

}
