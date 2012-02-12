package org.iwein.enigma

/**
 * @author iwein
 */

trait Transformer {
  def rotate(steps:Int) = this

  def transform (inputIndex:Int):Int

}