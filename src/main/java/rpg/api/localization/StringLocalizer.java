package rpg.api.localization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.IllegalFormatConversionException;
import java.util.MissingFormatArgumentException;

public class StringLocalizer {
	private static final File	langDir			= getLangDir();
	private static Locale		activeLocale	= Locale.getDefault();
	
	public static String localize(String path) {
		return format0(path, true);
	}
	
	public static String format(String path, Object... objects) {
		return format0(path, false, objects);
	}
	
	private static String format0(String path, boolean ignoreExceptions, Object... objects) {
		final File[] files = langDir.listFiles(getLangFileFilter());
		
		for(final File file : files)
			try {
				final BufferedReader reader = new BufferedReader(new FileReader(file));
				
				String line = null;
				int lineNumber = 1;
				while( (line = reader.readLine()) != null) {
					if(line.split("=")[0].equals(path)) try {
						return String.format(line.split("=")[1], objects);
					} catch(final MissingFormatArgumentException | IllegalFormatConversionException e) {
						if(ignoreExceptions) return line.split("=")[1];
						else if(e instanceof MissingFormatArgumentException) throw new LocalizationException(path, file.getName(), ((MissingFormatArgumentException) e).getFormatSpecifier(), lineNumber);
						else if(e instanceof IllegalFormatConversionException) throw new LocalizationException(path, file.getName(), "%" + ((IllegalFormatConversionException) e).getConversion(), ((IllegalFormatConversionException) e).getArgumentClass(), lineNumber);
					} catch(final ArrayIndexOutOfBoundsException e) {
						return "";
					}
					
					lineNumber++;
				}
				
				reader.close();
			} catch(final IOException e) {
				e.printStackTrace();
			}
		
		return path;
	}
	
	private static File getLangDir() {
		final URL url = StringLocalizer.class.getClassLoader().getResource("lang");
		
		File file = null;
		try {
			file = new File(url.toURI());
		} catch(final URISyntaxException e) {
			file = new File(url.getPath());
		}
		
		return file;
	}
	
	private static FilenameFilter getLangFileFilter() {
		return new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(activeLocale.getFilename());
			}
		};
	}
	
	public static Locale getActiveLocale() {
		return activeLocale;
	}
	
	public static void setActiveLocale(Locale locale) {
		activeLocale = locale;
	}
	
	public static void resetActiveLocale() {
		activeLocale = Locale.getDefault();
	}
}
