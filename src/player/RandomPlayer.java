package player;

import Game.IPlayer;

import java.util.Random;

public class RandomPlayer implements IPlayer {

    @Override
    public String getCommand() {
        return "play black a1";
    }
}
