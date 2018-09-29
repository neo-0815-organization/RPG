package rpg.api;

/**
 * Enum representing the four main directions north west south and east.
 * 
 * @author Tim Ludwig
 */
public enum Direction {
	NORTH(Vec2D.NORTH),
	EAST(Vec2D.EAST),
	SOUTH(Vec2D.SOUTH),
	WEST(Vec2D.WEST),
	NONE(Vec2D.createXY(0, 0));
	
	private Vec2D vec;
	
	private Direction(final Vec2D vec) {
		this.vec = vec;
	}
	
	/**
	 * Returns the {@link Vec2D} pointing in this {@link Direction}.
	 * 
	 * @return the {@link Vec2D} pointing int this {@link Direction}
	 */
	public Vec2D getVector() {
		return vec.clone();
	}
	
	@Override
	public String toString() {
		return super.toString() + "[" + vec.getX() + ", " + vec.getY() + "]";
	}
}
