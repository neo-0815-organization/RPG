package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.entity.item.ItemStack;
import rpg.api.eventhandling.EventHandler;
import rpg.api.eventhandling.EventType;
import rpg.api.eventhandling.events.CollisionEvent;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.units.DistanceValue;

public class EntityRing extends ItemStack {
	private static final Sprite sprite = new Sprite("tiles/ring", SpriteTheme.NONE);
	
	static {
		sprite.addAnimation("ring");
		sprite.setAnimation("ring");
	}
	
	public EntityRing() {
		super("ring", true);
		
		setSprite(sprite);
		
		hitbox = new Hitbox(new DistanceValue(1d), new DistanceValue(1d));
	}
	
	@Override
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		EventHandler.handle(new CollisionEvent((Entity)objects[0], (Entity)objects[1]));
	}
}