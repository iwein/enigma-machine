package org.iwein.enigma

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ BeforeAndAfterEach, FlatSpec }

/**
 * @author iwein
 */
@RunWith(classOf[JUnitRunner])
class EnigmaAcceptanceTests extends FlatSpec with ShouldMatchers with BeforeAndAfterEach {
  val plainText = "ENIGMA REVEALED"
  val cipherText = "QMJIDO MZWZJDMG"
  val realAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  val alphabetI = "EKMFLGDQVZNTOWYHXUSPAIBRCJ"
  val alphabetII = "AJDKSIRUXBLHWTMCQGZNPYFVOE"
  val alphabetIII = "BDFHJLCPRTXVZNYEIWGAKMUSQO"
  val reflectorSeq = "ABCDEFGDIJKGMKMIEBFTCVVJAT"

  override def beforeEach() {
  }

  it should "Map right to left correctly" in {
    val roter3 = Roter(realAlphabet, alphabetIII, 0)
    roter3.rightToLeft('E') should be(9)
  }
  it should "Map right to left correctly with initial offset" in {
    val roter3 = Roter(realAlphabet, alphabetIII, 1)
    roter3.rightToLeft('E') should be(10)
  }
  it should "Map right to left correctly with single shift" in {
    val roter3 = Roter(realAlphabet, alphabetIII, 0)
    roter3.shift._2.rightToLeft('E') should be(10)
  }
  it should "Map right to left correctly with initial offset and single shift" in {
    val roter3 = Roter(realAlphabet, alphabetIII, 1)
    roter3.shift._2.rightToLeft('E') should be(0)
  }
  it should "reflect correctly" in {
    val reflector = Reflector(reflectorSeq)
    reflector.reflect(4) should be(16)
    reflector.reflect(9) should be(23)
    reflector.reflect(21) should be(22)
    reflector.reflect(7) should be(3)
    reflector.reflect(24) should be(0)
  }
  it should "fully encrypt the first character" in {
    val roter1 = Roter(realAlphabet, alphabetI, realAlphabet.indexOf('M'))
    val roter2 = Roter(realAlphabet, alphabetII, realAlphabet.indexOf('C'))
    val (_, roter3) = Roter(realAlphabet, alphabetIII, realAlphabet.indexOf('K')).shift
    val reflector = Reflector(reflectorSeq)
    val plainChar = 'M'
    val plainIndex = realAlphabet.indexOf(plainChar)
    val encryptedIndex = roter3.leftToRight(roter2.leftToRight(roter1.leftToRight(reflector.reflect(roter1.rightToLeft(roter2.rightToLeft(roter3.rightToLeft(plainIndex)))))))
    encryptedIndex should be(realAlphabet.indexOf('J'))
  }
  it should "also work from right to left" in {
    val roter3 = Roter(realAlphabet, alphabetIII, 0)
    roter3.leftToRight(4) should be(15)
    roter3.leftToRight(11) should be(5)
    roter3.leftToRight(18) should be(23)
    roter3.leftToRight(8) should be(16)
  }
  it should "encrypt character" in {
    val roter1 = Roter(realAlphabet, alphabetI, realAlphabet.indexOf('M'))
    val roter2 = Roter(realAlphabet, alphabetII, realAlphabet.indexOf('C'))
    val (_, roter3) = Roter(realAlphabet, alphabetIII, realAlphabet.indexOf('K')).shift
    val reflector = Reflector(reflectorSeq)
    val enigma = EnigmaEngine(List(roter1, roter2, roter3), reflector)
    enigma.encrypt('N') should be('G')
    enigma.encrypt('E') should be('Q')
  }
  it should "rotate correctly" in {
    val (rotated, roter3) = Roter(realAlphabet, alphabetIII, alphabetIII.length - 1).shift
    rotated should be(true)
  }

}

case class EnigmaEngine(roters: Seq[Roter], reflector: Reflector) {
  val realAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  def encrypt(char: Char): Char = {
    val charIndex = realAlphabet.indexOf(char)
    val leftPermutation = roters.foldRight(charIndex) {
      case (r, index) => r.rightToLeft(index)
    }
    val plainIndex = roters.foldLeft(reflector.reflect(leftPermutation)) {
      case (index, r) => r.leftToRight(index)
    }
    realAlphabet.charAt(plainIndex)
  }

}

case class Reflector(cipherSeq: String) {

  val cipherLength = cipherSeq.length
  val doubleChipherSeq = (cipherSeq * 2)

  def reflect(index: Int): Int = {
    val initialChar = cipherSeq.charAt(index)
    doubleChipherSeq.indexOf(initialChar, index + 1) % cipherLength
  }
}

case class Roter(leftCipher: String, rightCipher: String, offset: Int) {

  val cipherLength = leftCipher.length

  assert(leftCipher.size == rightCipher.size)
  assert(offset < cipherLength)

  def shift: (Boolean, Roter) = {
    val newIndex = (offset + 1 + cipherLength) % cipherLength
    (newIndex == 0, this.copy(offset = newIndex))
  }

  def rightToLeft(char: Char): Int = {
    rightToLeft(leftCipher.indexOf(char))
  }

  def rightToLeft(index: Int): Int = {
    sideToSide(leftCipher, rightCipher, index)
  }
  private def sideToSide(cipherA: String, cipherB: String, index: Int): Int = {
    val shiftedBIndex = (index + offset) % cipherLength
    val bChar = cipherB.charAt(shiftedBIndex)
    val unshiftedAIndex = cipherA.indexOf(bChar)
    val shiftedAIndex = (unshiftedAIndex - offset + cipherLength) % cipherLength
    shiftedAIndex
  }
  def leftToRight(index: Int): Int = {
    sideToSide(rightCipher, leftCipher, index)
  }
}


