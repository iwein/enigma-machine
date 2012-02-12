package org.iwein.enigma

/**
 * This class is severely underdocumented.
 *
 * @author iwein
 */

class Reflector extends Transformer {

  val transformations = List(25, 18, 21, 8, 17, 19,
    12, 4, 16, 24, 14, 7,
    15, 11, 13, 9, 5, 2,
    6, 26, 3, 23, 22, 10, 1, 20).map(_-1).zipWithIndex.toMap.map(_ swap)

  def transform(index: Int): Int = {
    transformations.get(index).getOrElse(throw new Exception("no such element"))
  }

  def rotate = {}
}

object Reflector {
  def apply() = new Reflector
}