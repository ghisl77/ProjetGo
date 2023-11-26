package Appli;

import Game.*;

public class Main {
    public static void main(String[] args) {
		GoGame go = new GoGame();
        
    	boolean game_is_running = true;
    	
    	GoGame partie = new GoGame(args[1]==null ? 19 : Integer.parseInt(args[1]));
    	
    	while(game_is_running) {
    		
    		
    		
    	}
    	
    }
}