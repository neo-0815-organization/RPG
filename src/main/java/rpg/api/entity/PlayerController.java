package rpg.api.entity;

import java.awt.event.KeyEvent;

import rpg.api.vector.UnmodifiableVec2D;

/**
 * The class LocalController used to control a {@link Player}.
 */
public class PlayerController extends LocalController {
	private final double speed = 0.75d;
	
	public PlayerController(final Player player) {
		super(player);
	}
	
	public void controlPlayerMovement(final int keyCode) {
		switch(keyCode) {
			case KeyEvent.VK_W:
				setVelocity(UnmodifiableVec2D.createXY(0, -1).scale(speed));
				getPlayer().sprite.setAnimation("walking/up");
				break;
			case KeyEvent.VK_A:
				setVelocity(UnmodifiableVec2D.createXY(-1, 0).scale(speed));
				getPlayer().sprite.setAnimation("walking/left");
				break;
			case KeyEvent.VK_S:
				setVelocity(UnmodifiableVec2D.createXY(0, 1).scale(speed));
				getPlayer().sprite.setAnimation("walking/down");
				break;
			case KeyEvent.VK_D:
				setVelocity(UnmodifiableVec2D.createXY(1, 0).scale(speed));
				getPlayer().sprite.setAnimation("walking/right");
				break;
		}
	}
	
	public Player getPlayer() {
		return (Player) getEntity();
	}
}
