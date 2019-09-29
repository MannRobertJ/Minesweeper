package game.logic;

import java.util.ArrayList;
import java.util.List;

public class Square {
	private final int x;
	private final int y;
	private final boolean isBomb;
	private boolean isFlagged;
	private boolean isRevealed;
	private final Board board;
	private final List<Square> neighbours;
	private final int bombNeighbours;

	public Square(int x, int y, boolean bomb, Board board) {
		this.x = x;
		this.y = y;
		this.isBomb = bomb;
		this.isFlagged = false;
		this.isRevealed = false;
		this.board = board;
		this.neighbours = new ArrayList<Square>();
		this.bombNeighbours = countBombNeighbours();
	}

	private int countBombNeighbours() {
		int bombs = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				Coordinate coord = new Coordinate(x + i, y + j);
				Square neighbour = board.getSquares().get(coord);
				neighbours.add(neighbour);
				if (neighbour.isBomb()) {
					bombs++;
				}
			}
		}
		return bombs;
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
		isRevealed = true;
		if (isBomb) {
			board.lose();
		}
		if (bombNeighbours == 0) {
			for (Square square : neighbours) {
				square.reveal();
			}
		}
	}

	public void toggleFlagged() {
		isFlagged = !isFlagged;
	}

}
