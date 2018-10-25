package rpg.api.units;

public class DistanceValue extends Value {
	private static final double		DISTANCE_PIXEL_FACTOR	= 32;
	private static final double[]	DISTANCE_FACTORS		= new double[] {DISTANCE_PIXEL_FACTOR};
	
	public DistanceValue(final double value) {
		super(value, DISTANCE_FACTORS);
	}
	
	public double getValueTiles() {
		return getValue(0);
	}
	
	public int getValuePixel() {
		return (int) getValue(1);
	}
}
