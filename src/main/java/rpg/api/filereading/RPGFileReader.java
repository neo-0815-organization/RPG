package rpg.api.filereading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RPGFileReader {
	/**
	 * Reads a file, splitting the lines seperately in to key and value pairs,
	 * seperated by a seperator in the file.
	 * 
	 * @param readFile
	 *        the file to read
	 * @param seperator
	 *        the seperator seperating key and value
	 * @param onRead
	 *        the method to run on every line read. see {@link ILineRead}
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
			
			reader.close();
		} catch(final IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Reads a file, splitting the lines seperately in to key and value pairs,
	 * seperated by a seperator in the file.
	 * 
	 * @param path
	 *        the path to the file to read
	 * @param seperator
	 *        the seperator seperating key and value
	 * @return a {@link HashMap} consisting of the key and value pairs read from the
	 *         file
	 */
	public static HashMap<String, String> readLineSplit(final String path, final String seperator) {
		final HashMap<String, String> result = new HashMap<>();
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(RPGFileReader.class.getResource(path).getFile())));
			
			String line;
			String[] split;
			
			while((line = reader.readLine()) != null)
				if((split = line.split(seperator)).length == 2) result.put(split[0], split[1]);
			
			reader.close();
		} catch(final IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
