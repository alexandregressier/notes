package dev.gressier.scalauserland.programs

import dev.gressier.scalauserland.files.File

/** Program.
  */
sealed abstract class Program[+I, +O] (name: String) extends File(name) {
  def run[I2 >: I, O2 >: O] (input: I2): O2
}
