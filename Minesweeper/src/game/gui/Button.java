package game.gui;

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
		this.button.addActionListener(new SquareClickListener(square, buttons, game));
		refresh();
	}

	public void refresh() {
		button.setText(square.toString());
	}

	public JButton getButton() {
		return button;
	}

}
