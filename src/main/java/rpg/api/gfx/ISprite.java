package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import rpg.RPG;
import rpg.api.Vec2D;
import rpg.api.scene.Camera;

public interface ISprite extends IDrawable {
	public Sprite getSprite();
	
	public default Rectangle getBounds() {
		return getSprite().getBounds();
	}
	
	public default void draw(final Graphics2D g2d, final Vec2D location) {
		final int x = location.getX().getValuePixel(),
				y = location.getY().getValuePixel(),
				camX = Camera.loc.getX().getValuePixel(),
				camY = Camera.loc.getY().getValuePixel(),
				w = (int) getBounds().getWidth(),
				h = (int) getBounds().getHeight(),
				camXW = camX + RPG.SCREEN_WIDTH,
				camYH = camY + RPG.SCREEN_HEIGHT;
		
		if((x < camX && camX < w || x < camXW && camXW < w) && (x < camY && camY < h || x < camYH && camYH < h)) g2d.drawImage(getSprite().getCurrentAnimationFrame(), x, y, null);
	}
}
