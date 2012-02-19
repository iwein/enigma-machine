import junit.framework.TestCase;

public class EnigmaImplTest extends TestCase {
	
	private Enigma enigma;
	
	private Reflector reflector;
	
	private Rotor[] rotors;
	
	@Override
	protected void setUp() throws Exception {
		reflector = new ReflectorImpl("ABCDEFGDIJKGMKMIEBFTCVVJAT");
		rotors = new Rotor[]{
				new RotorImpl("BDFHJLCPRTXVZNYEIWGAKMUSQO", 10),
				new RotorImpl("AJDKSIRUXBLHWTMCQGZNPYFVOE", 2),
				new RotorImpl("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 12)};
	}

	public void testSingleRotor() {
		enigma = new EnigmaImpl(new Rotor[]{rotors[0]}, reflector);
		assertEquals("J", enigma.map("E"));
		
		enigma.reset();
		assertEquals("E", enigma.map("J"));
	}

	public void testMultipleRotors() {
		enigma = new EnigmaImpl(rotors, reflector);
		assertEquals("Q", enigma.map("E"));
		
		enigma.reset();
		assertEquals("ENIGMAREVEALED", enigma.map("QMJIDOMZWZJFJR"));
	}
}
