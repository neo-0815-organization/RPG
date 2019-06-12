package rpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.JLabel;

public class Statics {
	public static final Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final double scaleConst = 1.75d;
	public static final double scale = frameSize.width / 1920d * scaleConst;
	
	public static final Dimension gameSize = new Dimension((int) (frameSize.width / scale), (int) (frameSize.height / scale));
	
	public static int scale(final double value) {
		return (int) Math.round(value * scale);
	}
	
	public static final Font defaultFont = new Font("Sans", 0, scale(24 / scaleConst));
	
	public static Font defaultFont(final int style) {
		return defaultFont.deriveFont(style);
	}
	
	public static Font defaultFont(final double size) {
		return defaultFont.deriveFont((float) (scale * size / scaleConst));
	}
	
	public static Font defaultFont(final int style, final double size) {
		return defaultFont(size).deriveFont(style);
	}
	
	public static final BufferedImage missingImage = missingImage(32, 32);
	
	public static BufferedImage missingImage(final int width, final int height) {
		final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g2d = img.createGraphics();
		
		final int halfWidth = width / 2, halfHeight = height / 2;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, halfWidth, halfHeight);
		g2d.fillRect(halfWidth, halfHeight, halfWidth, halfHeight);
		
		g2d.setColor(new Color(0.5019608f, 0.0f, 0.5019608f)); // PURPLE
		g2d.fillRect(halfWidth, 0, halfWidth, halfHeight);
		g2d.fillRect(0, halfHeight, halfWidth, halfHeight);
		
		g2d.dispose();
		return img;
	}
	
	public static String formatToWidth(String txt, int width, Font font) {
		return formatToWidth(txt, width, font, "\n");
	}
	
	public static String formatToWidth(String txt, int width, Font font, String seperator) {
		final char widestChar = 'W';
		final int charWidth = new JLabel().getFontMetrics(font).stringWidth(widestChar + "");
		final int charsPerRow = (int) ((int) (width / (double) charWidth) * 1.25);
		
		for(int currentPos = charsPerRow; currentPos < txt.length(); currentPos += charsPerRow) {
			int spacePos = 0, lastSpacePos = 0;
			while((spacePos = txt.indexOf(' ', spacePos)) != -1 && spacePos < currentPos) {
				lastSpacePos = spacePos;
				spacePos++;
			}
			txt = txt.substring(0, lastSpacePos) + seperator + txt.substring(lastSpacePos);
			currentPos += 2;
		}
		
		return txt;
	}
	
	public static String formatToWidthAsHTML(String txt, final int width, final Font font) {
		final char widestChar = 'W';
		final int charWidth = new JLabel().getFontMetrics(font).stringWidth(widestChar + "");
		final int charsPerRow = (int) ((int) (width / (double) charWidth) * 1.25);
		
		for(int currentPos = charsPerRow; currentPos < txt.length(); currentPos += charsPerRow) {
			int spacePos = 0, lastSpacePos = 0;
			while((spacePos = txt.indexOf(' ', spacePos)) != -1 && spacePos < currentPos) {
				lastSpacePos = spacePos;
				spacePos++;
			}
			txt = txt.substring(0, lastSpacePos) + "<br>" + txt.substring(lastSpacePos);
			currentPos += 2;
		}
		
		return "<html>" + txt + "</html>";
	}
	
	public static File executionDir = new File("/");
	
	static {
		try {
			executionDir = new File(URLDecoder.decode(Statics.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8"));
		}catch(final UnsupportedEncodingException e) {
			executionDir = new File(Statics.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		}
	}
	
	public static File fileFromExecutionDir(final String name) {
		return fileFromExecutionDir("", name);
	}
	
	public static File fileFromExecutionDir(final String path, final String name) {
		return new File(executionDir, path + "/" + name);
	}
}
