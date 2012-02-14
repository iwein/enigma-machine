package nl.fnord.xke.enigma;

import static com.google.common.collect.Lists.newArrayList;
import static nl.fnord.xke.enigma.Alphabets.alphabetIII;
import static nl.fnord.xke.enigma.Alphabets.realAlphabet;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import quicktime.qd3d.transform.RotateTransformData;

import com.google.common.collect.Lists;

/**
 * A full test of the transformation from the keyboard input to the position on the first rotor.
 * <p/>
 * I've never used JUnit's {@link Parameterized} runner before, let's check it out.
 */
@RunWith(Parameterized.class)
public class EnigmaRotorIIITest {

    private Enigma enigma = new Enigma();
    private int input;
    private int expectedIndex;

    public EnigmaRotorIIITest(int input, int expectedIndex) {
        super();
        this.input = input;
        this.expectedIndex = expectedIndex;
    }

    @Test
    public void test() {
        assertThat(enigma.rotor3(input), is(expectedIndex));
    }

    @Parameters
    public static List<Object[]> data() {
        List<Object[]> result = newArrayList();
        for (int i = 0, max = realAlphabet.length(); i < max; i++) {
            result.add(new Object[] { realAlphabet.charAt(i), alphabetIII.charAt(i) });
        }
        return result;
    }
}
