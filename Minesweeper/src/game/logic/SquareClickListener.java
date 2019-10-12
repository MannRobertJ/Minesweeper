package game.logic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import game.gui.Button;

public class SquareClickListener implements MouseListener {

	private final Square square;
	private final List<Button> buttons;
	private final Game game;

	public SquareClickListener(Square square, List<Button> buttons, Game game) {
		this.square = square;
		this.buttons = buttons;
		this.game = game;
	}

	public void win() {
		JOptionPane pane = new JOptionPane("You won! Play again?");
		pane.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		boolean isRightClick = SwingUtilities.isRightMouseButton(e);
		if (isRightClick) {
			square.setFlagged(!square.isFlagged());
		} else {
			square.setFlagged(true);
			square.reveal();
		}
		for (Button button : buttons) {
			button.refresh();
		}
		game.checkIfWon();
		if (game.isWon()) {
			win();
		}
		if (game.isLost()) {
			JOptionPane.showMessageDialog(null, "You lost!");
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
