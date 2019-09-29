package game.logic;

public class Coordinate {
	private final int x;
	private final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		Coordinate o = (Coordinate) obj;

		return x == o.getX() && y == o.getY();
	}

	@Override
	public int hashCode() {
		return (x + "," + y).hashCode();
	}
}
