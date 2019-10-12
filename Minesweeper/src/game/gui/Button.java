package game.gui;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import game.logic.Game;
import game.logic.Square;
import game.logic.SquareClickListener;

public class Button {
	private final Square square;
	private final JButton button;

	public Button(Square square, List<Button> buttons, Game game) {
		this.square = square;
		this.button = new JButton();
		this.button.addMouseListener(new SquareClickListener(square, buttons, game));
		refresh();
		this.button.setBackground(Color.DARK_GRAY);
	}

	public void refresh() {
		if (square.isFlagged()) {
			System.out.println(square.toString());
		}
		button.setText(square.toString());

		if (square.isRevealed()) {
			button.setForeground(pickTextColor(square));
			button.setBackground(Color.LIGHT_GRAY);
		}
		if (square.isFlagged()) {
			button.setForeground(Color.WHITE);
		}
	}

	public Color pickTextColor(Square square) {
		int bombs = square.getBombNeighbours();
		switch (bombs) {
		case 1:
			return Color.BLUE;
		case 2:
			return Color.GREEN;
		case 3:
			return Color.RED;
		case 4:
			return new Color(255, 0, 255); // PURPLE
		case 5:
			return new Color(128, 0, 0); // MAROON
		case 6:
			return new Color(64, 224, 208); // TURQUOISE
		case 7:
			return Color.BLACK;
		case 8:
			return Color.GRAY;
		default:
			return null;
		}
	}

	public JButton getButton() {
		return button;
	}

}
