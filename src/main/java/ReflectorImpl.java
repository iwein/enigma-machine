public class ReflectorImpl implements Reflector {
	
	private final String code;
	
	public ReflectorImpl(String code) {
		this.code = code;
	}

	@Override
	public int map(int x) {
		return ((code + code).indexOf(code.charAt(x), x + 1)) % code.length();
	}
}
