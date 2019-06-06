package rpg.api.tile.fluids;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Fluid;
import rpg.api.vector.UnmodifiableVec2D;

public class FluidWater extends Fluid {

	public FluidWater() {
		super(UnmodifiableVec2D.createXY(1.5, 0));

		setHitbox(1, 1);

		sprite = new Sprite("fluids/water");
		addAnims0("water_right");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
