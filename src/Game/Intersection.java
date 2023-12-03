package Game;

public class Intersection {
	
	private final int x;
	private final int y;
	
	public Intersection(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Intersection(String s) {
		
		String upper_string = s.toUpperCase();
		
		int temp_x = -1;
		
		final int l = upper_string.length();
		int i;
		for (i = 0 ; i < l ; i++) {
			Character c = upper_string.charAt(i);
			if (Character.isLetter(c)) {
				temp_x = c - 'A';
				break;
			}	
		}
		
		if (temp_x == -1)
			throw new IllegalArgumentException();
		else {
			this.x = temp_x;
		}
		
		try {
			this.y = Integer.parseInt(upper_string.substring(i));
		} catch(Exception e) {
			throw e;
		}
		
		
	}
	
	public boolean validIntersectionString(String s) {
		return true;
		
	}
	
}
