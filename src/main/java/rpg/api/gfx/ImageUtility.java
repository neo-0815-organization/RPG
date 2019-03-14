package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import rpg.Statics;

public class ImageUtility {
	
	public static BufferedImage scale(final BufferedImage image) {
		return scale(image, Statics.scale);
	}
	
	public static BufferedImage scale(final BufferedImage image, final int scale) {
		return scale(image, image.getWidth() * scale, image.getHeight() * scale);
	}
	
	public static BufferedImage scale(final BufferedImage image, final double scale) {
		return scale(image, (int) Math.round(image.getWidth() * scale), (int) Math.round(image.getHeight() * scale));
	}
	
	public static BufferedImage scale(final BufferedImage image, final int newWidth, final int newHeight) {
		return scale(image, newWidth, newHeight, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	public static BufferedImage scale(final BufferedImage image, final int newWidth, final int newHeight, final Object interpolation) {
		return scale(image, newWidth, newHeight, interpolation, BufferedImage.TYPE_INT_ARGB);
	}
	
	public static BufferedImage scale(final BufferedImage image, final int newWidth, final int newHeight, final Object interpolation, final int type) {
		if(image.getWidth() == newWidth && image.getHeight() == newHeight) return image;
		
		final BufferedImage newImage = new BufferedImage(newWidth, newHeight, type);
		final Graphics2D g = newImage.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
		g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
		
		return newImage;
	}
	
	public static BufferedImage rotate(final BufferedImage image, final int angle) {
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
	
	public static boolean compare(final BufferedImage imageOne, final BufferedImage imageTwo) {
		if(imageOne == imageTwo) return true;
		if(imageOne == null || imageTwo == null) return false;
		if(imageOne.getWidth() != imageTwo.getWidth() || imageOne.getHeight() != imageTwo.getHeight()) return false;
		
		for(int x = 0; x < imageOne.getWidth(); x++)
			for(int y = 0; y < imageOne.getHeight(); y++)
				if(imageOne.getRGB(x, y) != imageTwo.getRGB(x, y)) return false;
			
		return true;
	}
}
