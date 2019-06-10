package rpg.api.tile.fluids;

import rpg.api.gfx.Sprite;
import rpg.api.tile.Fluid;
import rpg.api.vector.UnmodifiableVec2D;

public class FluidWater extends Fluid {
	
	public FluidWater() {
		super(UnmodifiableVec2D.createXY(0.5, 0));
		
		setHitbox(1, 1);
		
		sprite = new Sprite("fluids/water");
		addAnims0("water_right");
	}
}
