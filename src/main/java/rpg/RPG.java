package rpg;

import java.awt.event.KeyEvent;

import rpg.api.gfx.GameFrame;
import rpg.api.listener.key.KeyboardListener;

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
		registerKeys();
		KeyboardListener.start();
	}
	
	private static void registerKeys() {
		KeyboardListener.registerKey(KeyEvent.VK_W, (state) -> {
			switch(state) {
			case PRESSED:
				break;
			case PRESSING:
				System.out.println("PRESSING");
				break;
			case RELEASED:
				break;
			case RELEASING:
				break;
			default:
				break;
			}
		});
	}
}
