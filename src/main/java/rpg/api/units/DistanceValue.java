package rpg.api.units;

/**
 * Sub-class of {@link Value} to convert between diffenernt units of distance
 *
 * @author Tim Ludwig
 */
public class DistanceValue extends Value {
	private static final double		DISTANCE_PIXEL_FACTOR	= 32;
	private static final double[]	DISTANCE_FACTORS		= new double[] {DISTANCE_PIXEL_FACTOR};
	
	public DistanceValue(final double value) {
		super(value, DISTANCE_FACTORS);
	}
	
	public DistanceValue(final int value) {
		super(value / DISTANCE_PIXEL_FACTOR, DISTANCE_FACTORS);
	}
	
	/**
	 * Gets the value stored in this {@link DistanceValue} converted to tiles
	 *
	 * @return the converted value
	 */
	public double getValueTiles() {
		return getValue(0);
	}
	
	/**
	 * Gets the value stored in this {@link DistanceValue} converted to pixels on
	 * screen
	 *
	 * @return the converted value
	 */
	public int getValuePixel() {
		return (int) getValue(1);
	}
}
