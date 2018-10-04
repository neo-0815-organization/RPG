package rpg;

import rpg.api.scene.GameField;

/**
 * The main class of this project.
 */
public class RPG {
	public static final int SCREEN_WIDTH = 1680, SCREEN_HEIGHT = 1050;
	public static GameFrame gui;
	
	public static void main(String[] args) {
		init();
		GameField gf = new GameField();
		gf.startUpdating();
	}
	
	/* This method should not be edited! Thanks! */
	private static void init() {
		gui = new GameFrame();
	}
}
