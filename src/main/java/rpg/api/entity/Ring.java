package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventHandler;
import rpg.api.eventhandling.EventType;
import rpg.api.eventhandling.events.Event;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.units.DistanceValue;

public class Ring extends Entity {
	
	private static final Sprite sprite = new Sprite("tiles/ring", SpriteTheme.NONE);
	static {
		sprite.addAnimation("ring");
		sprite.setAnimation("ring");
	}
	
	public Ring() {
		super("tiles.ring");
		
		setSprite(sprite);
		
		hb = new Hitbox(new DistanceValue(1), new DistanceValue(1));
	}
	
	@Override
	public Hitbox getHitbox() {
		return hb;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		EventHandler.handle(new Event(EventType.COLLISION_EVENT, objects));
	}
	
}
