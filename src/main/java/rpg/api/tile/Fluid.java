package rpg.api.tile;

import rpg.api.vector.Vec2D;

public abstract class Fluid extends Tile {
	public static Vec2D<?> acceleration;

	public Fluid(final Vec2D<?> acceleration) {
		Fluid.acceleration = acceleration;
	}
}
