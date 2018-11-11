package rpg.api.filereading;

import java.io.File;

/**
 * The interface ILineRead representing the action of reading one line from a
 * {@link File}
 */
@FunctionalInterface
public interface ILineRead {
	
	/**
	 * Method to run on lineread in the {@link RPGFileReader}.
	 *
	 * @param key
	 *     the key read
	 * @param value
	 *     the value read
	 * @param lineNumber
	 *     the linenumber key and value were read at
	 */
	void onLineRead(String key, String value, int lineNumber);
}
