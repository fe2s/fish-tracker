name := "archive weather extractor"

version := "0.1"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "0.6.0",
  "com.novus" %% "salat" % "1.9.4",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
)