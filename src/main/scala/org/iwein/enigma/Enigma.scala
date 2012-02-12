package org.iwein.enigma

import java.lang.String

/**
 * This class is severely underdocumented.
 *
 * @author iwein
 */

class Enigma (transformer:Transformer) extends Alphabets {

  def transform(input: String): String = {
    def transformCharacter( toBeTransformed:Char) : Char = {
      val inputIndex: Int = realAlphabet.indexOf(toBeTransformed)

      if (inputIndex < 0) return toBeTransformed
      transformer.rotate
      val transformed: Int = transformer.transform(inputIndex)
      realAlphabet.charAt(transformed)
    }
    input.foldLeft("")((base, char)=>base+transformCharacter(char))
  }

}

object Enigma {
  def apply(tranformer:Transformer): Enigma = new Enigma(tranformer)
}