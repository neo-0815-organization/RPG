package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import rpg.api.filehandling.ResourceGetter;

public class HUD implements IDrawable {
	private static final BufferedImage overlay = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/overlay.png"));
	
	@Override
	public void draw(final Graphics2D g2d) {
		g2d.drawImage(overlay, 0, 0, null);
	}
}
