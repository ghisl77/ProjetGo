package Game;

import player.ConsolePlayer;
import player.PlayerFactory;

public class GoGame {
	
	private static final String[] defaultConfig = {"CONSOLE","CONSOLE"};
    private Board goban;
    private IPlayer[] joueurs;
    private IPlayer current_player;
    private char current_player_char;
    private short consecutive_skips;

    private int[] points;
    public GoGame(String[] plrConfig,int nbCase){
    	
        goban =  new Board(nbCase);
        
        		/*
        		new Board(
        		"...................\n" +
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
                "...................\n" +
                "...................", nbCase);
        		*/
        
        joueurs = new IPlayer[2];
        
        joueurs[0] = PlayerFactory.NewPlayer(plrConfig[0],goban,'b');
        joueurs[1] = PlayerFactory.NewPlayer(plrConfig[1],goban,'w');
        
        current_player_char = 'b';
        current_player = GetPlayer(current_player_char);
        
        consecutive_skips = 0;
        
        points = new int[2];
    }
    
    public GoGame(){
        this(defaultConfig,19);
    }
    
    public String showboard() {
    	
    	String[] boardRows = goban.SeparatedRows();
    	

    	boardRows[boardRows.length-3] += "WHITE (O) has captured " + points[1] + " stones";
    	boardRows[boardRows.length-2] += "BLACK (X) has captured " + points[0] + " stones";
    	
    	/*
    	StringBuilder s = new StringBuilder(goban.showboard());
    	s.append("\nblack: ");
    	s.append(points[0]);
    	s.append("\nwhite: ");
    	s.append(points[1]);
    	return s.toString();
    	*/
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (String str:boardRows) {
    		sb.append(str);
    		sb.append("\n");
    	}
    	
    	return sb.toString();
    }
    
    public void play(Intersection inter,char color) throws Exception {
    	consecutive_skips = 0;
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
    	consecutive_skips++;
    	current_player_char = current_player_char=='w'?'b':'w';
    	current_player = GetPlayer(current_player_char);
    }
    
    public boolean estTerminee() {
    	return consecutive_skips >= 2;
    }
}
