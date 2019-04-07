package rpg.api.units;

/**
 * Value is used to transfer measurments between different proportionaly
 * dependent units in one system
 * of measurment
 *
 * @author Tim Ludwig
 */
public class Value {
	private double			value;
	private final double[]	factors;
	
	protected Value(final double value, final double... factors) {
		this.value = value;
		this.factors = factors;
	}
	
	/**
	 * Sets the base value of the measurment system.
	 *
	 * @param value
	 *     the value to set
	 */
	public void setValue(final double value) {
		this.value = value;
	}
	
	/**
	 * Gets the value stored in this {@link Value} converted to another unit
	 *
	 * @param i
	 *     the index of the unit to convert to
	 * @return the value converted to the specified unit
	 */
	protected double getValue(final int i) {
		return value * (i == 0 ? 1 : factors[i - 1]);
	}
	
	@Override
	public Value clone() {
		return new Value(value, factors);
	}
}
