package rpg.worldcreator;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

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
			private int count = 0;
			
			@Override
			public void accept(String name) {
				image = getImage(assetsFolder, dir + "/" + name);
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
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static BufferedImage scaleImage(final BufferedImage image, final int newWidth, final int newHeight) {
		return scaleImage(image, newWidth, newHeight, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	public static BufferedImage scaleImage(final BufferedImage image, final int newWidth, final int newHeight, final Object interpolation) {
		if(image.getWidth() == newWidth && image.getHeight() == newHeight) return image;
		
		final BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
		final Graphics2D g = newImage.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
		g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
		
		return newImage;
	}
	
	public static BufferedImage rotateImage(final BufferedImage image, final int angle) {
		if(angle % 360 == 0) return image;
		
		final BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		final Graphics2D g = newImage.createGraphics();
		final AffineTransform originalAT = g.getTransform();
		
		final AffineTransform rot = new AffineTransform();
		rot.rotate(Math.toRadians(angle), image.getWidth() / 2, image.getHeight() / 2);
		g.transform(rot);
		
		g.drawImage(image, 0, 0, null);
		
		g.setTransform(originalAT);
		g.dispose();
		
		return newImage;
	}
	
	public static boolean compareImages(final BufferedImage imageOne, final BufferedImage imageTwo) {
		if(imageOne == imageTwo) return true;
		if(imageOne == null ^ imageTwo == null) return false;
		if(imageOne.getWidth() != imageTwo.getWidth() || imageOne.getHeight() != imageTwo.getHeight()) return false;
		
		for(int x = 0; x < imageOne.getWidth(); x++)
			for(int y = 0; y < imageOne.getHeight(); y++)
				if(imageOne.getRGB(x, y) != imageTwo.getRGB(x, y)) return false;
			
		return true;
	}
	
	public static int getLayerCount() {
		return 3;
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
