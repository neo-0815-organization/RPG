package rpg.api.filereading;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

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
	 *                 the path to the resource
	 * @return the {@link InputStream} of the requested resource, or<br>
	 *         {@code null} if no resource is found at this path
	 */
	public static InputStream getResource(final String path) {
		return ResourceGetter.class.getResourceAsStream(path);
	}
	
	/**
	 * Returns the {@link InputStreamReader} of the requested resource.
	 * 
	 * @param path
	 *                 the path to the resource
	 * @return the {@link InputStreamReader} of the requested resource, or<br>
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
	 *                 the path to the resource
	 * @return the {@link BufferedReader} of the requested resource, or<br>
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
	 *                 the path to the resource
	 * @return the {@link BufferedImage} of the requested resource, or<br>
	 *         {@code null}
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
	
	/**
	 * Returns the Midi {@link Sequence} of the requested resource.
	 * 
	 * @param path
	 *                 the path to the resource
	 * @return the {@link Sequence} of the requested resource, or {@code null}
	 * @see MidiSystem#getSequence(InputStream)
	 */
	public static Sequence getSequence(final InputStream inputStream) {
		try {
			return MidiSystem.getSequence(inputStream);
		} catch(InvalidMidiDataException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Returns the Midi {@link Sequence} of the requested resource.
	 * 
	 * @param path
	 *                 the path to the resource
	 * @return the {@link Sequence} of the requested resource, or {@code null}
	 * @see MidiSystem#getSequence(InputStream)
	 */
	public static Sequence getSequence(final String path) {
		return getSequence(getResource(path));
	}
}
