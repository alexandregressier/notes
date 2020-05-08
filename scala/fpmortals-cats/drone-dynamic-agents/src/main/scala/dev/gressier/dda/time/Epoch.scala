package dev.gressier.dda.time

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration

/** A millisecond timestamp.
 */
final case class Epoch(millis: Long) extends AnyVal {
  def +(duration: FiniteDuration): Epoch = Epoch(millis + duration.toMillis)
  def -(duration: FiniteDuration): Epoch = Epoch(millis - duration.toMillis)
  def -(other: Epoch): FiniteDuration = FiniteDuration(millis - other.millis, TimeUnit.MILLISECONDS)
}
