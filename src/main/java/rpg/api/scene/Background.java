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

import rpg.api.collision.Hitbox;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.IImage;
import rpg.api.tile.Tile;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;
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
		final ZipInputStream zis = new ZipInputStream(ResourceGetter.getResource("/assets/worlds/" + name + ".world"));
		final HashMap<String, ByteArrayInputStream> streams = new HashMap<>();
		
		ZipEntry entry;
		ByteArrayOutputStream baos;
		while((entry = zis.getNextEntry()) != null) {
			baos = new ByteArrayOutputStream();
			
			while(zis.available() > 0)
				baos.write(zis.read());
			
			streams.put(entry.getName(), new ByteArrayInputStream(baos.toByteArray(), 0, baos.size() - 1));
		}
		
		background = ImageIO.read(streams.get("background"));
		
		// read mapping
		final ByteArrayInputStream mappingStream = streams.get("mapping");
		final HashMap<Integer, HashMap<Integer, String>> neededTiles = new HashMap<>();
		
		final int mapCount = mappingStream.read();
		int layerID, length, tileID;
		HashMap<Integer, String> map;
		byte[] name;
		for(int i = 0; i < mapCount; i++) {
			layerID = mappingStream.read();
			length = mappingStream.read();
			
			map = new HashMap<>(length);
			
			for(int j = 0; j < length; j++) {
				tileID = mappingStream.read();
				name = new byte[mappingStream.read()];
				
				mappingStream.read(name);
				
				map.put(tileID, new String(name));
			}
			
			neededTiles.put(layerID, map);
		}
		
		// read & create fluids
		final ByteArrayInputStream fluidStream = streams.get("fluids");
		
		final int fluidCount = fluidStream.read();
		Tile fluid;
		for(int i = 0; i < fluidCount; i++) {
			final ModifiableVec2D location = ModifiableVec2D.createXY(fluidStream.read(), fluidStream.read());
			final int id = fluidStream.read();
			
			fluid = new Tile() {};
			
			fluids.add(fluid);
		}
		
		// read & create tiles
		final ByteArrayInputStream tileStream = streams.get("tiles");
		
		final int tileCount = tileStream.read();
		Tile tile;
		for(int i = 0; i < tileCount; i++) {
			final ModifiableVec2D location = ModifiableVec2D.createXY(tileStream.read(), tileStream.read());
			final int id = tileStream.read();
			
			tile = new Tile() {};
			
			tiles.add(tile);
		}
		
		// read & create hitboxes
		final ByteArrayInputStream hitboxStream = streams.get("hitboxes");
		
		final int hitboxCount = hitboxStream.read();
		final Hitbox hitbox;
		for(int i = 0; i < hitboxCount; i++) {
			final ModifiableVec2D location = ModifiableVec2D.createXY(hitboxStream.read(), hitboxStream.read());
			final byte[] typeName = new byte[hitboxStream.read()];
			
			hitboxStream.read(typeName);
			
			final int pointCount = hitboxStream.read();
			final ArrayList<UnmodifiableVec2D> points = new ArrayList<>(pointCount);
			
			for(int j = 0; j < pointCount; j++)
				points.add(UnmodifiableVec2D.createXY(Double.longBitsToDouble(hitboxStream.read() << 32 | hitboxStream.read() & 0xFFFFFFFFL), Double.longBitsToDouble(hitboxStream.read() << 32 | hitboxStream.read() & 0xFFFFFFFFL)));
			
			switch(new String(typeName)) {
				case "rectangle":
					break;
				case "triangle":
					break;
				case "circle":
					break;
			}
		}
		
		zis.close();
		streams.values().parallelStream().forEach(bais -> {
			try {
				bais.close();
			}catch(final IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public BufferedImage getImage() {
		return background;
	}
}
