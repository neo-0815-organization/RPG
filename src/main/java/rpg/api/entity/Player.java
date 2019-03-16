package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.collision.RectangleHitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * The class represents a player.
 * 
 * @author Neo Hornberger
 */
public class Player extends Entity {
	
	/**
	 * Constructs a player with the standard display name
	 * ("entity.player.name").
	 */
	public Player() {
		super("entity.player");
		hb = new RectangleHitbox(Vec2D.ORIGIN, UnmodifiableVec2D.createXY(32, 0), UnmodifiableVec2D.createXY(0, 32));
	}

	@Override
	public Hitbox getHitbox() {
		return hb;
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
