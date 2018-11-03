package rpg.api.tile;

import java.awt.Graphics2D;

import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;
import rpg.api.vector.ModifiableVec2D;

public abstract class Tile implements ISprite {
	protected ModifiableVec2D location;
	protected Sprite sprite;
	protected Hitbox hbox;
	
	/**
	 * Gets the location of this tile {@link ModifiableVec2D}
	 * 
	 * @return the location of this tile
	 */
	public ModifiableVec2D getLocation() {
		return location;
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		draw(g2d, location);
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	public Hitbox getHitbox() {
		return hbox;
	}
}
