package rpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Statics {
	public static final Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final double scaleConst = 1.75d;
	public static final double scale = frameSize.width / 1920d * scaleConst;
	
	public static int scale(final double value) {
		return (int) Math.round(value * scale);
	}
	
	public static final Font defaultFont = new Font("Arial", 0, 24);
	
	public static final BufferedImage missingImage = missingImage(32, 32);
	
	public static BufferedImage missingImage(final int width, final int height) {
		final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g2d = img.createGraphics();
		
		final int halfWidth = width / 2, halfHeight = height / 2;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, halfWidth, halfHeight);
		g2d.fillRect(halfWidth, halfHeight, halfWidth, halfHeight);
		
		g2d.setColor(new Color(0.5019608f, 0.0f, 0.5019608f)); // PURPLE
		g2d.fillRect(halfWidth, 0, halfWidth, halfHeight);
		g2d.fillRect(0, halfHeight, halfWidth, halfHeight);
		
		g2d.dispose();
		return img;
	}
}
