package rpg.api.filehandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * A text file reading utility.
 * 
 * @author Tim Ludwig
 */
public class RPGFileReader {
	
	public static long lineCount(final String path) {
		return ResourceGetter.getBufferedReader(path).lines().count();
	}
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs, separated by a separator.
	 *
	 * @param readFile
	 *            the {@link File} to read
	 * @param separator
	 *            the separator separating key and value
	 * @param onRead
	 *            the method to run on every line read. see {@link ILineRead}
	 */
	public static void readLineSplit(final String path, final String separator, final ILineRead onRead) {
		try {
			final BufferedReader reader = ResourceGetter.getBufferedReader(path);
			
			//			String line = null;
			//			String[] lineSplit = null;
			//			int lineNumber = 1;
			//
			//			while((line = reader.readLine()) != null) {
			//				if((lineSplit = line.split(seperator)).length == 2) onRead.onLineRead(lineSplit[0], lineSplit[1], lineNumber);
			//
			//				lineNumber++;
			//			}
			
			final AtomicReference<Integer> lineNumber = new AtomicReference<>(0);
			reader.lines().map(line -> {
				lineNumber.set(lineNumber.get() + 1);
				
				return new SimpleEntry<>(lineNumber.get(), line);
			}).parallel().map(line -> new SimpleEntry<>(line.getKey(), line.getValue().split(separator))).filter(line -> line.getValue().length == 2).forEach(line -> onRead.onLineRead(line.getValue()[0], line.getValue()[1], line.getKey()));
			
			reader.close();
		}catch(final IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs, separated by a separator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param separator
	 *            the separator separating key and value
	 * @return a {@link Map} consisting of the key and value pairs read from the
	 *         {@link File}
	 */
	public static Map<String, String> readLineSplit(final String path, final String separator) {
		Map<String, String> result = null;
		
		try {
			final BufferedReader reader = ResourceGetter.getBufferedReader(path);
			
			//@formatter:off
			result = reader
					.lines()
					.parallel()
					.map(line -> line.split(separator))
					.filter(entry -> entry.length == 2)
					.collect(Collectors.<String[], String, String>toMap(entry -> entry[0],
																		entry -> entry[1]));
			//@formatter:on
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs, separated by a separator in the {@link File}.
	 *
	 * @param file
	 *            the {@link File} to read
	 * @param separator
	 *            the separator separating key and value
	 * @return a {@link Map} consisting of the key and value pairs read from the
	 *         {@link File}
	 */
	public static Map<String, String> readLineSplit(final File file, final String separator) {
		Map<String, String> result = null;
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(file));
			
			//@formatter:off
			result = reader
					.lines()
					.parallel()
					.map(line -> line.split(separator))
					.filter(entry -> entry.length == 2)
					.collect(Collectors.<String[], String, String>toMap(entry -> entry[0],
																		entry -> entry[1]));
			//@formatter:on
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs, separated by a separator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param separator
	 *            the separator separating key and value
	 * @param splitCount
	 *            the number of elements per row
	 * @return a {@link Map} consisting of the key and value set pairs read from
	 *         the {@link File}
	 */
	public static Map<String, String[]> readLineMultiSplit(final String path, final String separator, final int splitCount) {
		Map<String, String[]> result = null;
		
		try {
			final BufferedReader reader = ResourceGetter.getBufferedReader(path);
			
			//@formatter:off
			result = reader
					.lines()
					.parallel()
					.map(line -> line.split(separator))
					.filter(entry -> entry.length == splitCount)
					.collect(Collectors.<String[], String, String[]>toMap(entry -> entry[0],
																		   entry -> {
																			   final String[] list = new String[splitCount - 1];
																					 
																			   for(int i = 0; i < list.length; i++)
																				   list[i] = entry[i + 1];
																						  
																			   return list;
																		   }));
			//@formatter:on
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Reads a {@link File}, returns the {@link String} value corresponding to
	 * the 'key', separated by a separator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param separator
	 *            the separator separating key and value
	 * @param key
	 *            the key to read from
	 * @return a {@link String} value read from the {@link File}
	 */
	public static String readLineSplit(final String path, final String separator, final String key) {
		String result = "";
		
		try {
			final BufferedReader reader = ResourceGetter.getBufferedReader(path);
			
			//@formatter:off
			result = reader
					.lines()
					.parallel()
					.map(line -> line.split(separator))
					.filter(entry -> entry.length == 2 && entry[0].equals(key))
					.findFirst()
					.get()[1];
			//@formatter:on
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Reads a {@link File}, returns the {@link String} value corresponding to
	 * the 'key', separated by a separator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param separator
	 *            the separator separating key and value
	 * @param key
	 *            the key to read from
	 * @param splitCount
	 *            the number of elements per row
	 * @return a {@link Map} consisting of the key and value set pairs read from
	 *         the {@link File}
	 */
	public static String[] readLineMultiSplit(final String path, final String separator, final String key, final int splitCount) {
		final String[] result = new String[splitCount - 1];
		
		try {
			final BufferedReader reader = ResourceGetter.getBufferedReader(path);
			
			//@formatter:off
			final String[] elements = reader.lines()
											.parallel()
											.map(line -> line.split(separator))
											.filter(entry -> entry.length == splitCount && entry[0].equals(key))
											.findFirst()
											.get();
			//@formatter:on
			
			for(int i = 0; i < result.length; i++)
				result[i] = elements[i + 1];
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
