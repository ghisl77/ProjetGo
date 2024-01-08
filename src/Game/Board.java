package Game;

import java.util.ArrayList;

//import java.util.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Board {
    private final char[] alphabet = {'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private final int nbCases;
    private char[][] tableau;

    public Board(String str, int nb){
        this(nb);
        String[] rows = str.split("\n");
        for (int rowi = 0; rowi < rows.length; rowi++) {
        	String row = rows[rowi];
        	for (int coli = 0;coli < nb;coli++) {
        		char c = row.charAt(coli);
        		if (c == 'X' || c == 'O') {
        			setChar(coli,nb-rowi-1,c);
        		}
        	}
        }
    }

    public Board(int nb){
    	
        nbCases=nb;
        tableau = new char[nbCases][nbCases];
        for (int row = 0; row < nbCases; row++) {
            for (int col = 0; col < nbCases; col++) {
            	setChar(col, row,'.');
            }
        }
        
        handicap();
        
    }
    
    private void setChar(int col, int row, char value) {
    	tableau[row][col] = value;
    }
    
    private void setChar(Intersection inter,char value) {
    	setChar(inter.getCol(),inter.getRow(),value);
    	
    }
    
    private char getChar(int col, int row) {
    	
    	if (col < 0 || col >= nbCases || row < 0 || row >= nbCases) {
    		return 'W';
    	}
    	
    	return tableau[row][col];
    } 
    
    private char getChar(Intersection inter) {
    	return getChar(inter.getCol(),inter.getRow());
    }
    
    public int getSize() {
    	return nbCases;
    }
   
    
    private int getLiberties(Intersection i) {
    	
    	int liberties = 0;
    	
    	for (int x = 1 ; x<9 ; x+=2) {
    		Intersection ic = new Intersection(i.getCol()-1+x%3,i.getRow()-1+x/3);
    		if (isEmpty(ic))
    			liberties++;
    	}
    	
    	return liberties;
    }
    
    public boolean isEmpty(Intersection i) {
    	char c = getChar(i);
		return c == '.'|| c =='+';
    }
    
    public boolean isPlayable(Intersection i) {
    	
    	if (!isEmpty(i)) {return false;}
    	
    	if (getLiberties(i) == 0) {return false;}
  
    	return true;
    }
    
    public ArrayList<Intersection> getPlayableIntersections() {
    	
    	ArrayList<Intersection> result = new ArrayList<Intersection>();
    	
    	for (int row = 0; row < nbCases; row++) {
            for (int col = 0; col < nbCases; col++) {
            	Intersection i = new Intersection(col,row);
            	if (isPlayable(i)) {
            		result.add(i);
            	}
            }
        }
    	
    	return result;
    	
    }
    
    private void handicap(){
        if(nbCases>6){
            int ecart;
            if(nbCases>13){
                ecart = 3;
            }
            else{
                ecart = 2;
            }
            tableau[ecart][ecart]='+';
            tableau[nbCases-ecart-1][nbCases-ecart-1]='+';
            tableau[nbCases-ecart-1][ecart]='+';
            tableau[ecart][nbCases-ecart-1]='+';
            if(nbCases%2!=0 && nbCases!=7){
                tableau[nbCases/2][nbCases/2]='+';
                tableau[nbCases/2][ecart]='+';
                tableau[nbCases/2][nbCases-ecart-1]='+';
                tableau[nbCases-ecart-1][nbCases/2]='+';
                tableau[ecart][nbCases/2]='+';
            }
        }
    }
    
    public String toString(){
    	
        StringBuilder s = new StringBuilder();
        s.append("   ");
        
        for (int col = 0; col < nbCases; col++) {
            s.append(alphabet[col]+ " ");
        }
        s.append("\n");
        
        for (int row = nbCases-1; row >= 0; row--) {
            if(row<nbCases-10){
                s.append(" ");
            }
            s.append((row+1) + " ");
            for (int col = 0; col < nbCases; col++) {
                s.append(getChar(col,row)+" ");
            }
            if(row<nbCases-10){
                s.append(" ");
            }
            s.append((row+1) + " ");
            s.append("\n");
        }
        s.append("   ");
        
        for (int col = 0; col < nbCases; col++) {
            s.append(alphabet[col]+ " ");
        }
        return s.toString();
    }
    
    public String[] SeparatedRows() {
    	
    	return toString().split("\n");
    	
    }
    
    public void setStone(Intersection inter, char player) throws Exception {
    	
    	if (getChar(inter) != '.' && getChar(inter) != '+') {
    		throw new Exception();
    	}
    	setChar(inter, player);
    }
    
    public void checkForCapturedStones(Intersection inter,char player_color,int[] points) {

    	char player_sign = (player_color=='b'?'X':'O');
    	char enemy_sign = (player_color=='b'?'O':'X');
    	
    	int playing = player_color=='b'?0:1;
    	int ennemy =  player_color=='b'?1:0;
    	
    	HashSet<Intersection> connected = new HashSet<Intersection>();
    	
    	for (int x = 1 ; x<9 ; x+=2) {

        	connected = new HashSet<Intersection>();
    		Intersection next_to_inter = new Intersection(inter.getCol()-1+x%3,inter.getRow()-1+x/3);
    		
    		connectedStones(next_to_inter, connected, enemy_sign);
    		
    		points[playing] += capture(connected);
    		
    	}
    	
    	connected = new HashSet<Intersection>();
		connectedStones(inter, connected, player_sign);
		
		points[ennemy] += capture(connected);
    	
    	//HashMap<Intersection, Boolean> checked = new HashMap<Intersection,Boolean>();
    }
    
    private boolean hasNoLiberty(HashSet<Intersection> connected) {
		
		for (Intersection i:connected) {
			if (getLiberties(i) != 0) {
				return false;
			}
		}
		
		return true;
    	
    }
    
    private int capture(HashSet<Intersection> connected) {
    			
		if (hasNoLiberty(connected)) {
    		for (Intersection i:connected) {
    			setChar(i,'.');
    		}
    		return connected.size();
		}
    	
		return 0;
    	
    }
    
    private void connectedStones(Intersection inter, HashSet<Intersection> checked, char ally_sign) {
    	
    	if (!checked.contains(inter) && getChar(inter) == ally_sign) {
    		checked.add(inter);
    		for (int x = 1 ; x<9 ; x+=2) {
        		Intersection next_to_inter = new Intersection(inter.getCol()-1+x%3,inter.getRow()-1+x/3);
        		
        		connectedStones(next_to_inter, checked, ally_sign);
        	}
    	}
    	
    }
    
    
}
