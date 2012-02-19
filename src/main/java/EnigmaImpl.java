public class EnigmaImpl implements Enigma {
	
	private final Rotor[] rotors;
	private final Reflector reflector;
	
	public EnigmaImpl(Rotor[] rotors, Reflector reflector) {
		this.rotors = rotors;
		this.reflector = reflector;
	}

	@Override
	public void reset() {
		for (Rotor rotor : rotors) {
			rotor.reset();
		}
	}

	@Override
	public String map(String s) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			result.append(map(s.charAt(i)));
		}
		return result.toString();
	}

	private char map(char c) {
		inc(0, 1);
		
		int x = Util.toInt(c);
		
		for (int i = 0; i < rotors.length; i++) {
			x = rotors[i].toLeft(x);
		}
		
		x = reflector.map(x);
		
		for (int i = rotors.length - 1; i >= 0; i--) {
			x = rotors[i].toRight(x);
		}
		
		return Util.toChar(x);
	}
	
	private void inc(int r, int n) {
		int k = rotors[r].inc(n);
		if (k > 0 && r + 1 < rotors.length) {
			inc(r + 1, k);
		}
	}
}
