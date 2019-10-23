package dev.gressier.scalauserland.files

import java.time.LocalDateTime

/** File.
  */
sealed class File(name: String) {
  val content: String = ""
  val creationDateTime: LocalDateTime = LocalDateTime.now()
  val modificationDateTime: LocalDateTime = creationDateTime
}
