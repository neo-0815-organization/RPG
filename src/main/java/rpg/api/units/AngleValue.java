package rpg.api.units;

/**
 * Sub-class of {@link Value} to convert between diffenernt units of rotation
 *
 * @author Tim Ludwig
 */
public class AngleValue extends Value {
	private static final double		TURN_RADIAN_FACTOR	= 2 * Math.PI;
	private static final double		TURN_DEGREE_FACTOR	= 360;
	private static final double[]	ANGLE_FACTORS		= new double[] {TURN_RADIAN_FACTOR, TURN_DEGREE_FACTOR};
	
	public AngleValue(final double value) {
		super(value, ANGLE_FACTORS);
	}
	
	/**
	 * Gets the value stored in this {@link AngleValue} converted to turns
	 * (proportion of a whole circle)
	 *
	 * @return the converted value
	 */
	public double getValueTurn() {
		return getValue(0);
	}
	
	/**
	 * Gets the value stored in this {@link AngleValue} converted to radians
	 *
	 * @return the converted value
	 */
	public double getValueRadian() {
		return getValue(1);
	}
	
	/**
	 * Gets the value stored in this {@link AngleValue} converted to degrees
	 *
	 * @return the converted value
	 */
	public double getValueDegree() {
		return getValue(2);
	}
}
