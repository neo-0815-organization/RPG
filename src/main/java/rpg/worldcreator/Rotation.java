package rpg.worldcreator;

public enum Rotation {
	NONE(0, 0),
	LEFT(1, 90),
	UPSIDE_DOWN(2, 180),
	RIGHT(3, 270);
	
	private final int id, angle;
	
	private Rotation(final int id, final int angle) {
		this.id = id;
		this.angle = angle;
	}
	
	public Rotation rotateClockwise() {
		switch(this) {
			case NONE:
				return LEFT;
			case LEFT:
				return UPSIDE_DOWN;
			case UPSIDE_DOWN:
				return RIGHT;
			case RIGHT:
				return NONE;
		}
		
		return null;
	}
	
	public Rotation rotateCounterclockwise() {
		switch(this) {
			case NONE:
				return RIGHT;
			case RIGHT:
				return UPSIDE_DOWN;
			case UPSIDE_DOWN:
				return LEFT;
			case LEFT:
				return NONE;
		}
		
		return null;
	}
	
	public Rotation rotate(final int direction) {
		if(direction > 0) return rotateClockwise().rotate(direction - 1);
		if(direction < 0) return rotateCounterclockwise().rotate(direction + 1);
		
		return this;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public static Rotation getById(final int id) {
		switch(id % 4) {
			case 0:
				return NONE;
			case 1:
				return LEFT;
			case 2:
				return UPSIDE_DOWN;
			case 3:
				return RIGHT;
		}
		
		return null;
	}
}
