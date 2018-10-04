package rpg.api.tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import rpg.api.Location;

public abstract class Tile {
	protected Location loc;
	private Hitbox hbox;
	
	public Rectangle getCurrentImageBoundings() {
		return new Rectangle(loc.getX(), loc.getY(), getLook().getWidth(), getLook().getHeight());
	}
	
	public abstract BufferedImage getLook();
	
	public Location getLocation() {
		return loc;
	}
	
	public Hitbox getHitbox() {
		return hbox ;
	}
}
