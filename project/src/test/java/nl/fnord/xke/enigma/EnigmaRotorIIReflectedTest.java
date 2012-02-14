package nl.fnord.xke.enigma;

import static nl.fnord.xke.enigma.Alphabets.alphabetII;
import static nl.fnord.xke.enigma.Alphabets.realAlphabet;

import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * A full test of the transformation from the keyboard input to the position on the third rotor.
 * <p/>
 * Going from right to left (i.e. before reflection).
 */
@RunWith(Parameterized.class)
public class EnigmaRotorIIReflectedTest extends EnigmaRotorTest {

    public EnigmaRotorIIReflectedTest(int input, int expectedIndex) {
        super(input, expectedIndex);
    }

    @Override
    int transform(int param, Enigma enigma) {
        return enigma.rotor2reflected(param);
    }

    @Parameters
    public static List<Object[]> data() {
        return data(realAlphabet, alphabetII);
    }
}
