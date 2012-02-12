package org.iwein.enigma

import org.specs2.mutable.Specification

/**
 * This class is severely underdocumented.
 *
 * @author iwein
 */

class EnigmaTest extends Specification with Alphabets {

  "An Enigma with one Rotor, I, and our default reflector" should  {
    "encrypt an A to and O and an O to an A when rotor begins at Z" in {
      Enigma( Rotor('Z', alphabetI)( Reflector())).  transform("A") must_== "H"
      Enigma( Rotor('Z', alphabetI)( Reflector())).  transform("H") must_== "A"
    }
    "encrypt an A to and R and an R to an A when rotor begins at L" in {
      Enigma(Rotor('L', alphabetI)( Reflector())).  transform("A") must_== "T"
    }
  }

}