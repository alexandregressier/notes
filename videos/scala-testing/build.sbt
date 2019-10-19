name := "scala-testing"

version := "0.1"

scalaVersion := "2.13.1"

enablePlugins(TestNGPlugin)

resolvers += Resolver.jcenterRepo // only for sbt before 0.13.6

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.mockito" % "mockito-core" % "3.1.0" % Test,
  "ru.dokwork" % "mockito-sugar_2.12" % "0.3.3" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "org.specs2" %% "specs2-core" % "4.6.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.14.1" % Test,
)

scalacOptions in Test ++= Seq(
  "-Yrangepos",
)
