package rpg.api.collision;

import rpg.api.units.DistanceValue;
import rpg.api.vector.Vec2D;

/**
 * The class Hitbox used only in collision management.
 * 
 * @author Tim Ludwig
 */
public class Hitbox {
	private final DistanceValue width, height;
	
	public Hitbox(final int tileWidth, final int tileHeight) {
		this(new DistanceValue((double) tileWidth), new DistanceValue((double) tileHeight));
	}
	
	public Hitbox(final double tileWidth, final double tileHeight) {
		this(new DistanceValue(tileWidth), new DistanceValue(tileHeight));
	}
	
	public Hitbox(final DistanceValue width, final DistanceValue height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean checkCollision(final Vec2D<?> pos, final Hitbox collBox, final Vec2D<?> collPos) {
		//@formatter:off
		 final double
		 x1 = pos.getValueX(),
		 y1 = pos.getValueY(),
		 w1 = width.getValueTiles(),
		 h1 = height.getValueTiles(),
		 x2 = collPos.getValueX(),
		 y2 = collPos.getValueY(),
		 w2 = collBox.width.getValueTiles(),
		 h2 = collBox.height.getValueTiles();
		 
		 return (	x2 < x1 && x1 < x2 + w2
				 || x1 < x2 && x2 < x1 + w1)
			     &&(y2 < y1 && y1 < y2 + h2
			     || y1 < y2 && y2 < y1 + h1);
		 //@formatter:on
	}
	
	public DistanceValue getWidth() {
		return width;
	}
	
	public DistanceValue getHeight() {
		return height;
	}
}
