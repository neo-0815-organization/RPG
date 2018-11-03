package rpg.api.scene;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rpg.api.gfx.IImage;
import rpg.api.tile.Tile;
import rpg.api.vector.Vec2D;

/**
 * Background class contains the background image and all {@link Tile}s.
 *
 * @author Erik Diers, Jan Unterhuber
 */
public class Background implements IImage {
	private BufferedImage background;
	private final ArrayList<Tile> tiles;
	
	public Background() {
		tiles = new ArrayList<>();
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		draw(g2d, Vec2D.ORIGIN);
		
		for(final Tile t : tiles)
			t.draw(g2d);
	}
	
	@Override
	public BufferedImage getImage() {
		return background;
	}
}
