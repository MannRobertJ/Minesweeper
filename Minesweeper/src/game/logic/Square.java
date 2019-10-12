package game.logic;

import java.util.ArrayList;
import java.util.List;

public class Square {
	private final int x;
	private final int y;
	private boolean isBomb;
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

	public boolean isFlagged() {
		return isFlagged;
	}

	public void setFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	public void countBombNeighbours() {
		this.bombNeighbours = 0;
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

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	private void clearSquareAndNeighbours() {
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				Coordinate coord = new Coordinate(x + i, y + j);
				Square square = game.getSquares().get(coord);
				if (square != null) {
					square.setBomb(false);
				}
			}
		}
	}

	public void reveal() {
		if (!game.isStarted()) {
			clearSquareAndNeighbours();
			game.setStarted(true);
		}
		if (isRevealed) {
			return;
		}
		isFlagged = false;
		isRevealed = true;
		countBombNeighbours();
		if (isBomb) {
			game.lose();
			return;
		}
		if (bombNeighbours == 0) {
			neighbours.forEach(square -> square.reveal());
		}
	}

	@Override
	public String toString() {
		if (isFlagged) {
			return "F";
		}
		if (!isRevealed) {
			return "";
		}
		if (isBomb) {
			return "B";
		}

		return "" + getBombNeighbours();
	}

	public void toggleFlagged() {
		isFlagged = !isFlagged;
	}

}
