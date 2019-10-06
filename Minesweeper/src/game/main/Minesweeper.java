package game.main;

import java.util.Map;

import game.gui.Display;
import game.logic.Coordinate;
import game.logic.Game;
import game.logic.Square;

public class Minesweeper {

	public static void main(String[] args) {
		Game game = new Game(5, 5, 0.5);
		Display display = new Display(game);
		System.out.println();
		Map<Coordinate, Square> squares = game.getSquares();
		Square square = squares.get(new Coordinate(0, 0));
		square.reveal();
		display.show();
	}

}
