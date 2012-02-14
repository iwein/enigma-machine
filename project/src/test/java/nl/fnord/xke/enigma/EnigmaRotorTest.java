package nl.fnord.xke.enigma;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

/**
 * Common code for enigma rotor tests.
 */
public abstract class EnigmaRotorTest {

    private Enigma enigma = new Enigma('A','A','A');
    private int input;
    private int expectedIndex;

    public EnigmaRotorTest(int input, int expectedIndex) {
        super();
        this.input = input;
        this.expectedIndex = expectedIndex;
    }

    abstract int transform(int param, Enigma enigma);

    @Test
    public void test() {
        assertThat(transform(input, enigma), is(expectedIndex));
    }

    static List<Object[]> data(String leftColumn, String rightColumn) {
        assert leftColumn.length() == rightColumn.length();
        List<Object[]> result = newArrayList();
        for (int i = 0, max = leftColumn.length(); i < max; i++) {
            result.add(new Object[] { i, rightColumn.indexOf(leftColumn.charAt(i)) });
        }
        return result;
    }
}
