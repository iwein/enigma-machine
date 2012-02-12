package org.iwein.enigma

/**
 * @author iwein
 */

class Reflector extends Transformer {

  val mappings: Map[Int,  Int] = (0 to 25)
    .zip(List(24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19))
    .toMap

  def transform(index: Int): Int = {
    mappings.get(index).getOrElse(throw new Exception("no such element"))
  }
}

object Reflector {
  def apply() = new Reflector
}