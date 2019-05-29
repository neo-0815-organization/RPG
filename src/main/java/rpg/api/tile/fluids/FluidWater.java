package rpg.api.tile.fluids;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Fluid;

public class FluidWater extends Fluid {
	
	public FluidWater() {
		hitbox = new Hitbox(1, 1);
		sprite = new Sprite("fluids/water");
		sprite.addAnimation("water_right");
		sprite.setAnimation("water_right");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
