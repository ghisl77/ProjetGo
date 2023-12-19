package Game;

import player.ConsolePlayer;

public class GoGame {
	
    private Board goban;
    private IPlayer[] joueurs;
    private IPlayer current_player;
    private char current_player_char;

    private int[] points;
    public GoGame(int nbCase){
    	
        goban = new Board("...................\n" +
                "...................\n" +
                "...................\n" +
                "...................\n" +
                "...................\n" +
                "...................\n" +
                "...................\n" +
                "...................\n" +
                "...................\n" +
                "...................\n" +
                "..................X\n" +
                "...............XXXO\n" +
                "..............XOOOO\n" +
                ".............XOOOOO\n" +
                "..............XXXX.\n" +
                "...................\n" +
                "...................\n" +
                "...................", nbCase);
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
    	StringBuilder s = new StringBuilder(goban.showboard());
    	s.append("\nblack: ");
    	s.append(points[0]);
    	s.append("\nwhite: ");
    	s.append(points[1]);
    	return s.toString();
    }
    
    public void play(Intersection inter,char color) throws Exception {
    	
    	goban.setStone(inter,(color=='b'?'X':'O'));
    	goban.checkForCapturedStones(inter,color,points);
    	
    	switchCurrentPlayer();
    }
    
    public void boardSize(int taille) {
    	if(taille < 2 || taille > 25){
            throw new IllegalArgumentException();
        }
    	goban = new Board(taille);
    }

    private IPlayer GetPlayer(char c) {
    	return joueurs[c=='b'?0:1];
    }
    
    public IPlayer currentPlayer() {
    	return GetPlayer(current_player_char);
    }
    
    public char getColorPlaying() {
    	return current_player_char;
    }
    
    public int getScore(char player) {
    	return points[player=='b'?0:1];
    }
    
    public void switchCurrentPlayer() {
    	current_player_char = current_player_char=='w'?'b':'w';
    	current_player = GetPlayer(current_player_char);
    }
    
}
