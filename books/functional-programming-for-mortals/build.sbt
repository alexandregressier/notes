scalaVersion in ThisBuild := "2.12.6"
scalacOptions in ThisBuild ++= Seq(
  "-language:_",
  "-Ypartial-unification",
  "-Xfatal-warnings",
)

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum"  % "0.13.0",
  "org.scalaz"           %% "scalaz-core" % "7.2.26",
)

addCompilerPlugin("org.spire-math"  %% "kind-projector" % "0.9.7")
addCompilerPlugin("org.scalamacros" %% "paradise"       % "2.1.1" cross CrossVersion.full)