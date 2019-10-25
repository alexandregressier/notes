ThisBuild / organization := "dev.gressier"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.1"

val zioVersion = "1.0.0-RC15"

lazy val root = (project in file("."))
  .settings(
    name := "functional-concurrency-with-zio",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"         % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,
    ),
  )
