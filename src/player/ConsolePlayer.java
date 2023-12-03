package player;

import java.util.Scanner;

import Game.*;

public class ConsolePlayer implements IPlayer {

	private Scanner input;
	
	public ConsolePlayer() {
		input = new Scanner(System.in);
	}
	
	public String getCommand() {
		return input.nextLine();
	}

}
