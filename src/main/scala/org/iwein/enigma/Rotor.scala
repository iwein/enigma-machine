package org.iwein.enigma


/**
 * This class is severely underdocumented.
 *
 * @author iwein
 */

class Rotor(startPosition: Char, next:Transformer, alphabet:String) extends Transformer with Alphabets {

  var rotorMapping = realAlphabet.zip(alphabet).toList

  while (rotorMapping.head._1 != startPosition){
    rotate
  }

  def transform(inputIndex: Int): Int = {
    require((inputIndex < 26 && inputIndex >= 0), "index out of bounds: %s".format(inputIndex))
    val indexedRotorMapping = rotorMapping.zipWithIndex
    val rightCharacter = indexedRotorMapping(inputIndex)._1._2
    val indexLeftCharacter = indexedRotorMapping.find(_._1._1 == rightCharacter).get._2
    val nextOutput = next.transform(indexLeftCharacter)

    val leftCharacter = indexedRotorMapping(nextOutput)._1._1
    val indexRightCharacter = indexedRotorMapping.find(_._1._2 == leftCharacter).get._2

    if (rotorMapping.head._1 == 'Z') {
      next.rotate
    }

    indexRightCharacter
  }

  private def rotate[A](n: Int, ls: List[A]): List[A] = {
    val nBounded = if (ls.isEmpty) 0 else n % ls.length
    if (nBounded < 0) rotate(nBounded + ls.length, ls)
    else (ls drop nBounded) ::: (ls take nBounded)
  }

  def rotate = {
    rotorMapping = rotate(1, rotorMapping)
  }
}

object Rotor {
  def apply (startPosition:Char, alphabet:String)(next:Transformer): Rotor = new Rotor(startPosition, next, alphabet)
}