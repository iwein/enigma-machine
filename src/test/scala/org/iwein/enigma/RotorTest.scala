package org.iwein.enigma

import org.specs2.mutable.Specification

/**
 * @author iwein
 */

class RotorTest extends Specification with Alphabets {

  def testRotor: Rotor = {
    Rotor(realAlphabet, alphabetI)(Reflector())
  }

  "A single Rotor" should  {
    "convert 0 (index of E) to 7 (output index of Q)" in {
      testRotor.transform(0) must_== 7
    }
    "make sure the rotor loops" in {
      val rotor = testRotor
      rotor.transform(0) must_== 7
      rotor.rotate(26).transform(0) must_== 7
    }
    "convert 1 (index of K) to 10 (output index of N)" in {
      testRotor.transform(1) must_== 10
    }
    "convert 0 (index of E) and again (index of K) to 7 (output index of Q) and then to 13 (index of N)" in {
      val rotor = testRotor
      rotor.transform(0) must_== 7
      rotor.rotate(1).transform(0) must_== 13
    }
  }

}