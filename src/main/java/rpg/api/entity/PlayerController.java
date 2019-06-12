package rpg.api.entity;

import java.awt.event.KeyEvent;

import rpg.api.vector.UnmodifiableVec2D;

/**
 * The class used to control a {@link Player}.
 * 
 * @author Neo Hornberger
 */
public class PlayerController extends LocalController {
	private final double speed = 2.5d;
	
	/**
	 * Constructs a new {@link LocalController} which is controlling the
	 * {@link Player} 'player'.
	 * 
	 * @param player
	 *                   the {@link Player} to control
	 */
	public PlayerController(final Player player) {
		super(player);
	}
	
	/**
	 * Controls the movement of the {@link Player} which is controlled by this
	 * {@link PlayerController}.
	 * 
	 * @param keyCode
	 *                    the key code to handle
	 */
	public void controlPlayerMovement(final int keyCode) {
		switch(keyCode) {
			case KeyEvent.VK_W:
				addVelocity(UnmodifiableVec2D.createXY(0, -1).scale(speed));
				getPlayer().sprite.setAnimation("walking/up");
				break;
			case KeyEvent.VK_A:
				addVelocity(UnmodifiableVec2D.createXY(-1, 0).scale(speed));
				getPlayer().sprite.setAnimation("walking/left");
				break;
			case KeyEvent.VK_S:
				addVelocity(UnmodifiableVec2D.createXY(0, 1).scale(speed));
				getPlayer().sprite.setAnimation("walking/down");
				break;
			case KeyEvent.VK_D:
				addVelocity(UnmodifiableVec2D.createXY(1, 0).scale(speed));
				getPlayer().sprite.setAnimation("walking/right");
				break;
		}
	}
	
	/**
	 * Returns the {@link Player}, controlled by this {@link PlayerController}.
	 * 
	 * @return the {@link Player}, controlled by this {@link PlayerController}
	 * @see LocalController#getEntity()
	 */
	public Player getPlayer() {
		return (Player) getEntity();
	}
}
