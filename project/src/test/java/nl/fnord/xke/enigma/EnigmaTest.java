package nl.fnord.xke.enigma;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class EnigmaTest {

    private Enigma enigma = new Enigma();

    /** Check that the keyboard entry is translated into a position on the first rotor. */
    @Test
    public void input() {
        char input = 'E';
        int expectedIndex = 4;

        assertThat(enigma.type(input), is(expectedIndex));
    }
}
