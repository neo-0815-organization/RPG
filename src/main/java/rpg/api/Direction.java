package rpg.api;

/**
 * Enum representing the four main directions north west south and east.
 * 
 * @author Tim Ludwig
 */
public enum Direction {
	NORTH(Vec2D.createAL(.5 * Math.PI, 1), 0),
	EAST(Vec2D.createAL(0, 1), 1),
	SOUTH(Vec2D.createAL(1.5 * Math.PI, 1), 2),
	WEST(Vec2D.createAL(Math.PI, 1), 3),
  
	NONE(Vec2D.createXY(0, 0), -1);
	
	private Vec2D vec;
	private byte id;
	
	private Direction(final Vec2D vec, final int id) {
		this.vec = vec;
		this.id = (byte) id;
	}
	
	public static Direction getDirectionById(final byte id) {
		for(final Direction d : values())
			if(d.id == id) return d;
		
		return NONE;
	}
	
	/**
	 * Returns the {@link Vec2D} pointing in this {@link Direction}.
	 * 
	 * @return the {@link Vec2D} pointing in this {@link Direction}
	 */
	public Vec2D getVector() {
		return vec.clone();
	}
	
	public byte getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return super.toString() + "[" + vec.getX() + ", " + vec.getY() + "]";
	}
}
