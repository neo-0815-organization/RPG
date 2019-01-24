package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Utility class for images.
 * 
 * @author Neo Hornberger
 */
public class ImageUtility {
	
	/**
	 * Scales a {@link BufferedImage}.
	 * 
	 * @param image the {@link BufferedImage} to scale
	 * @param newWidth the width to scale the {@link BufferedImage} to
	 * @param newHeight the height to scale the {@link BufferedImage} to
	 * 
	 * @return the scaled {@link BufferedImage}
	 * 
	 * @see scale(BufferedImage, int, int, Object)
	 */
	public static BufferedImage scale(final BufferedImage image, final int newWidth, final int newHeight) {
		return scale(image, newWidth, newHeight, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	/**
	 * Scales a {@link BufferedImage}.
	 * 
	 * @param image the {@link BufferedImage} to scale
	 * @param newWidth the width to scale the {@link BufferedImage} to
	 * @param newHeight the height to scale the {@link BufferedImage} to
	 * @param valueInterpolation the {@link RenderingHints} to use
	 * 
	 * @return the scaled {@link BufferedImage}
	 */
	public static BufferedImage scale(final BufferedImage image, final int newWidth, final int newHeight, final Object valueInterpolation) {
		if(image.getWidth() == newWidth && image.getHeight() == newHeight) return image;
		
		final BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
		final Graphics2D g = newImage.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, valueInterpolation);
		g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
		
		return newImage;
	}
	
	/**
	 * Rotates a {@link BufferedImage}.
	 * 
	 * @param image the {@link BufferedImage} to rotate
	 * @param angle the angle to rotate by (clockwise in degrees)
	 * @return the rotated {@link BufferedImage}
	 */
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
	
	/**
	 * Compares two {@link BufferedImage}s
	 * @param imageOne one {@link BufferedImage}
	 * @param imageTwo another {@link BufferedImage}
	 * @return {@code true} <ul><li>if the contents of the two {@link BufferedImage}s are equal</li><li>if {@code (imageOne == imageTwo) == true}</li></ul>  
	 */
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
