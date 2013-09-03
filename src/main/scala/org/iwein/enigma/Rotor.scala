package org.iwein.enigma


/**
 * @author iwein
 */

case class Rotor(leftAlphabet:String, rightAlphabet:String)(next:Transformer) extends Transformer with Alphabets {

  def transform(inputIndex: Int): Int = {
    require(inputIndex < 26 && inputIndex >= 0, "Index out of bounds: %s".format(inputIndex))

    val indexLeftCharacter = leftAlphabet.indexOf(rightAlphabet.charAt(inputIndex))
    val nextOutput = next.transform(indexLeftCharacter)

    rightAlphabet.indexOf(leftAlphabet.charAt(nextOutput))
  }

  private def rotate[A](n: Int, ls: String): String = {
    require(n > 0)
    val nBounded =  n % ls.length
    (ls substring nBounded) + (ls substring (0,  nBounded))
  }

  override def rotate (steps:Int): Transformer = {
    val (rotatedLeftAlphabet, rotatedRightAlphabet) = (rotate(steps, leftAlphabet), rotate(steps, rightAlphabet))
    val rotatedNext = if (leftAlphabet.indexOf('Z')<steps) next.rotate(1) else next
    Rotor(rotatedLeftAlphabet, rotatedRightAlphabet)(rotatedNext)
  }

  def rotateUpto(char:Char): Transformer = {
    rotate(leftAlphabet.indexOf (char))
  }
}