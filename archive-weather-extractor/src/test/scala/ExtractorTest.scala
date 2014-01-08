import model._
import org.scalatest.FunSuite
import scala.io.Source
import scala.util.Success

/**
 * @author Oleksiy Dyagilev
 */
class ExtractorTest extends FunSuite {

  test("extract table") {
    val table = """<table class="archive_table table">
                  |asdas
                  |asdasd
                  |asdad</table>""".stripMargin


    val html =
      s"""
        |asdasd
        |$table
        |asdsad
        |as
        |dad
      """.stripMargin

    val extractor = new Extractor()
    val extractedTable = extractor.extractTable(html)
    assert(Some(table) === extractedTable)
  }

  test("extractTrs") {
    val extractor = new Extractor()
    val table = readFile("/table.html")
    assert(extractor.extractTrs(table).size === 5)
  }

  test("extractTds") {
    val extractor = new Extractor()
    val tr = readFile("/tr.html")
    assert(extractor.extractTds(tr).size === 6)
  }

  test("html") {
    val extractor = new Extractor()
    val html = readFile("/html.html")
    val records = extractor.extract(html)
    println(s"records = $records")
    val expected = Some(List(
      Success(Record("00:00", "Переменная облачность", 0, Wind("северо-западный", 6.0f), 762, 99)),
      Success(Record("06:00", "Дымка", -2, Wind("северо-западный", 6.0f), 764, 99)),
      Success(Record("12:00", "Ясно", -2, Wind("северо-западный", 5.0f), 765, 91)),
      Success(Record("18:00", "Небольшая облачность", -1, Wind("северо-западный", 4.0f), 767, 63)))
    )
    assert(records == expected)
  }

  def readFile(path: String) = Source.fromURL(getClass.getResource(path)).mkString

}
