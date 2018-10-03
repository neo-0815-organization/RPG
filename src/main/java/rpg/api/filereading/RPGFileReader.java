package rpg.api.filereading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RPGFileReader {
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
	
	public static String[][] readLineSplit(final File readFile, final String seperator) {
		String[][] result = null;
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(readFile));
			result = new String[(int) reader.lines().count()][2];
			
			String line = null;
			String[] lineSplit = null;
			int lineNumber = 0;
			
			while((line = reader.readLine()) != null) {
				if((lineSplit = line.split(seperator)).length == 2) result[lineNumber] = lineSplit;
				
				lineNumber++;
			}
			
			reader.close();
		} catch(final IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
}
