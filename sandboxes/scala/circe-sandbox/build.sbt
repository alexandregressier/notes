name := "circe-sandbox"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.10"

val circeVersion = "0.11.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
) map { _ % circeVersion }

libraryDependencies ++= Seq(
  "io.circe" %% "circe-yaml" % "0.10.0"
)
