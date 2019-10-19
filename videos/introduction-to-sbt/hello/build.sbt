// build.sbt - sbt configuration file
// Contains a list of key-value pairs representing either settings or tasks

// 3 most common settings:
name := "hello"

version := "0.0.1-SNAPSHOT" // /Semantic versioning/ convention (abbreviated /SemVer/)

scalaVersion := "2.13.1"

// IMPORTANT: sbt delimits the different settings by a blank line

// sbt borrows the source directory structure from Maven
// "Maven Standard Directory Layout"

// src/main/scala: the main application sources
// src/test/scala: the test sources


// TIP: launch sbt in interactive mode to avoid firing up a JVM for each command (that you could run w/ `$ sbt help`

// The prompt in earlier versions of sbt was only `> `

// > help

// See a list of settings for the project:
// > settings

// > tasks
// Similar to Maven goals
// - clean
// - compile: compile your main sources (LEARN: this is not build)
// - test: compile your test sources and run your tests
// - run: run the `main` method

// Tasks are input at the root level


// `:=` are used for direct assignments
// `+=` appends to a collection

// Managed library dependencies
//libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test" // A /library dependency/

// A blank line above this one is still required

// FUNDAMENTAL: under the hood, sbt is using Apache Ivy for dependency management
// -> The LHS of `+=` is constructing a Apache Ivy module:
// - 1st part: the /group id/
// - 2nd part: an /artifact id/
// - 3rd part: the version
// - Last part: configuration parameter for sbt
//   -> `test` specifies that the library dependency is only needed for tasks in the test configuration

// IMPORTANT: it is common to see the Scala version used to compile the artifact with as a suffix of the artifact id
// -> Convention mainly used by third party Scala libraries

// Use the `%%` between the group id and the artifact id  to make sbt use the `scalaVersion` set above
// (at least MAJOR.MINOR, not PATCH)
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

// FUNDAMENTAL: every time you edit an sbt configuration file (e.g, build.sbt, plugins.sbt), you must explicitly tell
// the interactive sbt instance to reload these files via `reload`
// OR
// Automatically reload the build when source changes are detected by setting
// `Global / onChangedBuildSource := ReloadOnSourceChanges`
onChangedBuildSource := ReloadOnSourceChanges

// sbt offers numerous IDE integrations via /community plugins/
// sbt is integrated to IntelliJ via the Scala plugin

// The `project` directory is created by sbt when ran for the first time

// `project/plugins.sbt` can be created to add plugins to sbt

// IMPORTANT: you can run the Scala REPL from interactive sbt by using the `console` task:
// > console
// scala> val alex = User("Alex")
// scala> alex.name

// -> Enables you to have all your managed dependency libraries and all the projects sources in the classpath

// FUNDAMENTAL: prefixing any sbt command with `~` enable the command to be ran in /continuous mode/
// Example: `~compile` will run `compile` any time a source file is changed (including, e.g., build.sbt)

// IMPORTANT: /interactive mode/ VS /batch mode/:
// $ sbt clean test
// Will run the tasks `clean` and `test` in order and then exit out of sbt

// Learn more about sbt by completing https://www.scala-sbt.org/1.x/docs/Getting-Started.html
