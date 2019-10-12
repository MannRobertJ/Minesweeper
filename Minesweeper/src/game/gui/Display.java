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

		print(width, height, squares);
		draw(width, height, squares);

	}

	private void print(int width, int height, Map<Coordinate, Square> squares) {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				Coordinate coord = new Coordinate(i, j);
				Square square = squares.get(coord);
				System.out.print(square.toString());
			}
			System.out.println();
		}
	}

	private void draw(int width, int height, Map<Coordinate, Square> squares) {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(width * 10, height * 10));

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		drawSquares(frame.getContentPane(), width, height, squares);

		frame.pack();
		frame.setVisible(true);
	}

	private void drawSquares(Container container, int width, int height, Map<Coordinate, Square> squares) {
		GridLayout layout = new GridLayout(width, height);
		List<Button> buttons = new ArrayList();
		container.setLayout(layout);
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < height; i++) {
				Square square = squares.get(new Coordinate(i, j));
				Button button = new Button(square, buttons);
				buttons.add(button);
				container.add(button.getButton());
			}
		}
	}
}
