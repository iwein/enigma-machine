package org.iwein.enigma

import org.specs2._
import execute.Result

/**
 * @author iwein
 */

class EnigmaAcceptanceTests extends Specification with Alphabets {
  def is =
    "This is a specification of the outside behavior of an Enigma machine" ^
      p ^
      "an Enigma machine should " ^
      "transform plain text to cipher " ! toCipher ^
      "transform cipher to plain text" ! toCipher
     end

  val plainText = "ENIGMA REVEALED"
  val cipherText = "QMJIDO MZWZJDMG"

  def toCipher : Result =  {
    enigma.transform(plainText) must_== cipherText
  }
  def toPlain : Result =  {
    enigma.transform(cipherText) must_!= plainText
  }

  def enigma: Enigma = {
    Enigma(
      (Rotor(realAlphabet, alphabetIII)
        (Rotor(realAlphabet, alphabetII)
          (Rotor(realAlphabet, alphabetI)(Reflector())
            .rotateUpto('M'))
          .rotateUpto('C'))
        .rotateUpto('K')))
  }
}