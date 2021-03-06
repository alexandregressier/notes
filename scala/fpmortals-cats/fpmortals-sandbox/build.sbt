lazy val root = project
  .in(file("."))
  .settings(
    name    := "fpmortals-sandbox",
    version := "0.1.0",

    scalaVersion := "2.13.2",
    scalacOptions ++= Seq(
      "-language:_",
    ),

    connectInput in run := true,

    libraryDependencies ++= Seq(
      "org.typelevel" %% "simulacrum" % "1.0.0",
      "org.typelevel" %% "cats-core"  % "2.2.0-M1",
      "org.typelevel" %% "mouse"      % "0.25",
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full),
  )
