package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;
	
	public GamePanel(Game game) {

		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		// setMinimumSize(size);
		setPreferredSize(size);
		// setMaximumSize(size);
	}

	public void updateGame() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}
}
