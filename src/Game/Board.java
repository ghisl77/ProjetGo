package Game;

//import java.util.Arrays;

import java.util.HashMap;

public class Board {
    private final char[] alphabet = {'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private final int nbCase;
    private char[][] tableau;


    public Board(int nb){
    	
        nbCase=nb;
        tableau = new char[nbCase][nbCase];
        for (int i = 0; i < nbCase; i++) {
            for (int j = 0; j < nbCase; j++) {
                tableau[i][j] = '.';
            }
        }
        
        handicap();
        
    }
    
    private void handicap(){
        if(nbCase>6){
            int ecart;
            if(nbCase>13){
                ecart = 3;
            }
            else{
                ecart = 2;
            }
            tableau[ecart][ecart]='+';
            tableau[nbCase-ecart-1][nbCase-ecart-1]='+';
            tableau[nbCase-ecart-1][ecart]='+';
            tableau[ecart][nbCase-ecart-1]='+';
            if(nbCase%2!=0 && nbCase!=7){
                tableau[nbCase/2][nbCase/2]='+';
                tableau[nbCase/2][ecart]='+';
                tableau[nbCase/2][nbCase-ecart-1]='+';
                tableau[nbCase-ecart-1][nbCase/2]='+';
                tableau[ecart][nbCase/2]='+';
            }
        }
    }
    
    public String showboard(){
        StringBuilder s = new StringBuilder();
        s.append("   ");
        for (int i = 0; i < nbCase; i++) {
            s.append(alphabet[i]+ " ");
        }
        s.append("\n");
        for (int i = 0; i < nbCase; i++) {
            if(i>nbCase-10){
                s.append(" ");
            }
            s.append(nbCase-i + " ");
            for (int j = 0; j < nbCase; j++) {
                s.append(tableau[i][j]+" ");
            }
            if(i>nbCase-10){
                s.append(" ");
            }
            s.append(nbCase-i + " ");
            s.append("\n");
        }
        s.append("   ");
        for (int i = 0; i < nbCase; i++) {
            s.append(alphabet[i]+ " ");
        }
        return s.toString();
    }
    
    public void setStone(Intersection inter, char player) {
    	tableau[nbCase-inter.getY()][inter.getX()] = (player=='b'?'X':'O');
    }

    public int getNbCase() {
        return nbCase;
    }
    
}
