package game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	private final int width;
	private final int height;
	private final double bombDensity;
	private final List<Square> squares;
	private boolean lost;
	private boolean won;

	public Board(int width, int height, double bombDensity) {
		this.width = width;
		this.height = height;
		this.bombDensity = bombDensity;
		this.squares = new ArrayList<Square>();
		this.lost = false;
		this.won = false;
	}

	public void generateSquares() {
		Random rand = new Random();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Boolean isBomb = rand.nextDouble() < bombDensity;
				Square newSquare = new Square(i, j, isBomb, this);
				squares.add(newSquare);
			}
		}
	}

	public void checkIfWon() {
		for (Square square : squares) {
			if (square.isBomb()) {
				continue;
			}
			if (!square.isRevealed()) {
				return;
			}
		}
		won = true;
	}

	public boolean isLost() {
		return lost;
	}

	public void lose() {
		lost = true;
	}

	public boolean isWon() {
		return won;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getBombDensity() {
		return bombDensity;
	}

	public List<Square> getSquares() {
		return squares;
	}

}
