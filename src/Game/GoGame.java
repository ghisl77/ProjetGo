package Game;

public class GoGame {
    private Board goban;
    private Player[] joueurs;
    public GoGame(int nbCase){
        goban = new Board(nbCase);
        joueurs = new Player[2];
    }
}