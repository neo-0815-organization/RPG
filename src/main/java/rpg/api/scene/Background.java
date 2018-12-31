package rpg.api.scene;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	private final ArrayList<Tile> fluids, tiles;
	private final String name;
	
	public Background() {
		this(null);
	}
	
	public Background(final String name) {
		this.name = name;
		
		fluids = new ArrayList<>();
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
		
		for(final Tile t : fluids)
			t.draw(g2d);
		
		draw(g2d, Vec2D.ORIGIN);
		
		for(final Tile t : tiles)
			t.draw(g2d);
	}
	
	public void update(final double deltaTime) {
		for(final Tile t : fluids)
			t.update(deltaTime);
		
		for(final Tile t : tiles)
			t.update(deltaTime);
	}
	
	private void loadFromFile() throws IOException {
		final ZipInputStream zis = new ZipInputStream(ResourceGetter.resource("/assets/worlds/" + name + ".world"));
		final HashMap<String, ByteArrayInputStream> streams = new HashMap<>();
		
		ZipEntry entry;
		ByteArrayOutputStream baos;
		while((entry = zis.getNextEntry()) != null) {
			baos = new ByteArrayOutputStream();
			
			while(zis.available() > 0)
				baos.write(zis.read());
			
			streams.put(entry.getName(), new ByteArrayInputStream(baos.toByteArray()));
		}
		
		background = ImageIO.read(streams.get("background"));
		
		final HashMap<Integer, HashMap<Integer, String>> neededTiles = new HashMap<>();
		final ByteArrayInputStream stream = streams.get("mapping");
		
		// read mapping
		final int mapCount = stream.read();
		int layerID, length, tileID;
		HashMap<Integer, String> map;
		byte[] name;
		for(int i = 0; i < mapCount; i++) {
			layerID = stream.read();
			length = stream.read();
			
			map = new HashMap<>(length);
			
			for(int j = 0; j < length; j++) {
				tileID = stream.read();
				name = new byte[stream.read()];
				
				stream.read(name);
				
				map.put(tileID, new String(name));
			}
			
			neededTiles.put(layerID, map);
		}
		
		// read & create fluids
		
		// read & create tiles
	}
	
	@Override
	public BufferedImage getImage() {
		return background;
	}
}
