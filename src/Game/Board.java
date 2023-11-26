package Game;

public class Board {
    private int nbCase ;
    final static char[] alphabet = {'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private char[][] tableau;
    
    public Board(int nb){
        nbCase=nb;
        tableau = new char[nb][nb];
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                tableau[i][j] = '.';
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
    
}
