package game.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import game.gui.Button;

public class SquareClickListener implements ActionListener {

	private final Square square;
	private final List<Button> buttons;
	private final Game game;

	public SquareClickListener(Square square, List<Button> buttons, Game game) {
		this.square = square;
		this.buttons = buttons;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		square.reveal();
		for (Button button : buttons) {
			button.refresh();
		}
		game.checkIfWon();
		if (game.isWon()) {
			JOptionPane.showMessageDialog(null, "You won!");
		}
		if (game.isLost()) {
			JOptionPane.showMessageDialog(null, "You lost!");
		}
	}

}
