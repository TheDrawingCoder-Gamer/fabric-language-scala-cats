package net.bulbyvr.language.scala

import cats.effect.IO
import cats.effect.unsafe.implicits.*
import net.fabricmc.api.{ModInitializer, ClientModInitializer, DedicatedServerModInitializer}
abstract class IOModInitializer extends ModInitializer {
  lazy val initialize: IO[Unit]
  override def onInitialize() = initialize.unsafeRunSync()
}

abstract class IOClientModInitializer extends ClientModInitializer {
  lazy val clientInitialize: IO[Unit]
  override def onInitializeClient() = clientInitialize.unsafeRunSync()
}

abstract class IODedicatedServerModInitializer extends DedicatedServerModInitializer {
  lazy val serverInitialize: IO[Unit]
  override def onInitializeServer() = serverInitialize.unsafeRunSync()
}
