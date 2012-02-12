package org.iwein.enigma

import org.specs2.mutable.Specification

/**
 * @author iwein
 */

class ReflectorTest extends Specification {

  "A Reflector" should  {
    "convert 1 (index of A) to 25 (output index of A)" in {
      Reflector().transform(0) must_== 24
    }
  "convert 2 (index of B) to 18 (output index of B)" in {
      Reflector().transform(1) must_== 17
    }
  "convert 10 (index of B) to 24 (output index of B)" in {
      Reflector().transform(9) must_== 23
    }
  "convert 22 (index of B) to 23 (output index of B)" in {
      Reflector().transform(21) must_== 22
    }
  }

}