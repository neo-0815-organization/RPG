package rpg.api.filereading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RPGFileReader {
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs, seperated by a seperator.
	 *
	 * @param readFile
	 *            the {@link File} to read
	 * @param seperator
	 *            the seperator seperating key and value
	 * @param onRead
	 *            the method to run on every line read. see {@link ILineRead}
	 */
	public static void readLineSplit(final File readFile, final String seperator, final ILineRead onRead) {
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(readFile));
			
			String line = null;
			String[] lineSplit = null;
			int lineNumber = 1;
			
			while((line = reader.readLine()) != null) {
				if((lineSplit = line.split(seperator)).length == 2) onRead.onLineRead(lineSplit[0], lineSplit[1], lineNumber);
				
				lineNumber++;
			}
			
			//			reader.lines().filter(line -> !line.equals("")).map(currentLine -> currentLine.split(seperator)).forEach(entry -> onRead.onLineRead(entry[0], entry[1], 0 /* TODO line number? */));
			
			reader.close();
		}catch(final IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs, seperated by a seperator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param seperator
	 *            the seperator seperating key and value
	 * @return a {@link Map} consisting of the key and value pairs read from the
	 *         {@link File}
	 */
	public static Map<String, String> readLineSplit(final String path, final String seperator) {
		Map<String, String> result = null;
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(RPGFileReader.class.getResource(path).getFile())));
			
			//@formatter:off
			result = reader.lines()
						   .parallel()
						   .filter(line -> line.contains(seperator))
						   .map(line -> line.split(seperator))
						   .filter(entry -> checkEntry(entry, 2))
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
	 * pairs, seperated by a seperator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param seperator
	 *            the seperator seperating key and value
	 * @param splitCount
	 *            the number of elements per row
	 * @return a {@link Map} consisting of the key and value set pairs read from
	 *         the {@link File}
	 */
	public static Map<String, String[]> readLineMultiSplit(final String path, final String seperator, final int splitCount) {
		Map<String, String[]> result = null;
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(RPGFileReader.class.getResource(path).getFile())));
			
			//@formatter:off
			result = reader.lines()
						   .parallel()
						   .filter(line -> line.contains(seperator))
						   .map(line -> line.split(seperator))
						   .filter(entry -> checkEntry(entry, splitCount))
						   .collect(Collectors.<String[], String, String[]>toMap(entry -> entry[0],
																				 entry -> {
																					 final String[] list = new String[splitCount - 1];
																					 
																					 for(int i = 0; i < list.length;)
																						 list[i] = entry[++i];
																						  
																					 return list;
																				  }));
			//@formatter:on
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private static boolean checkEntry(final String[] entry, final int splitCount) {
		return entry.length == splitCount && Stream.of(entry).parallel().allMatch(s -> !s.isEmpty());
	}
	
	/**
	 * Reads a {@link File}, returns the {@link String} value corresponding to
	 * the 'key', seperated by a seperator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param seperator
	 *            the seperator seperating key and value
	 * @param key
	 *            the key to read from
	 * @return a {@link String} value read from the {@link File}
	 */
	public static String readLineSplit(final String path, final String seperator, final String key) {
		String result = "";
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(RPGFileReader.class.getResource(path).getFile())));
			
			//@formatter:off
			result = reader.lines()
						   .parallel()
						   .filter(line -> line.contains(seperator))
						   .map(line -> line.split(seperator))
						   .filter(entry -> checkEntry(entry, 2) && entry[0].equals(key))
						   .findFirst().get()[1];
			//@formatter:on
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Reads a {@link File}, returns the {@link String} value corresponding to
	 * the 'key', seperated by a seperator in the {@link File}.
	 *
	 * @param path
	 *            the path to the {@link File} to read
	 * @param seperator
	 *            the seperator seperating key and value
	 * @param key
	 *            the key to read from
	 * @param splitCount
	 *            the number of elements per row
	 * @return a {@link Map} consisting of the key and value set pairs read from
	 *         the {@link File}
	 */
	public static String[] readLineMultiSplit(final String path, final String seperator, final String key, final int splitCount) {
		final String[] result = new String[splitCount - 1];
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(RPGFileReader.class.getResource(path).getFile())));
			
			//@formatter:off
			final String[] elements = reader.lines()
									  		.parallel()
									  		.filter(line -> line.contains(seperator))
									  		.map(line -> line.split(seperator))
									  		.filter(entry -> checkEntry(entry, splitCount) && entry[0].equals(key))
									  		.findFirst().get();
			//@formatter:on
			
			for(int i = 0; i < result.length;)
				result[i] = elements[++i];
			
			reader.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
