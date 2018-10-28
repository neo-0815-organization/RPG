package rpg.api.tile;

import java.awt.Polygon;

import rpg.api.Vec2D;

public class Hitbox {
	private final int shape;
	private final Vec2D loc;
	private Polygon realHitbox;
	/*
	 * shape=0 means Rectangle shape=1 means another shape etc.
	 */
	
	public Hitbox(final int shape, final Vec2D loc, final int width, final int height) {
		this.shape = shape;
		this.loc = loc;
		
		if(shape == 0) {
			final int[] xPoints = new int[2];
			final int[] yPoints = new int[2];
			final int nPoints = 4;
			
			xPoints[0] = loc.getX().getValuePixel();
			xPoints[1] = loc.getX().getValuePixel() + width;
			yPoints[0] = loc.getY().getValuePixel();
			yPoints[1] = loc.getY().getValuePixel() + height;
			
			realHitbox = new Polygon(xPoints, yPoints, nPoints);
		}else if(shape == 1) {
			
		}
	}
	
	public Polygon getRealHitbox() {
		return realHitbox;
	}
}
