package game.main;

import game.logic.Game;

public class Minesweeper {

	public static void main(String[] args) {
		Game game = new Game(6, 5, 0.1, false);
		game.run();
	}

}
