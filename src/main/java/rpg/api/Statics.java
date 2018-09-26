package rpg.api;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Statics {
	public static BufferedImage defaultImage = new BufferedImage(64, 64, 1);
	
	static {
		final Graphics g = defaultImage.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 32, 32);
		
		g.setColor(Color.PINK);
		g.fillRect(31, 0, 32, 32);
		
		g.setColor(Color.BLACK);
		g.fillRect(31, 31, 32, 32);
		
		g.setColor(Color.PINK);
		g.fillRect(0, 31, 32, 32);
	}
}
