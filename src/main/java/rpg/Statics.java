package rpg;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class Statics {
	public static final Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final double scaleConst = 1.75d;
	public static final double scale = frameSize.width / 1920d * scaleConst;
	
	public static int scale(final double value) {
		return (int) Math.round(value * scale);
	}
	
	public static final Font defaultFont = new Font("Arial", 0, 24);
}
