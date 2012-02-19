public class RotorImpl implements Rotor {

	private final String code;
	private final int pos0;
	
	private int pos;
	
	public RotorImpl(String code, int pos0) {
		this.code = code;
		this.pos = this.pos0 = pos0;
	}

	@Override
	public void reset() {
		pos = pos0;
	}

	@Override
	public int inc(int n) {
		pos += n;
		int k = pos / code.length();
		pos %= code.length();
		return k;
	}

	@Override
	public int toRight(int x) {
		return invTranslate(code.indexOf(Util.toChar(translate(x))));
	}

	@Override
	public int toLeft(int x) {
		return invTranslate(Util.toInt(code.charAt(translate(x))));
	}
	
	private int translate(int x) {
		return (x + pos) % code.length();
	}
	
	private int invTranslate(int x) {
		return (x + code.length() - pos) % code.length();
	}
}
