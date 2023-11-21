package Game;

public class Board {
    private int nbCase ;
    final char[] alphabet = {'A','B','C','D','E','F','G','H','J','K','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public Board(int nb){
        nb=nbCase;
    }
    public String showboard(){
        StringBuilder s = new StringBuilder();
        s.append("  ");
        for (int i = 0; i < nbCase+1; i++) {
            if(i!=0){s.append(i + " ");}
            for (int j = 0; j < nbCase+1; j++) {
                if(i==0){
                    s.append(alphabet[j]+ " ");
                }
                else
                    s.append(". ");
            }
            if(i!=0){s.append(i + " ");}
            s.append("\n");
        }
        s.append("  ");
        for (int i = 0; i < nbCase+1; i++) {
            s.append(alphabet[i]+ " ");
        }
        return s.toString();
    }
}