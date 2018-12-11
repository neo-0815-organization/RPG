package rpg.worldcreator;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

public class RPGWorldCreator {
	private static final HashMap<String, BufferedImage> images = new HashMap<>();
	private static final TwoValueMap<String, Integer, BufferedImage> textures = new TwoValueMap<>();
	private static final boolean darkMode = false;
	
	public static final String texturesFolder = "/assets/worldcreator/textures";
	
	private static WorldCreatorFrame wcFrame;
	
	public static void main(final String[] args) {
		loadTextures();
		
		wcFrame = new WorldCreatorFrame();
		wcFrame.setVisible(true);
	}
	
	private static void loadTextures() {
		Consumer<String> consumer = new Consumer<String>() {
			private BufferedImage image = null;
			private int count = 0;
			
			@Override
			public void accept(String name) {
				image = getImage(texturesFolder, name);
				
				textures.put(name.replace(".png", ""), count, image);
				
				count++;
			}
		};
		
		new BufferedReader(new InputStreamReader(RPGWorldCreator.class.getResourceAsStream(texturesFolder + "/textures.txt"))).lines().parallel().forEach(consumer);
	}
	
	public static BufferedImage getImage(final String file) {
		return getImage("/", file);
	}
	
	public static BufferedImage getImage(final String dir, final String file) {
		if(images.containsKey(file)) return images.get(file);
		
		try {
			final BufferedImage image = ImageIO.read(RPGWorldCreator.class.getResourceAsStream(dir + (dir.equals("/") ? "" : "/") + file));
			
			images.put(file, image);
			
			return image;
		}catch(final Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static BufferedImage scaleImage(final BufferedImage image, final int newWidth, final int newHeight) {
		final BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
		final Graphics2D g = newImage.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
		
		return newImage;
	}
	
	public static BufferedImage rotateImage(final BufferedImage image, final int angle) {
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
	
	public static TwoValueMap<String, Integer, BufferedImage> getTextures() {
		return textures;
	}
	
	public static boolean isDarkmode() {
		return darkMode;
	}
}
