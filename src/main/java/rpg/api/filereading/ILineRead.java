package rpg.api.filereading;

/**
 * The interface ILineRead representing the action of reading one line from a
 * {@link FIle}
 */
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
