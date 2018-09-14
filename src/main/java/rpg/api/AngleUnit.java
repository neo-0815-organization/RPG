package rpg.api;

/**
 * Enum representing different angle units.
 * 
 * @author Tim Ludwig
 *         14.09.2018
 */
public enum AngleUnit {
	TURN,
	DEGREE,
	RADIAN,
	GRADIAN;
	
	/**
	 * returns the value of a full circle in this {@link AngleUnit}.
	 * 
	 * @return the value of a full circle
	 */
	public double getFullAngle() {
		switch(this) {
			case DEGREE:
				return 360;
			case GRADIAN:
				return 400;
			case RADIAN:
				return 2 * Math.PI;
			case TURN:
			default:
				return 1;
		}
	}
	
	/**
	 * Converts the value from this {@link AngleUnit} to another.
	 * 
	 * @param angle
	 *            the value to convert
	 * @param to
	 *            the unit to convert to
	 * @return the convertet value
	 */
	public double convert(final double angle, final AngleUnit to) {
		return angle * to.getFullAngle() / getFullAngle();
	}
}
