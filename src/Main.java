package Appli;

import java.util.ArrayList;
import java.util.Arrays;

import Game.*;

public class Main {
    public static void main(String[] args) {
    	
    	final ArrayList<String> list_commands = 
    			new ArrayList<String>(Arrays.asList("protocol_version","version",
    					"known_command","list_commands","quit","boardsize","clear_board",
    					"komi","play","genmove"));
    	
    	boolean game_is_running = true;

    	GoGame partie = new GoGame(19);
    	int player_i = 0;
    	System.out.println(partie.getBoard().showboard());
		
    	
    	while(game_is_running) {
    		
    		IPlayer currentPlayer = partie.GetPlayer(player_i++%2);
    		
    		String[] arguments = currentPlayer.getCommand().split(" ");
    		String command = arguments[0];
    		
    		if(command.equals("protocol_version")) {

    			System.out.println("GTP protocol version number 2");
    			
    		}else if (command.equals("version")) {
    			
    			System.out.println("");
    			
    		}else if (command.equals("known_command")) {
    			if(list_commands.contains(arguments[1])) {
    				System.out.println("true");
    			} else {
        			System.out.println("false");
    			}
			
    		}else if (command.equals("list_commands")) {
    			for(String comm:list_commands) {
    				System.out.println(comm);
    			}
    			
    		}else if (command.equals("quit")) {
    			
    			game_is_running = false;
    			
    		}else if (command.equals("boardsize")) {
    			partie.boardSize(Integer.parseInt(arguments[1]));
    			
    		}else if (command.equals("clear_board")) {
    			// TODO
    		}else if (command.equals("komi")) {
    			// TODO
    		}else if (command.equals("play")) {
    			// TODO
    		}else if (command.equals("genmove")) {
    			// TODO
    		}
    		
    		System.out.println(partie.getBoard().showboard());
    		
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