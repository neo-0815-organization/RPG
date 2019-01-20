package rpg.api.filereading;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class ResourceGetter {
	
	public static InputStream resource(final String name) {
		return ResourceGetter.class.getResourceAsStream(name);
	}
	
	public static InputStreamReader reader(final String name) {
		return new InputStreamReader(resource(name));
	}
	
	public static BufferedReader bufferedReader(final String name) {
		return new BufferedReader(reader(name));
	}
	
	public static BufferedImage image(final String name) {
		try {
			return ImageIO.read(resource(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
