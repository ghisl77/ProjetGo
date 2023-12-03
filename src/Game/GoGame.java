package Game;

import player.ConsolePlayer;

public class GoGame {
	
    private Board goban;
    private IPlayer[] joueurs;

    private int[] points;
    public GoGame(int nbCase){
    	
        goban = new Board(nbCase);
        joueurs = new IPlayer[2];
        joueurs[0] = new ConsolePlayer();
        joueurs[1] = new ConsolePlayer();
        
        points = new int[2];
    }
    
    public GoGame(){
        this(19);
    }
    
    public IPlayer GetPlayer(int p) {
    	return joueurs[p];
    }
    
    public Board getBoard() {
    	
    	return goban;
    }
    
    public void boardSize(int taille) {
    	if(taille < 2 || taille > 25){
            throw new IllegalArgumentException("unacceptable size");
        }
    	goban = new Board(taille);
    }

    public void clearBoard(int taille){
        boardSize(taille);
    }
    
}
