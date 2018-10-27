package rpg.api;

/**
 * Enum representing the four main directions north west south and east.
 * 
 * @author Tim Ludwig
 */
public enum Direction {
	NORTH(Vec2D.createAL(.5 * Math.PI, 1)),
	EAST(Vec2D.createAL(0, 1)),
	SOUTH(Vec2D.createAL(1.5 * Math.PI, 1)),
	WEST(Vec2D.createAL(Math.PI, 1)),
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
