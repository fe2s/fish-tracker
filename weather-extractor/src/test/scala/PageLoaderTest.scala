import org.scalatest.FunSuite

/**
 * @author Oleksiy Dyagilev
 */
class PageLoaderTest extends FunSuite {

  test("load") {
    val page = new PageLoader().load("http://ya.ru")
    println(page)

    println(new PageLoader().load("http://meteo.ua/archive/150/harkov/2012-1-2"))

  }

}
