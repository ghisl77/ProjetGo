package Game;

//import java.util.Arrays;

import java.util.HashMap;
import java.util.HashSet;

public class Board {
    private final char[] alphabet = {'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private final int nbCases;
    private char[][] tableau;


    public Board(int nb){
    	
        nbCases=nb;
        tableau = new char[nbCases][nbCases];
        for (int row = 0; row < nbCases; row++) {
            for (int col = 0; col < nbCases; col++) {
            	setChar(col, row,'.');
            }
        }
        
        handicap();
        
        
        setChar(15,3,'O');
        setChar(16,3,'O');
        setChar(17,3,'O');
        setChar(15,4,'O');
        setChar(16,4,'O');
        setChar(17,4,'O');
        setChar(18,4,'O');
        setChar(18,5,'O');
        
        setChar(18,3,'.');
        setChar(17,2,'X');
        setChar(16,2,'X');
        setChar(15,2,'X');
        setChar(14,3,'X');
        setChar(14,4,'X');
        setChar(15,5,'X');
        setChar(16,5,'X');
        setChar(17,5,'X');
        setChar(18,6,'X');
        
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
    
    private int getLiberties(Intersection i) {
    	
    	int liberties = 0;
    	
    	for (int x = 1 ; x<9 ; x+=2) {
    		if (getChar(i.getCol()-1+x%3,i.getRow()-1+x/3) == '.');
    			liberties++;
    	}
    	
    	return liberties;
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
    
    public String showboard(){
    	
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
    
    public void setStone(Intersection inter, char player) throws Exception {
    	
    	if (getChar(inter) != '.') {
    		throw new Exception();
    	}
    	setChar(inter, player);
    }
    
    public int checkForCapturedStones(Intersection inter,char player) {
    	
    	for (int x = 1 ; x<9 ; x+=2) {

        	HashSet<Intersection> connected = new HashSet<Intersection>();
    		Intersection next_to_inter = new Intersection(inter.getCol()-1+x%3,inter.getRow()-1+x/3);
    		
    		connectedStones(next_to_inter, connected, (player=='X'?'O':'X'));
    		
    		boolean captured = true;
    		
    		for (Intersection i:connected) {
    			if (getLiberties(i) != 0) {
    				captured = false;
    				break;
    			}
    		}
    		
    		for (Intersection i:connected) {
    			setChar(i,'.');
    		}
    		
    	}
    	
    	//HashMap<Intersection, Boolean> checked = new HashMap<Intersection,Boolean>();
    
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
