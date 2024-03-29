package Appli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Game.*;

public class Main {
    public static void main(String[] args) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	final ArrayList<String> list_commands = 
    			new ArrayList<String>(Arrays.asList("protocol_version","version",
    					"known_command","list_commands","quit","boardsize","clear_board",
    					"komi","play","genmove"));
    	
    	boolean game_is_running = true;
    	
    	String[] config = {args[0],args[1]};
    	
    	GoGame partie = new GoGame(config,19);
    	
		ArrayList<String> arguments;
		String command;
		
		System.out.println(partie.showboard());
		
    	do {

    		arguments = new ArrayList<String>(Arrays.asList(partie.currentPlayer().getCommand().split(" ")));
    		
    		boolean has_id = false;
    		int command_id = 0;
    		
    		if (arguments.size() > 0) {
    			if (IsUnsignedInteger(arguments.get(0))) {
    				has_id = true;
    				command_id = Integer.parseInt(arguments.remove(0));
    			}
    			
    		} else {
    			continue;
    		}
    		
    		command = arguments.get(0);
    		StringBuilder response = new StringBuilder();
    		boolean successful_response = true;
    		String error_message = "";
    		
    		if(command.equals("protocol_version")) {

    			response.append("GTP protocol version number 2");
    			
    		}else if (command.equals("version")) {
    			
    			response.append("");
    			
    		}else if (command.equals("known_command")) {
    			if(list_commands.contains(arguments.get(1))) {
    				response.append("true");
    			} else {
    				response.append("false");
    			}
			
    		}else if (command.equals("list_commands")) {
    			for(String comm:list_commands) {
    				response.append(comm);
    			}
    			
    		}else if (command.equals("quit")) {
    			
    			game_is_running = false;
    			
    		}else if (command.equals("boardsize")) {
    			
    			try {
    				partie.boardSize(Integer.parseInt(arguments.get(1)));
    			} catch(Exception e) {
    				successful_response = false;
    				error_message = "unacceptable size";
    			}
    			
    		}else if (command.equals("clear_board")) {
    			// TODO
    		}else if (command.equals("komi")) {
    			// TODO
    		}else if (command.equals("play")) {
    			
    			if (arguments.size() == 1) {
    				partie.switchCurrentPlayer();
    			} else {
    				char char_color = 'n';
        			String color = arguments.get(1).toUpperCase();
        			
        			
        			if (color.equals("BLACK")) {char_color = 'b';}
        			else if (color.equals("WHITE")) {char_color = 'w';}
        			
        			if (char_color != 'n') {
    	    			try {
    	        			Intersection inter = new Intersection(arguments.get(2));
    	    				partie.play(inter,char_color);
    	    			} catch(Exception e) {
    	
    	    				successful_response = false;
    	    				error_message = "illegal move";		
    	    			}
        			}
        			else {

        				successful_response = false;
        				error_message = "illegal move";
        
        			}
    			}
    			
    			
    			
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
    	
    		System.out.println(partie.showboard());
    		
    		
    		System.out.print( 
    				(successful_response?"=":"?") + 
    				(has_id?command_id:"") + 
    				(successful_response ? response.toString() :error_message) );
    		
    		System.out.print("\n\n");
    		
    		if (partie.estTerminee()) {
    			int white_score = partie.getScore('w');
    			int black_score = partie.getScore('b');
    			System.out.println("WHITE (O) has captured " + white_score + " stones");
    			System.out.println("BLACK (X) has captured " + black_score + " stones");
    					
    			if (white_score == black_score) {
    				System.out.println("There is a tie.");
    			} else if (white_score < black_score) {
    				System.out.println("BLACK (X) won.");
    				
    			} else {
    				System.out.println("WHITE (O) won.");
    				
    			}
    			return;
    		}
    		
    	} while(game_is_running);
    	
    }
    
    public static boolean IsUnsignedInteger(String s) {
    	
    	for (int i = 0; i < s.length();i++) {
    		
    		if (!Character.isDigit(s.charAt(i))) {
    			return false;
    			
    		}
    		
    	}
    	return true;
    }
    
}
