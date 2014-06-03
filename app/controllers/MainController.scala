package controllers

import play.api.mvc._
import play.api.Routes

object MainController extends Controller {

  /**
   * Home page
   */
  def home(path: String) = Action {
    Ok(views.html.home())
  }

  /** The javascript router. */
  def router = Action { implicit req =>
    Ok(
      Routes.javascriptRouter("routes")(
        routes.javascript.CatchController.create,
        routes.javascript.CatchController.findAll,
        routes.javascript.WeatherController.findByDate,
        routes.javascript.BaitController.create
      )
    ).as("text/javascript")
  }

}
