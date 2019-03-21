package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.units.DistanceValue;

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
		hb = new Hitbox(new DistanceValue(1), new DistanceValue(1));
	} 

	@Override
	public Hitbox getHitbox() {
		return hb;
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
