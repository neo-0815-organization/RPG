package rpg.api.filehandling;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * A utility class to manage the retrieval of resources.
 * 
 * @author Neo Hornberger
 */
public class ResourceGetter {
	
	/**
	 * Returns the {@link InputStream} of the requested resource.
	 * 
	 * @param path
	 *            the path to the resource
	 * @return the {@link InputStream} of the requested resource; {@code null}
	 *         if no resource is found at this path
	 */
	public static InputStream getResource(final String path) {
		return ResourceGetter.class.getResourceAsStream(path);
	}
	
	/**
	 * Returns the {@link InputStreamReader} of the requested resource.
	 * 
	 * @param path
	 *            the path to the resource
	 * @return the {@link InputStreamReader} of the requested resource;
	 *         {@code null} if no resource is found at this path
	 * @see #getResource(String)
	 */
	public static InputStreamReader getInputStreamReader(final String path) {
		return new InputStreamReader(getResource(path));
	}
	
	/**
	 * Returns the {@link BufferedReader} of the requested resource.
	 * 
	 * @param path
	 *            the path to the resource
	 * @return the {@link BufferedReader} of the requested resource;
	 *         {@code null} if no resource is found at this path
	 * @see #getInputStreamReader(String)
	 */
	public static BufferedReader getBufferedReader(final String path) {
		return new BufferedReader(getInputStreamReader(path));
	}
	
	/**
	 * Returns the {@link Image} of the requested resource.
	 * 
	 * @param path
	 *            the path to the resource
	 * @return the {@link BufferedImage} of the requested resource; {@code null}
	 *         <ul>
	 *         <li>if no resource is found at this path</li>
	 *         <li>if an error occurs during reading or when unable to create the
	 *         required {@link ImageInputStream}</li>
	 *         </ul>
	 * @see #getResource(String)
	 */
	public static BufferedImage getImage(final String path) {
		try {
			return ImageIO.read(getResource(path));
		} catch(final IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
