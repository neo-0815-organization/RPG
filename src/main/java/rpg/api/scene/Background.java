package rpg.api.scene;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rpg.RPG;
import rpg.api.tile.Tile;

/**
 * Background class contains background image and all "tiles".
 */
public class Background extends Scene {
	private BufferedImage background;
	private ArrayList<Tile> tiles;
	
	public Background() {
		tiles = new ArrayList<>();
	}
	@Override
	public void draw(Graphics2D g2d) {
		final Rectangle screen = new Rectangle(Camera.loc.getX(), Camera.loc.getY(), RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
		
		g2d.drawImage(background.getSubimage(Camera.loc.getX(), Camera.loc.getY(), RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT), 0, 0, null);
		
		for(int i = 0; i < tiles.size(); i++) {
			final Tile current = tiles.get(i);
			
			if(screen.intersects(current.getCurrentImageBoundings())) g2d.drawImage(current.getLook(), current.getLocation().getX() - Camera.loc.getX(), current.getLocation().getY() - Camera.loc.getY(), null);
		}
	}
}
