package rpg.api.tile;

import java.util.NoSuchElementException;

import rpg.Logger;
import rpg.RPG;
import rpg.api.collision.Hitbox;
import rpg.api.collision.ICollideable;
import rpg.api.entity.Entity;
import rpg.api.eventhandling.EventTrigger;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.scene.Camera;
import rpg.api.units.DistanceValue;
import rpg.api.vector.UnmodifiableVec2D;

/**
 * The abstract class Tile represents everything a player can interact with
 * which is not an {@link Entity}.
 *
 * @author Tim Ludwig, Erik Diers
 */
public abstract class Tile implements ISprite, ICollideable, EventTrigger {
	protected UnmodifiableVec2D location;
	protected Sprite sprite;
	protected Hitbox hitbox;
	
	/**
	 * Gets the location of this tile {@link UnmodifiableVec2D}
	 *
	 * @return the location of this tile
	 */
	public UnmodifiableVec2D getLocation() {
		return location;
	}
	
	/**
	 * Sets the location of this tile {@link UnmodifiableVec2D}
	 *
	 * @param location
	 *            the new location of this tile
	 */
	public void setLocation(final UnmodifiableVec2D location) {
		this.location = location;
	}
	
	/**
	 * This update-method is used to update tiles, whenever it is needed.
	 *
	 * @param deltaTime
	 *            time since last frame in sec.
	 */
	public void update(final double deltaTime) {
		sprite.update(deltaTime);
	}
	
	@Override
	public void draw(final DrawingGraphics g) {
		draw(g, getLocation());
		
		if(RPG.showHitbox) g.drawRect(location.getX().getValuePixel() - Camera.location.getX().getValuePixel(), location.getY().getValuePixel() - Camera.location.getY().getValuePixel(), hitbox.getWidth().getValuePixel(), hitbox.getHeight().getValuePixel());
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	/**
	 * Gets the {@link Hitbox} of this {@link Tile}.
	 *
	 * @return the {@link Hitbox} of this {@link Tile}
	 */
	@Override
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	protected void setHitbox(final double size) {
		setHitbox(size, size);
	}
	
	protected void setHitbox(final double width, final double height) {
		hitbox = new Hitbox(width, height);
	}
	
	/*
	 * First of {@code animations} will be the default animation
	 */
	protected void setSprite(final String sprite, final String... animations) {
		this.sprite = new Sprite("tiles/" + sprite);
		
		addAnims0(animations);
		hitbox = new Hitbox(new DistanceValue(this.sprite.getWidth()), new DistanceValue(this.sprite.getHeight()));
	}
	
	/*
	 * First of {@code animations} will be the default animation
	 */
	protected void setSprite(final String sprite, final SpriteTheme theme, final String... animations) {
		this.sprite = new Sprite("tiles/" + sprite, theme);
		
		addAnims0(animations);
		hitbox = new Hitbox(new DistanceValue(this.sprite.getWidth()), new DistanceValue(this.sprite.getHeight()));
	}
	
	/*
	 * First of {@code animations} will be the default animation
	 */
	protected void addAnims0(final String... anims) {
		for(final String anim : anims)
			try {
				sprite.addAnimation(anim);
			}catch(final NoSuchElementException e) {
				Logger.error("Error while adding animation '" + anim + "' to sprite '" + sprite.getName() + "'");
				Logger.error(e);
			}
		
		sprite.setAnimation(anims[0]);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
