package org.iwein.enigma

import org.specs2.mutable.Specification

/**
 * @author iwein
 */

class EnigmaTest extends Specification with Alphabets {

  "An Enigma with one Rotor, I, and our default reflector" should  {
    "encrypt an A to and O and an O to an A when rotor begins at Z" in {
      val enigma: Enigma = Enigma(Rotor(realAlphabet, alphabetI)(Reflector()).rotateUpto('Z'))
      enigma.transform("A") must_== "H"
      enigma.transform("H") must_== "A"
    }
    "encrypt an A to and R and an R to an A when rotor begins at L" in {
      Enigma(Rotor(realAlphabet, alphabetI)( Reflector()).rotateUpto('L')).  transform("A") must_== "T"
    }
  }

}