package dev.gressier.scalauserland.files

import java.time.LocalDateTime

/** File.
  */
case class File(name: String) {
  val content: String = ""
  val creationDateTime: LocalDateTime = LocalDateTime.now()
  val modificationDateTime: LocalDateTime = creationDateTime
}
