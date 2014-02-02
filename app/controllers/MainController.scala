package controllers

import play.api.mvc._
import play.api.Routes

object MainController extends Controller {

  /**
   * The index page.  This is the main entry point, seeing as this is a single page app.
   */
  def index(path: String) = Action {
    Ok(views.html.index())
  }

  /** The javascript router. */
  def router = Action { implicit req =>
    Ok(
      Routes.javascriptRouter("routes")(
        routes.javascript.CatchController.create,
        routes.javascript.CatchController.findAll
      )
    ).as("text/javascript")
  }

}
