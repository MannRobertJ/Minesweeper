package game.main;

import game.logic.Game;

public class Minesweeper {

	public static void main(String[] args) {
		Game game = new Game(7, 5, 1);
		game.run();
	}

}
