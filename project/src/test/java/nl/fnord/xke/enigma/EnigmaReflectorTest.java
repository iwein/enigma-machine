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
 * Tests the index translation performed by the reflector.
 */
@RunWith(Parameterized.class)
public class EnigmaReflectorTest {

    private Enigma enigma = new Enigma('A','A','A');
    private int input;
    private int expectedIndex;

    public EnigmaReflectorTest(int input, int expectedIndex) {
        super();
        this.input = input;
        this.expectedIndex = expectedIndex;
    }

    @Test
    public void test() {
        assertThat(enigma.reflector(input), is(expectedIndex));
    }

    @Parameters
    public static List<Object[]> data() {
        return Lists.newArrayList(
                new Object[] {  0, 24 }
              , new Object[] {  1, 17 }
              , new Object[] {  2, 20 }
              , new Object[] {  3,  7 }
              , new Object[] {  4, 16 }
              , new Object[] {  5, 18 }
              , new Object[] {  6, 11 }
              , new Object[] {  7,  4 }
              , new Object[] {  8, 15 }
              , new Object[] {  9, 23 }
              , new Object[] { 10, 13 }
              , new Object[] { 11,  6 }
              , new Object[] { 12, 14 }
              , new Object[] { 13, 10 }
              , new Object[] { 14, 12 }
              , new Object[] { 15,  8 }
              , new Object[] { 16,  4 }
              , new Object[] { 17,  1 }
              , new Object[] { 18,  5 }
              , new Object[] { 19, 25 }
              , new Object[] { 20,  2 }
              , new Object[] { 21, 22 }
              , new Object[] { 22, 21 }
              , new Object[] { 23,  9 }
              , new Object[] { 24,  0 }
              , new Object[] { 25, 19 }
            );
    }
}
