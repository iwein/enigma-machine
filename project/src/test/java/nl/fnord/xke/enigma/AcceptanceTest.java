package nl.fnord.xke.enigma;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AcceptanceTest {
    private String clearText = "ENIGMA REVEALED";
    private String cipherText = "QMJIDO MZWZJDMG";

    public void accept() {
        Enigma enigma = new Enigma();
        assertThat(enigma.transform(clearText), is(cipherText));
        assertThat(enigma.transform(cipherText), is(clearText));
    }
}
