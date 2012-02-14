package nl.fnord.xke.enigma;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AcceptanceTest {
    private String clearText = "ENIGMA REVEALED";
    private String cipherText = "QMJIDO MZWZJDMG";

    @Test
    public void accept() {
        Enigma enigma = new Enigma('A','A','A');
        assertThat(enigma.transform(clearText), is(cipherText));
        assertThat(enigma.transform(cipherText), is(clearText));
    }
}
