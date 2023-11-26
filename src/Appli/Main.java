package Appli;

import java.util.ArrayList;

import Game.*;

public class Main {
    public static void main(String[] args) {
        
    	final String[] list_commands = 
    		{"protocol_version","version","known_command","list_commands",
    				"quit","boardsize","clear_board","komi","play","genmove"};
    	
    	boolean game_is_running = true;
    	
    	GoGame partie = new GoGame(19);
    	int player_i = 0;
    	
    	while(game_is_running) {
    		
    		IPlayer currentPlayer = partie.GetPlayer(player_i++%2);
    		
    		String[] arguments = currentPlayer.getCommand().split(" ");
    		String command = arguments[0];
    		
    		
    		if(command.equals("protocol_version")) {

    			System.out.println("GPT protocol version number 2");
    			
    		}else if (command.equals("version")) {
    			
    			System.out.println("");
    			
    		}else if (command.equals("known_command")) {
    			for (String comm:list_commands) {
    				if (comm.equals(arguments[1])) {
    					System.out.println("true");
    					break;
    				}
    				
    			}
    		}else if (command.equals("list_commands")) {
    			// TODO
    		}else if (command.equals("quit")) {
    			
    			return;
    			
    		}else if (command.equals("boardsize")) {
    			// TODO
    		}else if (command.equals("clear_board")) {
    			// TODO
    		}else if (command.equals("komi")) {
    			// TODO
    		}else if (command.equals("play")) {
    			// TODO
    		}else if (command.equals("genmove")) {
    			// TODO
    		}
    		
    		/*Commandes a faire:
    		protocol_version 
			version
			known_command
			list_commands
			quit
			boardsize
			clear_board
			komi
			play
			genmove
    		 * */
    		
    		
    		
    	}
    	
    }
}