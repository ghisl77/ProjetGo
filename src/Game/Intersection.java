package Game;

import java.util.HashMap;

public class Intersection {

	private static HashMap<Character,Integer> ChartoRow;

	private final int x;

	private final int y;

	static {
		final char[] alphabet = {'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		for (int i = 0; i < alphabet.length;i++) {
			ChartoRow.put(alphabet[i],i);
		}
	}
	
	public Intersection(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Intersection(String s) {
		
		String upper_string = s.toUpperCase();

		this.x = ChartoRow.get(upper_string.charAt(0));
		
		try {
			this.y = Integer.parseInt(upper_string.substring(1));
		} catch(Exception e) {
			throw e;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
