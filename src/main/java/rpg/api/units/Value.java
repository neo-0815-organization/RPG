package rpg.api.units;

public class Value {
	private double			value;
	private final double[]	factors;
	
	protected Value(final double value, final double... factors) {
		this.value = value;
		this.factors = factors;
	}
	
	public void setValue(final double value) {
		this.value = value;
	}
	
	protected double getValue(final int i) {
		return value * (i == 0 ? 1 : factors[i - 1]);
	}
	
	@Override
	public Value clone() {
		return new Value(value, factors);
	}
}
