package Game;

import player.ConsolePlayer;

public class GoGame {
	
    private Board goban;
    private IPlayer[] joueurs;
    private IPlayer current_player;
    private char current_player_char;

    private int[] points;
    public GoGame(int nbCase){
    	
        goban = new Board(nbCase);
        joueurs = new IPlayer[2];
        joueurs[0] = new ConsolePlayer();
        joueurs[1] = new ConsolePlayer();
        current_player_char = 'b';
        current_player = GetPlayer(current_player_char);
        
        points = new int[2];
    }
    
    public GoGame(){
        this(19);
    }
    
    public String showboard() {
    	return goban.showboard();
    }
    
    public void play(Intersection inter) {
    	goban.setStone(inter,current_player_char);
    	switchCurrentPlayer();
    }
    
    public void boardSize(int taille) {
    	if(taille < 2 || taille > 25){
            throw new IllegalArgumentException("unacceptable size");
        }
    	goban = new Board(taille);
    }

    private IPlayer GetPlayer(char c) {
    	return joueurs[c=='b'?0:1];
    }
    
    public IPlayer currentPlayer() {
    	return GetPlayer(current_player_char);
    }
    
    public void switchCurrentPlayer() {
    	current_player_char = current_player_char=='w'?'b':'w';
    	current_player = GetPlayer(current_player_char);
    }
}
