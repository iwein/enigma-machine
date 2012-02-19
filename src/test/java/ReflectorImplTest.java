import junit.framework.TestCase;

public class ReflectorImplTest extends TestCase {
	
	private Reflector reflector = new ReflectorImpl("ABCDEFGDIJKGMKMIEBFTCVVJAT");

	public void test0() {
		assertEquals(24, reflector.map(0));
	}
}
