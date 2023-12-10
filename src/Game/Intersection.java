package Game;

import java.util.HashMap;
import java.util.Objects;

public class Intersection {

	private static HashMap<Character,Integer> ChartoRow;

	private final int x;

	private final int y;

	static {
		final char[] alphabet = {'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		ChartoRow = new HashMap<Character,Integer>();
		
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
		
		try {
			
			this.x = ChartoRow.get(upper_string.charAt(0));
			this.y = Integer.parseInt(upper_string.substring(1))-1;
			
		} catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public int getCol() {
		return x;
	}

	public int getRow() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intersection other = (Intersection) obj;
		return x == other.x && y == other.y;
	}
	
	

}
