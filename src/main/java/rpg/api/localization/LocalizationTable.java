package rpg.api.localization;

import java.util.LinkedHashMap;

/**
 * The class LocalizationTable stores paths and their corresponding value and
 * linenumber.
 *
 * @author Neo Hornberger
 */
public class LocalizationTable {
	private final LinkedHashMap<String, String> values = new LinkedHashMap<>();
	private final LinkedHashMap<String, Integer> lineNumbers = new LinkedHashMap<>();
	
	/**
	 * Gets the localized text corresponding to the path 'path'.
	 *
	 * @param path
	 *            the path to check
	 * @return the localized text (includig formatting symbols like %s etc.)
	 *         corresponding to the path 'path'
	 */
	public String getValue(final String path) {
		if(!pathHasValue(path)) return null;
		
		return values.get(path);
	}
	
	/**
	 * Gets the linenumber where the path 'path' is specified.
	 *
	 * @param path
	 *            the path to check
	 * @return the linenumber where the path 'path' is specified (if not
	 *         specified returns {@code -1})
	 */
	public int getLineNumber(final String path) {
		if(!pathHasValue(path)) return -1;
		
		return lineNumbers.get(path);
	}
	
	/**
	 * Checks if the path 'path' corresponds to a value.
	 *
	 * @param path
	 *            the path to check
	 * @return {@code true} if the path 'path' corresponds to a value
	 */
	public boolean pathHasValue(final String path) {
		return values.containsKey(path);
	}
	
	/**
	 * Moves the path 'path' to line 'lineNumber' and sets its value to 'value'.
	 *
	 * @param path
	 *            the path to modify
	 * @param value
	 *            the new value for the path 'path'
	 * @param lineNumber
	 *            the line to move the path 'path' to
	 */
	protected void setValueAndLineNumber(final String path, final String value, final int lineNumber) {
		values.put(path, value);
		lineNumbers.put(path, lineNumber);
	}
	
	/**
	 * Clears the whole {@link LocalizationTable}.
	 */
	protected void clear() {
		values.clear();
		lineNumbers.clear();
	}
}
