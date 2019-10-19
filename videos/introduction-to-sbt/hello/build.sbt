// build.sbt - sbt configuration file
// Contains a list of key-value pairs representing either settings or tasks

// 3 most common settings:
name := "hello"

version := "0.0.1-SNAPSHOT" // Semantic versioning convention

scalaVersion := "2.13.1"

// IMPORTANT: sbt delimits the different settings by a blank line

// sbt borrows the source directory structure from Maven
// "Maven Standard Directory Layout"

// src/main/scala: the main application sources
// src/test/scala: the test sources


// TIP: launch sbt in interactive mode to avoid firing up a JVM for each command

// The prompt in earlier versions of sbt was only `> `

// > help

// See a list of settings for the project:
// > settings

// > tasks
// Similar to Maven goals
// - clean
// - compile: compile your main sources
// - test: compile your test sources and run your tests
// - run: run the `main` method

// Tasks are input at the root level


// `:=` are used for direct assignments
// `+=` appends to a collection

// Managed library dependencies
libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test"

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
