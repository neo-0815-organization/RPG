package rpg.api;

import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;

/**
 * Enum representing the four main directions north west south and east.
 * 
 * @author Tim Ludwig
 */
public enum Direction {
	NORTH(UnmodifiableVec2D.createAL(.5 * Math.PI, 1), 0),
	EAST(UnmodifiableVec2D.createAL(0, 1), 1),
	SOUTH(UnmodifiableVec2D.createAL(1.5 * Math.PI, 1), 2),
	WEST(UnmodifiableVec2D.createAL(Math.PI, 1), 3),
	
	NONE(UnmodifiableVec2D.createXY(0, 0), -1);
	
	private UnmodifiableVec2D vec;
	private byte id;
	
	private Direction(final UnmodifiableVec2D vec, final int id) {
		this.vec = vec;
		this.id = (byte) id;
	}
	
	public static Direction getDirectionById(final byte id) {
		for(final Direction d : values())
			if(d.id == id) return d;
		
		return NONE;
	}
	
	/**
	 * Returns the {@link ModifiableVec2D} pointing in this {@link Direction}.
	 * 
	 * @return the {@link ModifiableVec2D} pointing in this {@link Direction}
	 */
	public ModifiableVec2D getVector() {
		return vec.toModifiable();
	}
	
	public byte getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return super.toString() + "[" + vec.getX() + ", " + vec.getY() + "]";
	}
}
