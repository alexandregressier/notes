package dev.gressier.scalauserland.shell

import dev.gressier.scalauserland.files.programs.{Exit, Program}
import dev.gressier.scalauserland.system.User

import scala.annotation.tailrec
import scala.io.StdIn

/** The shell.
  */
object Shell {

  // TODO: move the following member variables to a more appropriate place
  private val users = List(
    User(System.getProperty("user.name"))
  )
  private val path: List[Program[Any, Any]] = List(
    Exit
  )
  private val user = users.head
  private val wd = "/home/alex"

  private val ifs = "\\s+"

  @tailrec
  def prompt(): Unit = {
    print(s"${Console.YELLOW}${user.name}${Console.WHITE}@${Console.BLUE}$wd${Console.WHITE}$$${Console.RESET} ")

    val input = StdIn.readLine().split(ifs)

    path find { _.name == input.head } match {
      case Some(program) ⇒ program(input.tail)
      case None ⇒ println(s"No `${input.head}` program found.")
    }

    prompt()
  }
}
