package rpg.api.tile;

import java.awt.Polygon;
import rpg.api.Location;

public class Hitbox {
	private int shape;
	private Location loc;
	private Polygon realHitbox;
	/*  
	 * shape=0 means Rectangle
	 * shape=1 means another shape
	 * etc.
	 */
	
	public Hitbox(int shape,Location loc,int width,int height) {
		this.shape = shape;
		this.loc = loc;
		if (shape==0) {
			int[] xPoints =new int[2];
			int[] yPoints =new int[2];
			int nPoints = 4;
			xPoints[0]=loc.getX();
			xPoints[1]=loc.getX()+width;
			yPoints[0]=loc.getY();
			yPoints[1]=loc.getY()+height;
			realHitbox = new Polygon(xPoints, yPoints, nPoints);
		}else if (shape==1) {
			
		}
	}
	
	public Polygon getRealHitbox() {
		return realHitbox;
	}
}
