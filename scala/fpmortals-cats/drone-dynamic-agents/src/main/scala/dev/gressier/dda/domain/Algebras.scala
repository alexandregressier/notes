package dev.gressier.dda.domain

import cats.data.NonEmptyList
import dev.gressier.dda.time.Epoch

/** Drone.
  */
trait Drone[F[_]] {
  def getBacklog: F[Int]
  def getAgents : F[Int]
}

/** Machines Node.
 */
final case class MachineNode(id: String)

/** Machines.
 */
trait Machines[F[_]] {
  def getTime                 : F[Epoch]
  def getManaged              : F[NonEmptyList[MachineNode]]
  def getAlive                : F[Map[MachineNode, Epoch]]
  def start(node: MachineNode): F[MachineNode]
  def stop (node: MachineNode): F[MachineNode]
}
