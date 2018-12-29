package rpg.api.scene;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import rpg.api.filereading.ResourceGetter;
import rpg.api.gfx.IImage;
import rpg.api.tile.Tile;
import rpg.api.vector.Vec2D;

/**
 * Background class contains the background image and all {@link Tile}s.
 *
 * @author Erik Diers, Jan Unterhuber, Neo Hornberger
 */
public class Background implements IImage {
	private BufferedImage background;
	private final ArrayList<Tile> tiles;
	private final String name;
	
	public Background() {
		this(null);
	}
	
	public Background(final String name) {
		this.name = name;
		
		tiles = new ArrayList<>();
		
		if(name != null) try {
			loadFromFile();
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		if(name == null) return;
		
		draw(g2d, Vec2D.ORIGIN);
		
		for(final Tile t : tiles)
			t.draw(g2d);
	}
	
	public void updateBackground(final double deltaTime) {
		for(final Tile t : tiles)
			t.update(deltaTime);
	}
	
	private void loadFromFile() throws IOException {
		final ZipInputStream zis = new ZipInputStream(ResourceGetter.resource("/assets/worlds/" + name + ".world"));
		
		ZipEntry entry;
		while((entry = zis.getNextEntry()) != null)
			switch(entry.getName()) {
				case "background":
					background = ImageIO.read(zis);
					break;
			}
	}
	
	@Override
	public BufferedImage getImage() {
		return background;
	}
}
