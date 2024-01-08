package player;

import Game.Board;
import Game.IPlayer;

public class PlayerFactory {
	public static IPlayer NewPlayer(String type,Board goban,char stone) {
		type = type.toUpperCase();
		
		if (type.equals("CONSOLE")) {
			return new ConsolePlayer();
		}
		
		if (type.equals("RANDOM")) {
			return new RandomPlayer(goban,stone);
		}
		
		return new ConsolePlayer();
		
	}
}
