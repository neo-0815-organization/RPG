package rpg.api.scene;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rpg.api.gfx.IImage;
import rpg.api.tile.Tile;

/**
 * Background class contains background image and all "tiles".
 */
public class Background implements IImage {
	private BufferedImage	background;
	private ArrayList<Tile>	tiles;
	
	@Override
	public void draw(final Graphics2D g2d) {
		draw(g2d, 0, 0);
		
		for(final Tile t : tiles)
			t.draw(g2d);
	}
	
	@Override
	public BufferedImage getImage() {
		return background;
	}
}
