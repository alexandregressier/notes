package lectures.part2oop // This is a top-level /package definition/
// -> All the definitions in this file will be part of the `lectures.part2oop` package
// IMPORTANT: other files using the same top-level package definition will see their definitions added to this package

import java.util.Date
import java.sql.{Date => SQLDate}

import playground.fairytale.{Cinderella, PrinceCharming}

object PackagingAndImports extends App {
  // A /package/ is a group of definitions/symbols (e.g., variables, classes, traits, objects) under the same name

  // FUNDAMENTAL: the same principles applies to Java

  // IMPORTANT: a /package definition/ is one of the few exceptions in Scala that is not an expression

  // /Package members/ are accessible by using their name
  // Example: `GoldenCounter` from `OOBasics.scala`:
  val counter = new GoldenCounter(5)

  // If you are not in the package you want to access a symbol from, you must import that package
  // (Package imports are placed preferably after the top-level package definition)
  val princess = new Cinderella

  // INTELLIJ TIP: IntelliJ will automatically suggest to import the class `Cinderella` while typing its name

  // You could have also resolved the `Cinderella` symbol by qualifying its name
  // -> Use a /fully qualified name/ (abbreviated FQN):
  val copycat = new Cinderella
  // -> The `import playground.fairytale.Cinderella` above becomes useless for this particular case
  // IMPORTANT: the FQN is required when dealing with two symbols having the same name from two distinct packages
  // -> Or just use Scala's aliases

  // Packages are ordered hierarchically
  // The relation between them is established by the `.`
  // Example: `part2oop` is a /subpackage/ of the `lectures` package
  // -> This maps to the directory structure of the file system almost all the time
  // LEARN: but this is not a requirement (although all IDEs will enforce this practice)


  // /Package objects/ - A Scala-specific code organizing structure
  // For now, values, methods, and constants were accessed from classes, traits, or objects
  // -> Addresses the problem that sometimes we may want to write methods or variables outside of everything else
  // "Universal/standalone methods and constants" (i.e., vals)

  // A package object resides at the top of a package
  // FUNDAMENTAL: there is at most one package object in a package
  // -> Explains why the IDE does not prompt for customization

  // The name of the package object the same as the current package's (even using the same case)
  // package object part2oop {
  //
  // }
  // By convention, the file storing the package object is called `package.scala`
  // IMPORTANT: such package object name and filename is used almost all the time
  // -> Avoid breaking this naming convention at all costs (the same applies to the mapping between the package
  // hierarchy and the directory structure)

  // Definitions inside a package object are visible throughout the entire package
  // -> LEARN: but not the subpackages!
  sayHello()
  println(SPEED_OF_LIGHT)

  // Note that package objects are not very used in practice


  // Package imports (a.k.a. imports)
  val prince = new PrinceCharming
  // The import of the playground package changes from
  // import playground.fairytale.Cinderella
  // to
  // import playground.{Cinderella, PrinceCharming}

  // Importing all the definitions from a package:
  // import playground._ // Java uses `*`
  // -> But b/c of the principle of the least privilege, only import what you need (delegate this task to your IDE)

  // GREAT: Scala offers renaming for imports (a.k.a. aliasing)CK
  // -> Avoid symbol resolution conflicts and to use FQNs
  // import playground.{Cinderella => Princess,PrinceCharming}
  // FUNDAMENTAL: if there is a single import, `{}` are required in order to define aliases

  // Usage:
  // val princess = new Princess // The symbol is accessed using another name!
  // -> But `Cinderella` is no longer resolved (at least w/o using its FQN)

  // Example: `java.util.Date` and `java.sql.Date`
  val date = new Date
  // LEARN: Scala will use the first listed import to resolve `Date` in `new Date`
  // -> But adding `: Date` type annotation would not be resolved b/c of the naming conflict!

  // Solution #1: use the FQN
  val sqlDateByFQN = new java.sql.Date(2019, 10, 13) // Ignore the depreciation warning

  // Solution #2: use the alias defined above
  val sqlDateByAlias: SQLDate = new SQLDate(2019, 10, 13)

  // /Default imports/ - Automatic and implicit package imports in every Scala file:
  // - java.lang (e.g, String, Object, Exception)
  // - scala (e.g, Int, Nothing, Function)
  // - scala.Predef (e.g, println, ???)

  // TAKEAWAY: leave the daunting task of managing packages to your IDE (which is very good at it)
}
