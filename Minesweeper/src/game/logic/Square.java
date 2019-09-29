package game.logic;

public class Square {
	private final int x;
	private final int y;
	private final boolean isBomb;
	private boolean isFlagged;
	private boolean isRevealed;
	private final Board board;

	public Square(int x, int y, boolean bomb, Board board) {
		this.x = x;
		this.y = y;
		this.isBomb = bomb;
		this.isFlagged = false;
		this.isRevealed = false;
		this.board = board;
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
	}

	public void toggleFlagged() {
		isFlagged = !isFlagged;
	}

}
