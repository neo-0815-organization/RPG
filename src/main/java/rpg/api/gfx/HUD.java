package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import rpg.api.filehandling.ResourceGetter;
import rpg.api.vector.Vec2D;

public class HUD implements IDrawable {
	private final IImage overlay = new IImage() {
		private final BufferedImage image = ResourceGetter.getImage("/assets/textures/hud/overlay.png");
		
		@Override
		public void draw(final Graphics2D g2d) {
			draw(g2d, Vec2D.ORIGIN);
		}
		
		@Override
		public BufferedImage getImage() {
			return image;
		}
	};
	
	@Override
	public void draw(final Graphics2D g2d) {
		overlay.draw(g2d);
	}
}
