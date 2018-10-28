package rpg.api.localization;

import java.io.File;
import java.util.ArrayList;

/**
 * The class LocalizationException to be thrown if an exception occures while
 * localizing a path.
 *
 * @author Neo Hornberger
 */
public class LocalizationException extends RuntimeException {
	private static final long	serialVersionUID	= 7639671735833895387L;
	private final String		baseMessage			= "Error in path '%path%'";
	
	private final int		line;
	private final String	path,
			file,
			specifier;
	private final Class<?>	specifierClass;
	
	/**
	 * Constructs a new {@link LocalizationException} with a 'path' and a 'file'.
	 *
	 * @param path
	 *     the path of the {@link File} in which the error has occured
	 * @param file
	 *     the name of the {@link File} in which the error has occured
	 * @see #LocalizationException(String, String, int)
	 */
	public LocalizationException(final String path, final String file) {
		this(path, file, -1);
	}
	
	/**
	 * Constructs a new {@link LocalizationException} with a 'path', a 'file' and a
	 * 'line'.
	 *
	 * @param path
	 *     the path of the {@link File} in which the error has occured
	 * @param file
	 *     the name of the {@link File} in which the error has occured
	 * @param line
	 *     the line on which the error has occured (if {@code line < 0}
	 *     linenumber won't be shown in the error message)
	 * @see #LocalizationException(String, String, String, int)
	 */
	public LocalizationException(final String path, final String file, final int line) {
		this(path, file, null, line);
	}
	
	/**
	 * Constructs a new {@link LocalizationException} with a 'path', a 'file' and a
	 * 'specifier'.
	 *
	 * @param path
	 *     the path of the {@link File} in which the error has occured
	 * @param file
	 *     the name of the {@link File} in which the error has occured
	 * @param specifier
	 *     the specifier that was matched incorrectly (if
	 *     {@code specifier == null || specifier.isEmpty()} the specifier won't
	 *     be shown in the error message)
	 * @see #LocalizationException(String, String, String, int)
	 */
	public LocalizationException(final String path, final String file, final String specifier) {
		this(path, file, specifier, -1);
	}
	
	/**
	 * Constructs a new {@link LocalizationException} with a 'path', a 'file', a
	 * 'specifier' and a 'line'.
	 *
	 * @param path
	 *     the path of the {@link File} in which the error has occured
	 * @param file
	 *     the name of the {@link File} in which the error has occured
	 * @param specifier
	 *     the specifier that was matched incorrectly (if
	 *     {@code specifier == null || specifier.isEmpty()} the specifier won't
	 *     be shown in the error message)
	 * @param line
	 *     the line on which the error has occured (if {@code line < 0}
	 *     linenumber won't be shown in the error message)
	 * @see #LocalizationException(String, String, String, Class<?>, int)
	 */
	public LocalizationException(final String path, final String file, final String specifier, final int line) {
		this(path, file, specifier, null, line);
	}
	
	/**
	 * Constructs a new {@link LocalizationException} with a 'path', a 'file', a
	 * 'specifier', a {@link Class} 'specifierClass' and a 'line'.
	 *
	 * @param path
	 *     the path of the {@link File} in which the error has occured
	 * @param file
	 *     the name of the {@link File} in which the error has occured
	 * @param specifier
	 *     the specifier that was matched incorrectly (if
	 *     {@code specifier == null || specifier.isEmpty()} the specifier won't
	 *     be shown in the error message)
	 * @param specifierClass
	 *     the {@link Class} of the specifier (if {@code specifierClass == null}
	 *     the class of the specifier won't be shown in the error message)
	 * @param line
	 *     the line on which the error has occured (if {@code line < 0}
	 *     linenumber won't be shown in the error message)
	 */
	public LocalizationException(final String path, final String file, final String specifier, final Class<?> specifierClass, final int line) {
		this.path = path;
		this.file = file;
		this.specifier = specifier;
		this.specifierClass = specifierClass;
		this.line = line;
	}
	
	@Override
	public String getMessage() {
		String base = baseMessage.replace("%path%", path);
		final ArrayList<String> list = new ArrayList<>();
		
		if(file != null && !file.isEmpty()) list.add(getFieldValue("file"));
		if(line >= 0) list.add(getFieldValue("line"));
		
		if(specifier != null && !specifier.isEmpty()) if(specifierClass != null) base += " with wrong-matched [" + specifierClass + "]" + " specifier '" + specifier + "'";
		else base += " with unmatched specifier '" + specifier + "'";
		
		if(!list.isEmpty()) base += " (" + list.toString().substring(1).substring(0, list.toString().length() - 2) + ")";
		
		return base;
	}
	
	/**
	 * A method to help build the error message.
	 *
	 * @param field
	 *     the field which is wanted
	 * @return the wanted component of the error message
	 */
	private String getFieldValue(final String field) {
		switch(field.toLowerCase()) {
			case "file":
				return "File: '" + file + "'";
			case "line":
				return "Line: " + line;
			default:
				return "";
		}
	}
}
