package game.logic;

import java.util.ArrayList;
import java.util.List;

public class Square {
	private final int x;
	private final int y;
	private final boolean isBomb;
	private boolean isFlagged;
	private boolean isRevealed;
	private final Game game;
	private final List<Square> neighbours;
	private int bombNeighbours;

	public Square(int x, int y, boolean bomb, Game game) {
		this.x = x;
		this.y = y;
		this.isBomb = bomb;
		this.isFlagged = false;
		this.isRevealed = false;
		this.game = game;
		this.neighbours = new ArrayList<Square>();
		this.bombNeighbours = 0;
	}

	public void countBombNeighbours() {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				Coordinate coord = new Coordinate(x + i, y + j);
				Square neighbour = game.getSquares().get(coord);
				if (neighbour == null) {
					continue;
				}
				neighbours.add(neighbour);
				if (neighbour.isBomb()) {
					bombNeighbours++;
				}
			}
		}
	}

	public int getBombNeighbours() {
		return bombNeighbours;
	}

	public boolean isFlag() {
		return isFlagged;
	}

	public boolean isRevealed() {
		return isRevealed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public void reveal() {
		if (isRevealed) {
			return;
		}
		isRevealed = true;
		if (isBomb) {
			game.lose();
			return;
		}
		if (bombNeighbours == 0) {
			neighbours.forEach(square -> square.reveal());
		}
	}

	public void toggleFlagged() {
		isFlagged = !isFlagged;
	}

}
