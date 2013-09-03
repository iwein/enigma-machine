package org.iwein.enigma

import org.specs2._
import execute.Result

/**
 * @author iwein
 */

class EnigmaAcceptanceTest extends Specification with Alphabets {
  def is = s2"""This is a specification of the outside behavior of an Enigma machine

       an Enigma machine should

         transform cipher to plain text  $toPlain
     """

  val plainText = "ENIGMA REVEALED"
  val cipherText = "QMJIDO MZWZJFJR"

  def toCipher : Result =  {
    enigma.transform(plainText) must_==cipherText
  }

  def toPlain : Result =  {
    enigma.transform(cipherText) must_== plainText
  }

  def enigma: Enigma = Enigma(
      Rotor(realAlphabet, alphabetIII, 'V')
        (Rotor(realAlphabet, alphabetII, 'E')
          (Rotor(realAlphabet, alphabetI, 'Q')(Reflector())
            .rotateUpto('M'))
          .rotateUpto('C'))
        .rotateUpto('K'))
}