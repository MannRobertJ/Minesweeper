package game.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import game.gui.Display;

public class Game {
	private final int width;
	private final int height;
	private final double bombDensity;
	private final Map<Coordinate, Square> squares;
	private boolean lost;
	private boolean won;
	private boolean isStarted;

	public Game(int width, int height, double bombDensity) {
		this.width = width;
		this.height = height;
		this.bombDensity = bombDensity;
		this.squares = new HashMap<Coordinate, Square>();
		this.lost = false;
		this.won = false;
		this.isStarted = false;
		generateSquares();
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public void run() {
		Display display = new Display(this);
		display.show();
	}

	public void generateSquares() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				Square newSquare = new Square(i, j, false, this);
				Coordinate coord = new Coordinate(i, j);
				squares.put(coord, newSquare);
			}
		}

		generateBombs();
	}

	public void generateBombs() {
		Random rand = new Random();
		for (Square square : squares.values()) {
			Boolean isBomb = rand.nextDouble() < bombDensity;
			square.setBomb(isBomb);
		}
		for (Square square : squares.values()) {
			square.countBombNeighbours();
		}
	}

	public void checkIfWon() {
		for (Square square : squares.values()) {
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

	public Map<Coordinate, Square> getSquares() {
		return squares;
	}

}
