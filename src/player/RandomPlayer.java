package player;

import Game.Board;
import Game.IPlayer;
import Game.Intersection;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements IPlayer {

	private Random rng;
	private Board goban;
	private String color;
	
	public RandomPlayer(Board goban,char stone) {
		this.rng = new Random();
		this.goban = goban;
		this.color = stone=='b'?"black ":"white ";
	}
	
    @Override
    public String getCommand() {
    	
    	ArrayList<Intersection> potentialMoves = goban.getPlayableIntersections();
    	
    	if (potentialMoves.isEmpty()) {
    		return "play";
    	}
    	
    	Intersection move = potentialMoves.get(rng.nextInt(potentialMoves.size()));
    	
        return "play " + color + move.toString() ;
    }
}
