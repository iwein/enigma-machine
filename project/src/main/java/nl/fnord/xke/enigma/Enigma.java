package nl.fnord.xke.enigma;

import static nl.fnord.xke.enigma.Alphabets.realAlphabet;
public class Enigma {

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
     * Translates the character index of rotor III.
     */
    public int rotor3(int input) {
        throw new UnsupportedOperationException("Please, implement this method.");
    }

}
