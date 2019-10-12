package game.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import game.gui.Display;

public class Game {
	private final int width;
	private final int height;
	private final double bombDensity;
	private final Map<Coordinate, Square> squares;
	private boolean lost;
	private boolean won;
	private final Display display;
	private final boolean godMode;

	public Game(int width, int height, double bombDensity, boolean godMode) {
		this.width = width;
		this.height = height;
		this.bombDensity = bombDensity;
		this.squares = new HashMap<Coordinate, Square>();
		this.lost = false;
		this.won = false;
		this.display = new Display(this);
		generateSquares();
		this.godMode = godMode;
	}

	public Game(int width, int height, double bombDensity) {
		this(width, height, bombDensity, false);
	}

	public boolean isGodMode() {
		return godMode;
	}

	public void run() {
		final Scanner scan = new Scanner(System.in);
		while (!lost & !won) {
			display.show();
			try {
				int x = scan.nextInt();
				int y = scan.nextInt();
				Coordinate coord = new Coordinate(x, y);
				Square square = squares.get(coord);
				square.reveal();
				checkIfWon();
			} catch (Throwable t) {
				System.out.println("Please enter valid coordinates");
			}
		}
		scan.close();
		if (won) {
			System.out.println("You win!");
			return;
		}
		System.out.println("You lose!");
	}

	public void generateSquares() {
		Random rand = new Random();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Boolean isBomb = rand.nextDouble() < bombDensity;
				Square newSquare = new Square(i, j, isBomb, this);
				Coordinate coord = new Coordinate(i, j);
				squares.put(coord, newSquare);
			}
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
