package rpg.api.units;

public class AngleValue extends Value {
	private static final double		TURN_RADIAN_FACTOR	= 2 * Math.PI;
	private static final double		TURN_DEGREE_FACTOR	= 360;
	private static final double[]	ANGLE_FACTORS		= new double[] {TURN_RADIAN_FACTOR, TURN_DEGREE_FACTOR};
	
	public AngleValue(final double value) {
		super(value, ANGLE_FACTORS);
	}
	
	public double getValueTurn() {
		return getValue(0);
	}
	
	public double getValueRadian() {
		return getValue(1);
	}
	
	public double getValueDegree() {
		return getValue(2);
	}
}
