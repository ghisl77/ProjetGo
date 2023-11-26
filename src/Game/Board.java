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
        s.append("  ");
        for (int i = 0; i < nbCase; i++) {
            if(i!=0){s.append(i + " ");}
            for (int j = 0; j < nbCase; j++) {
                if(i==0){
                    s.append(alphabet[j]+ " ");
                }
                else
                    s.append(tableau[i][j]);
            }
            if(i!=0){s.append(i + " ");}
            s.append("\n");
        }
        s.append("  ");
        for (int i = 0; i < nbCase; i++) {
            s.append(alphabet[i]+ " ");
        }
        return s.toString();
    }
    
}
