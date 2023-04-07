/**
 * This is a drop in replacement for the scala language adapter. This means it WILL conflict with the original!
 */

package net.bulbyvr.language.scala

import net.fabricmc.loader.api.{LanguageAdapter, ModContainer}

class ScalaLanguageAdapter extends LanguageAdapter {
  override def create[T](modContainer: ModContainer, s: String, aClass: Class[T]): T = {
    try {
      val objectClass = Class.forName(s + "$")
      val moduleField = objectClass.getField("MODULE$")
      val instance = moduleField.get(null).asInstanceOf[T]
      if (instance == null) throw new NullPointerException
      instance
    } catch {
      case e: Exception =>
        println(s"Unable to find ${s}$$MODULE$$")
        aClass.getConstructor().newInstance()
    }
  }
}
