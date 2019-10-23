package dev.gressier.scalauserland.system

/** User.
  *
  * @param name the name of the user.
  */
sealed case class User(name: String)

/** The root user.
  */
object Root extends User("root")
