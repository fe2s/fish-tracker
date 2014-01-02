import org.scalatest.FunSuite

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

}
