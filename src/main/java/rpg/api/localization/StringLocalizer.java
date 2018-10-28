package rpg.api.localization;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.IllegalFormatConversionException;
import java.util.LinkedHashMap;
import java.util.MissingFormatArgumentException;

import rpg.api.filereading.ILineRead;
import rpg.api.filereading.RPGFileReader;

/**
 * The class StringLocalizer used to localize {@link String}s.
 *
 * @author Neo Hornberger
 */
public class StringLocalizer {
	private static final File										langDir			= getLangDir();
	private static final LinkedHashMap<String, LocalizationTable>	tables			= new LinkedHashMap<>();
	private static Locale											activeLocale	= Locale.getDefault();
	
	private static final FilenameFilter filenameFilter = new FilenameFilter() {
		
		@Override
		public boolean accept(final File dir, final String name) {
			return name.endsWith(".lang");
		}
	};
	
	/**
	 * Localizes the path 'path'.
	 *
	 * @param path
	 *     the path which will be localized
	 * @return the localized value of the path 'path'
	 */
	public static String localize(final String path) {
		return format0(path, true);
	}
	
	/**
	 * Localizes the path 'path' and formats its value.
	 *
	 * @param path
	 *     the path which will be localized and formated
	 * @param objects
	 *     the arguments referenced by the format specifiers in the format
	 *     string. If there are more arguments than format specifiers, the
	 *     extra arguments are ignored. The number of arguments is variable and
	 *     may be zero.
	 *     The behaviour on a {@code null} argument depends on the
	 *     <a href="../util/Formatter.html#syntax">conversion</a>
	 * @return the localized and formated value of the path 'path'
	 */
	public static String format(final String path, final Object... objects) {
		return format0(path, false, objects);
	}
	
	/**
	 * <h4>Does the whole magic!!</h4>
	 * Localizes the path 'path' and formats its value.
	 * This method is only for internal usage.
	 *
	 * @param path
	 *     the path which will be localized and formated
	 * @param ignoreExceptions
	 *     if {@code true} the method won't throw any {@link LocalizationException}
	 * @param objects
	 *     the arguments referenced by the format specifiers in the format
	 *     string. If there are more arguments than format specifiers, the
	 *     extra arguments are ignored. The number of arguments is variable and
	 *     may be zero.
	 *     The behaviour on a {@code null} argument depends on the
	 *     <a href="../util/Formatter.html#syntax">conversion</a>
	 * @return the localized and formated value of the path 'path'
	 */
	private static String format0(final String path, final boolean ignoreExceptions, final Object... objects) {
		final LocalizationTable table = tables.get(activeLocale.getName());
		
		if(table.pathHasValue(path)) {
			final String value = table.getValue(path);
			
			try {
				return String.format(value, objects);
			} catch(final MissingFormatArgumentException | IllegalFormatConversionException e) {
				if(ignoreExceptions) return value;
				else if(e instanceof MissingFormatArgumentException) throw new LocalizationException(path, activeLocale.getFilename(), ((MissingFormatArgumentException) e).getFormatSpecifier(), table.getLineNumber(path));
				else if(e instanceof IllegalFormatConversionException) throw new LocalizationException(path, activeLocale.getFilename(), "%" + ((IllegalFormatConversionException) e).getConversion(), ((IllegalFormatConversionException) e).getArgumentClass(), table.getLineNumber(path));
			} catch(final ArrayIndexOutOfBoundsException e) {
				return "";
			}
		}
		
		return path;
	}
	
	/**
	 * Updates the {@link LocalizationTable}s.
	 */
	public static void updateTables() {
		final File[] files = langDir.listFiles(filenameFilter);
		
		for(final File file : files) {
			final LocalizationTable table = new LocalizationTable();
			
			RPGFileReader.readLineSplit(file, "=", new ILineRead() {
				
				@Override
				public void onLineRead(final String key, final String value, final int lineNumber) {
					table.setValueAndLineNumber(key, value, lineNumber);
				}
			});
			
			tables.put(file.getName().replace(".lang", ""), table);
		}
	}
	
	/**
	 * Gets the directory where the language files are located.
	 *
	 * @return the directory where the language files are located
	 */
	private static File getLangDir() {
		final URL url = StringLocalizer.class.getResource("/assets/lang/");
		
		File file = null;
		try {
			file = new File(url.toURI());
		} catch(final URISyntaxException e) {
			file = new File(url.getPath());
		}
		
		return file;
	}
	
	/**
	 * Gets the currently active {@link Locale}.
	 *
	 * @return the currently active {@link Locale}
	 */
	public static Locale getActiveLocale() {
		return activeLocale;
	}
	
	/**
	 * Sets the currently active {@link Locale}.
	 */
	public static void setActiveLocale(final Locale locale) {
		activeLocale = locale;
	}
	
	/**
	 * Resets the currently active {@link Locale} to the default {@link Locale}.
	 *
	 * @see Locale#getDefault()
	 */
	public static void resetActiveLocale() {
		activeLocale = Locale.getDefault();
	}
}
