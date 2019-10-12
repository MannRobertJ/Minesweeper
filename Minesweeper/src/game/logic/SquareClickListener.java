package game.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import game.gui.Button;

public class SquareClickListener implements ActionListener {

	private final Square square;
	private final List<Button> buttons;

	public SquareClickListener(Square square, List<Button> buttons) {
		this.square = square;
		this.buttons = buttons;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		square.reveal();
		for (Button button : buttons) {
			button.refresh();
		}
	}

}
