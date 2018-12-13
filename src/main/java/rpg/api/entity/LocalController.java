package rpg.api.entity;

import java.awt.event.KeyEvent;

import rpg.api.vector.UnmodifiableVec2D;

/**
 * The class LocalController used to control an {@link Entity} locally.
 */
public class LocalController implements Controller {
	private Entity entity;
	private double speed = 5.0;
	
	@Override
	public Entity getEntity() {
		return entity;
	}
	
	public void controlPlayerMovement(int keyCode) {
		switch (keyCode) {
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
	
}
