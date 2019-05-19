package rpg.worldcreator;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import rpg.Logger;
import rpg.api.gfx.ImageUtility;

public class RPGWorldCreator {
	private static final HashMap<String, BufferedImage> images = new HashMap<>();
	private static final TwoValueMap<String, Integer, BufferedImage> fluids = new TwoValueMap<>(), textures = new TwoValueMap<>(), tiles = new TwoValueMap<>();
	private static final boolean darkMode = false;
	
	public static final String assetsFolder = "/assets/worldcreator/";
	
	private static WorldCreatorFrame wcFrame;
	
	public static void main(final String[] args) {
		loadImages();
		
		wcFrame = new WorldCreatorFrame();
		wcFrame.setVisible(true);
	}
	
	private static void loadImages() {
		loadPictures("fluids", fluids, new HashMap<>());
		loadPictures("textures", textures, new HashMap<>());
		loadPictures("tiles", tiles, new HashMap<>());
	}
	
	protected static void loadPictures(final String dir, final TwoValueMap<String, Integer, BufferedImage> map, final HashMap<String, Integer> ids) {
		final Consumer<String> consumer = new Consumer<String>() {
			private BufferedImage image = null;
			private int count = 0, size = 0;
			
			@Override
			public void accept(String name) {
				image = getImage(assetsFolder, dir + "/" + name);
				
				if(dir.equals("tiles")) {
					size = Math.max(image.getWidth(), image.getHeight());
					
					if(size > Data.tileSize) image = ImageUtility.scale(image, Data.tileSize / (double) size);
				}else if(dir.equals("fluids")) image = image.getSubimage(0, 0, Data.tileSize, Data.tileSize);
				
				name = name.replace(".png", "");
				
				if(ids.containsKey(name)) map.put(name, ids.get(name), image);
				else {
					while(ids.containsValue(count))
						count++;
					
					map.put(name, count, image);
				}
				
				count++;
			}
		};
		
		new BufferedReader(new InputStreamReader(RPGWorldCreator.class.getResourceAsStream(assetsFolder + dir + "/images.txt"))).lines().parallel().forEach(consumer);
	}
	
	public static BufferedImage getImage(final String file) {
		return getImage("/", file);
	}
	
	public static BufferedImage getImage(final String dir, final String file) {
		if(images.containsKey(file)) return images.get(file);
		
		try {
			final BufferedImage image = ImageIO.read(RPGWorldCreator.class.getResourceAsStream(dir + (dir.endsWith("/") ? "" : "/") + file));
			
			images.put(file, image);
			
			return image;
		}catch(final Exception e) {
			Logger.error(e);
		}
		
		return null;
	}
	
	public static int getLayerCount() {
		return 3;
	}
	
	public static int[] getMappingIndeces() {
		return new int[] { 0, 2 };
	}
	
	public static String getMapDir(final int index) {
		if(getImageMap(index) == null) return null;
		
		switch(index) {
			case 0:
				return "fluids";
			case 1:
				return "textures";
			case 2:
				return "tiles";
		}
		
		return null;
	}
	
	public static TwoValueMap<String, Integer, BufferedImage> getImageMap(final int index) {
		switch(index) {
			case 0:
				return fluids;
			case 1:
				return textures;
			case 2:
				return tiles;
		}
		
		return null;
	}
	
	public static TwoValueMap<String, Integer, BufferedImage> getFluids() {
		return fluids;
	}
	
	public static TwoValueMap<String, Integer, BufferedImage> getTextures() {
		return textures;
	}
	
	public static TwoValueMap<String, Integer, BufferedImage> getTiles() {
		return tiles;
	}
	
	public static boolean isDarkmode() {
		return darkMode;
	}
}
