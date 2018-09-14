package rpg.api;

public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST;
	
	public Vec2D getVector() {
		switch(this) {
			case NORTH:
				return Vec2D.create(0, 1);
			case EAST:
				return Vec2D.create(1, 0);
			case SOUTH:
				return Vec2D.create(0, -1);
			case WEST:
				return Vec2D.create(-1, 0);
			default:
				return Vec2D.create(0, 0);
		}
	}
}
