package pt.iscte.dcti.poo.sokoban;

public class Main {
	public static void main(String[] args) {
		// Entry point, TODO: Check if there's a smarter way to load the game, 
		// perhaps we can call startSession() on CTOR
		Sokoban game = Sokoban.getInstance();		
		game.startSession();
	}	
}
