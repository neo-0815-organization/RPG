package rpg;

import java.awt.event.KeyEvent;

import rpg.api.gfx.GameFrame;
import rpg.api.listener.key.KeyboardListener;
import rpg.api.scene.GameField;
import rpg.api.vector.ModifiableVec2D;

/**
 * The main class of this project.
 */
public class RPG {
	public static GameFrame gameFrame;
	public static GameField gameField;
	
	public static void main(final String[] args) {
		init();
	}
	
	/* This method should not be edited! Thanks! */
	/**
	 * This Method initiates the game. - it sets up the GameFrame
	 */
	private static void init() {
		gameFrame = new GameFrame();
		gameField = new GameField();
		registerKeys();
		//		KeyboardListener.start();
	}
	
	private static void registerKeys() {
		KeyboardListener.registerKey(KeyEvent.VK_W, (state) -> {
			switch(state) {
				case PRESSED:
					gameField.getPlayerController().controlPlayerMovement(KeyEvent.VK_W);
					break;
				case PRESSING:
					break;
				case RELEASED:
					break;
				case RELEASING:
					//				System.out.println("Released");
					gameField.getPlayerController().getPlayer().setVelocity(ModifiableVec2D.ORIGIN.toModifiable());
					break;
				default:
					break;
			}
		});
		KeyboardListener.registerKey(KeyEvent.VK_A, (state) -> {
			switch(state) {
				case PRESSED:
					gameField.getPlayerController().controlPlayerMovement(KeyEvent.VK_A);
					break;
				case PRESSING:
					break;
				case RELEASED:
					break;
				case RELEASING:
					gameField.getPlayerController().getPlayer().setVelocity(ModifiableVec2D.ORIGIN.toModifiable());
					break;
				default:
					break;
			}
		});
		KeyboardListener.registerKey(KeyEvent.VK_S, (state) -> {
			switch(state) {
				case PRESSED:
					gameField.getPlayerController().controlPlayerMovement(KeyEvent.VK_S);
					break;
				case PRESSING:
					break;
				case RELEASED:
					break;
				case RELEASING:
					gameField.getPlayerController().getPlayer().setVelocity(ModifiableVec2D.ORIGIN.toModifiable());
					break;
				default:
					break;
			}
		});
		KeyboardListener.registerKey(KeyEvent.VK_D, (state) -> {
			switch(state) {
				case PRESSED:
					gameField.getPlayerController().controlPlayerMovement(KeyEvent.VK_D);
					break;
				case PRESSING:
					break;
				case RELEASED:
					break;
				case RELEASING:
					gameField.getPlayerController().getPlayer().setVelocity(ModifiableVec2D.ORIGIN.toModifiable());
					break;
				default:
					break;
			}
		});
	}
}
