package game.gui;

import java.util.Map;

import game.logic.Coordinate;
import game.logic.Game;
import game.logic.Square;

public class Display {
	private final Game game;

	public Display(Game game) {
		this.game = game;
	}

	public void show() {
		int width = game.getWidth();
		int height = game.getHeight();
		Map<Coordinate, Square> squares = game.getSquares();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Coordinate coord = new Coordinate(i, j);
				Square square = squares.get(coord);
				if (square.isBomb()) {
					System.out.print("b");
					continue;
				}
				if (square.isRevealed()) {
					System.out.print("r");
					continue;
				}
				System.out.print("?");
			}
			System.out.println();
		}
	}
}
