package dev.gressier.scalauserland.files.programs

import dev.gressier.scalauserland.files.File

/** Program.
  *
  * @param name the name of the program.
  * @tparam I the type of the program input.
  * @tparam O the type of the program output.
  */
sealed abstract class Program[+I, +O] (name: String) extends File(name) {
  def apply[I2 >: I, O2 >: O] (input: I2): O2
}

/** The `exit` program.
  */
object Exit extends Program[Unit, Unit]("exit") {
  override def apply[I2 >: Unit, O2 >: Unit](input: I2): O2 = System.exit(0) // TODO: remove the `exit` side-effect
}
