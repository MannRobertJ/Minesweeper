package game.main;

import game.logic.Game;

public class Minesweeper {

	public static void main(String[] args) {
		Game game = new Game(20, 20, 0.2);
		game.run();
	}

}
