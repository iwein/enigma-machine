package nl.fnord.xke.enigma;

import static nl.fnord.xke.enigma.Alphabets.realAlphabet;
import static nl.fnord.xke.enigma.Alphabets.alphabetI;
import static nl.fnord.xke.enigma.Alphabets.alphabetII;
import static nl.fnord.xke.enigma.Alphabets.alphabetIII;

public class Enigma {
	
	private int rightRotorPosition;
	private int middleRotorPosition;
	private int leftRotorPosition;

	public Enigma(char initialLeftPosition, char initialMiddlePosition, char initialRightPosition) {
		leftRotorPosition = realAlphabet.indexOf(initialLeftPosition);
		middleRotorPosition = realAlphabet.indexOf(initialMiddlePosition);
		rightRotorPosition = realAlphabet.indexOf(initialRightPosition);
	}

    public String transform(String clearText) {
        throw new UnsupportedOperationException("Please, implement this method.");
    }

    /**
     * Translates an input character to an input index for input into the
     * rotors.
     *
     * @param input
     *            the input character.
     * @return index for the input character
     */
    public int type(char input) {
    	return realAlphabet.indexOf(input);
    }

    /**
     * Translates the character index of rotor III, original pass.
     */
    public int rotor3(int input) {
    	int notchedInput = (input + rightRotorPosition) % 26;
    	char inputChar = alphabetIII.charAt(notchedInput);
    	return realAlphabet.indexOf(inputChar);
    }

    /**
     * Translates the character index of rotor III, reflected pass.
     */
    public int rotor3reflected(int input) {
    	char inputChar = realAlphabet.charAt(input);
    	return alphabetIII.indexOf(inputChar);
    }

    /**
     * Translates the character index of rotor II, original pass.
     */
    public int rotor2(int input) {
    	char inputChar = alphabetII.charAt(input);
    	return realAlphabet.indexOf(inputChar);
    }

    /**
     * Translates the character index of rotor II, reflected pass.
     */
    public int rotor2reflected(int input) {
    	char inputChar = realAlphabet.charAt(input);
    	return alphabetII.indexOf(inputChar);
    }

    /**
     * Translates the character index of rotor I, original pass.
     */
    public int rotor1(int input) {
    	char inputChar = alphabetI.charAt(input);
    	return realAlphabet.indexOf(inputChar);
    }

    /**
     * Translates the character index of rotor I, reflected pass.
     */
    public int rotor1reflected(int input) {
    	char inputChar = realAlphabet.charAt(input);
    	return alphabetI.indexOf(inputChar);
    }

    public int reflector(int input) {
        throw new UnsupportedOperationException("Please, implement this method.");
    }
}