package net.bulbyvr.language.scala.implicits

import net.minecraft.server.MinecraftServer

import scala.concurrent.ExecutionContext
import cats.effect.IO
import net.minecraft.client.MinecraftClient

extension[T] (io: IO[T]) {
  def onServer(server: MinecraftServer): IO[T] =
    io.evalOn(ExecutionContext.fromExecutor(server))

  def onClient: IO[T] =
    io.evalOn(ExecutionContext.fromExecutor(MinecraftClient.getInstance()))
}
