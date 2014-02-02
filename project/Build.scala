import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "fish-tracker"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Reactive Mongo dependencies
//    "org.reactivemongo" %% "play2-reactivemongo" % "0.9",
    "com.novus" %% "salat" % "1.9.4",

    // WebJars pull in client-side web libraries
    "org.webjars" %% "webjars-play" % "2.2.0"
//    "org.webjars" % "bootstrap" % "3.0.0",
//    "org.webjars" % "knockout" % "2.3.0",
//    "org.webjars" % "requirejs" % "2.1.8"

    // Add your own project dependencies in the form:
    // "group" % "artifact" % "version"
  )

  lazy val weatherModel = Project(id = "weather-model", base = file("weather-model"))

  lazy val weatherExtractor = Project(id = "weather-extractor", base = file("weather-extractor")) dependsOn(weatherModel)


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    scalaVersion := "2.10.2"

    //    publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

  ) dependsOn(weatherModel) aggregate(weatherModel, weatherExtractor)

}
