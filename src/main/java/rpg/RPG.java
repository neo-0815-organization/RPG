package rpg;

import rpg.api.gfx.GameFrame;

/**
 * The main class of this project.
 */
public class RPG {
	public static GameFrame gameFrame;
	
	public static void main(final String[] args) {
		init();
	}
	
	/* This method should not be edited! Thanks! */
	/**
	 * This Method initiates the game.
	 * - it sets up the GameFrame
	 */
	private static void init() {
		gameFrame = new GameFrame();
	}
}
