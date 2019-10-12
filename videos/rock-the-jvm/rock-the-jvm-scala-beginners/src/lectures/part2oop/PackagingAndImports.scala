package lectures.part2oop // This is a top-level /package definition/:
// -> All the definitions in this file are under a package named `lectures.part2oop`
// IMPORTANT: other files using the same top-level package definition will see their definitions added to this package

import playground.Cinderella

object PackagingAndImports extends App {
  // A /package/ is a group of definitions (e.g., variables, classes, objects) under the same name
  // -> Matches the directory structure in almost all cases

  // FUNDAMENTAL: the Same principles applies to Java

  // IMPORTANT: a /package definition/ is one of the rare things in Scala that is not an expression

  // /Package members/ are accessible by using their name
  // Example: `GoldenCounter` from `OOBasics.scala`:
  val counter = new GoldenCounter(5)

  // If you are not in the proper package, you must import it (preferably after the top-level package definition
  val princess = new Cinderella

  // INTELLIJ TIP: IntelliJ will automatically suggest to import the class `Cinderella` while typing its name

  // You could have also qualify the name of the definition by using a /fully qualified name/ (abbreviated FQN):
  val copycat = new playground.Cinderella
  // -> The `import playground.Cinderella` above becomes useless for this case in particular
  // IMPORTANT: the FQN is required when dealing with two definitions using the same name

  // Packages are ordered hierarchically
  // The relation between them is established by the `.`
  // Example: `part2oop` is a subpackage of the `lectures` package
  // -> This maps to the directory structure of the file system
  // IMPORTANT: but this is not required (although all IDEs will enforce this practice)

  // /Package objects/ - A Scala-specific code organizing structure
  // For now, values, methods, and constants were accessed from classes, traits, or objects
  // -> Addresses the problem that sometimes we may want to write methods or variables outside of everything else:
  // -> Universal methods, values, and constants

  // IMPORTANT: a package object resides in a package:
  // package object part2oop { // The name of package object is the same as the package (+ using the same case)
  //
  // }
  // The file storing the package object is called `package.scala`
  // IMPORTANT: just like the package hierarchy, such package object name and filename are not really required
  // -> Avoid breaking this naming convention at all costs

  // Definitions inside a package object are visible throughout the entire package
  // -> LEARN: but not the subpackages!
  sayHello()
  println(SPEED_OF_LIGHT)

  // IMPORTANT: there can only be one package object per package
  // -> Explains why the IDE does not prompt for customization

  // -> Package objects are not very used in practice
}
