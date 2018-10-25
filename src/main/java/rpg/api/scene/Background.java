package rpg.api.scene;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rpg.RPG;
import rpg.api.gfx.IDrawable;
import rpg.api.tile.Tile;

/**
 * Background class contains background image and all "tiles".
 */
public class Background implements IDrawable {
	private BufferedImage	background;
	private ArrayList<Tile>	tiles;
	
	@Override
	public void draw(final Graphics2D g2d) {
		final Rectangle screen = new Rectangle(Camera.loc.getX().getValuePixel(), Camera.loc.getY().getValuePixel(), RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
		
		g2d.drawImage(background.getSubimage(Camera.loc.getX().getValuePixel(), Camera.loc.getY().getValuePixel(), RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT), 0, 0, null);
		
		for(int i = 0; i < tiles.size(); i++) {
			final Tile current = tiles.get(i);
			
			if(screen.intersects(current.getCurrentImageBoundings())) g2d.drawImage(current.getLook(), current.getLocation().getX().getValuePixel() - Camera.loc.getX().getValuePixel(), current.getLocation().getY().getValuePixel() - Camera.loc.getY().getValuePixel(), null);
		}
	}
}
