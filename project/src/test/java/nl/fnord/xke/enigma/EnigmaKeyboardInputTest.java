package nl.fnord.xke.enigma;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.Lists;

/**
 * A full test of the transformation from the keyboard input to the position on the first rotor.
 * <p/>
 * I've never used JUnit's {@link Parameterized} runner before, let's check it out.
 */
@RunWith(Parameterized.class)
public class EnigmaKeyboardInputTest {

    private Enigma enigma = new Enigma();
    private char input;
    private int expectedIndex;

    public EnigmaKeyboardInputTest(char input, int expectedIndex) {
        super();
        this.input = input;
        this.expectedIndex = expectedIndex;
    }

    @Test
    public void test() {
        assertThat(enigma.type(input), is(expectedIndex));
    }

    @Parameters
    public static List<Object[]> data() {
        // Dilemma, do we apply common sense and use a for loop, or do we apply dumb observation?
        return Lists.newArrayList(
                new Object[] { 'A', 0 }
              , new Object[] { 'B', 1 }
              , new Object[] { 'C', 2 }
              , new Object[] { 'D', 3 }
              , new Object[] { 'E', 4 }
              , new Object[] { 'F', 5 }
              , new Object[] { 'G', 6 }
              , new Object[] { 'H', 7 }
              , new Object[] { 'I', 8 }
              , new Object[] { 'J', 9 }
              , new Object[] { 'K', 10 }
              , new Object[] { 'L', 11 }
              , new Object[] { 'M', 12 }
              , new Object[] { 'N', 13 }
              , new Object[] { 'O', 14 }
              , new Object[] { 'P', 15 }
              , new Object[] { 'Q', 16 }
              , new Object[] { 'R', 17 }
              , new Object[] { 'S', 18 }
              , new Object[] { 'T', 19 }
              , new Object[] { 'U', 20 }
              , new Object[] { 'V', 21 }
              , new Object[] { 'W', 22 }
              , new Object[] { 'X', 23 }
              , new Object[] { 'Y', 24 }
              , new Object[] { 'Z', 25 }
            );
        //...dumb observation it is. Do as I say, don't do as I do :-)
    }
}
