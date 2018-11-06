package rpg.api.filereading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RPGFileReader {
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs,
	 * seperated by a seperator.
	 *
	 * @param readFile
	 *     the {@link File} to read
	 * @param seperator
	 *     the seperator seperating key and value
	 * @param onRead
	 *     the method to run on every line read. see {@link ILineRead}
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
			
//			 reader.lines().map(currentLine -> currentLine.split(seperator)).forEach(entry -> onRead.onLineRead(entry[0], entry[1], 0 /* TODO line number? */));
			
			reader.close();
		} catch(final IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Reads a {@link File}, splitting the lines seperately in to key and value
	 * pairs,
	 * seperated by a seperator in the {@link File}.
	 *
	 * @param path
	 *     the path to the {@link File} to read
	 * @param seperator
	 *     the seperator seperating key and value
	 * @return a {@link HashMap} consisting of the key and value pairs read from the
	 * {@link File}
	 */
	public static String[][] readLineSplit(final String path, final String seperator) {
		String[][] result = new String[0][0];
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(RPGFileReader.class.getResource(path).getFile())));
			
			result = reader.lines().map(line -> line.split(seperator)).toArray(String[][]::new);
			
			System.out.println(result);
			
			reader.close();
		} catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
