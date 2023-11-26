package Game;

public class GoGame {
	
    private Board goban;
    private IPlayer[] joueurs;

    private int[] points;
    public GoGame(int nbCase){
    	
        goban = new Board(nbCase);
        joueurs = new IPlayer[2];
        points = new int[2];
        System.out.println(goban.showboard());
        goban.boardSize(24);
        System.out.println(goban.showboard());
    }
    
    public GoGame(){
        this(19);
    }
    
    public IPlayer GetPlayer(int p) {
    	return joueurs[p];
    }
    
    
}
