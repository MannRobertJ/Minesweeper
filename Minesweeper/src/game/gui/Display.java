package game.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.logic.Coordinate;
import game.logic.Game;
import game.logic.Square;

public class Display {
	private final Game game;

	public Display(Game game) {
		this.game = game;
	}

	public void show() {
		final int width = game.getWidth();
		final int height = game.getHeight();
		final Map<Coordinate, Square> squares = game.getSquares();

		draw(width, height, squares);
	}

	private void draw(int width, int height, Map<Coordinate, Square> squares) {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(width * 100, height * 100));

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		drawSquares(frame.getContentPane(), width, height, squares);

		frame.pack();
		frame.setVisible(true);
	}

	private void drawSquares(Container container, int width, int height, Map<Coordinate, Square> squares) {
		GridLayout layout = new GridLayout(height, width);
		List<Button> buttons = new ArrayList<Button>();
		container.setLayout(layout);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Square square = squares.get(new Coordinate(i, j));
				Button button = new Button(square, buttons, game);
				buttons.add(button);
				container.add(button.getButton());
			}
		}
	}
}
