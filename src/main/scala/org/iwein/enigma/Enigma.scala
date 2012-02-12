package org.iwein.enigma

import java.lang.String

/**
 * @author iwein
 */

case class Enigma (transformer:Transformer, output:String="") extends Alphabets {

  def transform(input: String): String = {
    (input.foldLeft(this)((enigma, char)=>enigma.transformCharacter(char))).output
  }

  def transformCharacter( toBeTransformed:Char) : Enigma = {
    val inputIndex: Int = realAlphabet.indexOf(toBeTransformed)
    if (inputIndex < 0) return Enigma(transformer, this.output+toBeTransformed)

    val rotatedTransformer = transformer.rotate(1)
    val transformed: Int = rotatedTransformer.transform(inputIndex)
    Enigma(rotatedTransformer, this.output+realAlphabet.charAt(transformed))
  }

}