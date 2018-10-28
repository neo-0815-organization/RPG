package rpg.api.filereading;

public interface ILineRead {
	/**
	 * Method to run on lineread in the RPGFileReader
	 * 
	 * @param key
	 *        the key read
	 * @param value
	 *        the value read
	 * @param lineNumber
	 *        the linenumber key and value were read at
	 */
	public void onLineRead(String key, String value, int lineNumber);
}
