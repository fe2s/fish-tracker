import org.scalatest.FunSuite
import scala.io.Source

/**
 * @author Oleksiy Dyagilev
 */
class ExtractorTest extends FunSuite {

  test("extract table"){
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
    val table = Source.fromURL(getClass.getResource("/table.html")).mkString
    assert(extractor.extractTrs(table).size === 5)
  }

  test("extractTds") {
    val extractor = new Extractor()
    val tr = Source.fromURL(getClass.getResource("/tr.html")).mkString
    assert(extractor.extractTds(tr).size === 6)
  }

}
