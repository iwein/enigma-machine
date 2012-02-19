import junit.framework.TestCase;

public class RotorImplTest extends TestCase {
	
	private Rotor rotor;

	@Override
	protected void setUp() throws Exception {
		rotor = new RotorImpl("BDFHJLCPRTXVZNYEIWGAKMUSQO", 10);
	}
	
	public void test1toLeft() {
		assertEquals(11, rotor.toLeft(1));
	}
	
	public void test1toRight() {
		assertEquals(21, rotor.toRight(1));
	}
}
