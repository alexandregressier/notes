lazy val root = project
  .in(file("."))
  .settings(
    name    := "drone-dynamic-agents",
    version := "0.1.0",

    scalaVersion := "2.13.2",
    scalacOptions ++= Seq(
      "-language:_",
    ),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "simulacrum" % "1.0.0",
      "org.typelevel" %% "cats-core"  % "2.2.0-M1",
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full),
  )

