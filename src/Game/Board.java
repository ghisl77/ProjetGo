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
        encercle();
    }

    public int getNbCase() {
        return nbCase;
    }
    public void encercle() {
        for (int i = 1; i < nbCase - 1; i++) {
            for (int j = 1; j < nbCase - 1; j++) {
                if (tableau[i][j] == 'X') {
                    boolean surrounded = false;

                    // Vérifie si 'X' est entouré de 'O's de tous les côtés
                    if (tableau[i - 1][j] == 'O' && tableau[i + 1][j] == 'O' &&
                            tableau[i][j - 1] == 'O' && tableau[i][j + 1] == 'O') {
                        surrounded = true;
                    }

                    if (surrounded) {
                        tableau[i][j] = '.';
                    }
                }
            }
        }
    }
    public boolean estlibre(Intersection inter){
        char opps = '0';
        if (tableau[nbCase-inter.getY()][inter.getX()] == '0'){
             opps = 'X';
        }
        if(tableau[nbCase-inter.getY()- 1][inter.getX()] == opps && tableau[nbCase-inter.getY() + 1][inter.getX()] == opps &&
        tableau[nbCase-inter.getY()][inter.getX() - 1] == opps && tableau[nbCase-inter.getY()][inter.getX() + 1] == opps){
            return false;
        }
        return true;
    }
}