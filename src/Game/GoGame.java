package Game;

public class GoGame {
    private Board goban;
    private IPlayer[] joueurs;
    
    public GoGame(int nbCase){
        goban = new Board(nbCase);
        joueurs = new IPlayer[2];
    }
    
    
}
