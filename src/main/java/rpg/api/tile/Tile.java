package rpg.api.tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import rpg.api.Vec2D;

public abstract class Tile {
	protected Vec2D loc;
	
	public Rectangle getCurrentImageBoundings() {
		return new Rectangle(loc.getX().getValuePixel(), loc.getY().getValuePixel(), getLook().getWidth(), getLook().getHeight());
	}
	
	public abstract BufferedImage getLook();
	
	public Vec2D getLocation() {
		return loc;
	}
}
