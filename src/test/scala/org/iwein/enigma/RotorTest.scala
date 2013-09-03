package org.iwein.enigma

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

/**
 * @author iwein
 */

class RotorTest extends Specification with Alphabets with Mockito {

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

  "A nested Rotor" should {
    val dependent = mock[Transformer]
    "rotate it's dependent rotor at notch" in {
      val rotor = Rotor(realAlphabet, alphabetI, 'A')(dependent)
      rotor.rotate(1)
      .rotate(1)
      there was one(dependent).rotate(1)
    }
  }

}