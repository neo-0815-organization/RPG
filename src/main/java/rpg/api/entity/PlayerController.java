package rpg.api.entity;

import java.awt.event.KeyEvent;

import rpg.api.vector.UnmodifiableVec2D;

/**
 * The class LocalController used to control a {@link Player}.
 */
public class PlayerController extends LocalController {
	private final double speed = 5d;
	
	public PlayerController(final Player player) {
		super(player);
	}
	
	public void controlPlayerMovement(final int keyCode) {
		switch(keyCode) {
			case KeyEvent.VK_W:
				setVelocity(UnmodifiableVec2D.createXY(0, -1).scale(speed));
				break;
			case KeyEvent.VK_A:
				setVelocity(UnmodifiableVec2D.createXY(-1, 0).scale(speed));
				break;
			case KeyEvent.VK_S:
				setVelocity(UnmodifiableVec2D.createXY(0, 1).scale(speed));
				break;
			case KeyEvent.VK_D:
				setVelocity(UnmodifiableVec2D.createXY(1, 0).scale(speed));
				break;
		}
	}
	
	public Player getPlayer() {
		return (Player) getEntity();
	}
}
