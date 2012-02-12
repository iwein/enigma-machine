package org.iwein.enigma

/**
 * @author iwein
 */

trait Transformer {
  def rotate

  def transform (inputIndex:Int):Int

}