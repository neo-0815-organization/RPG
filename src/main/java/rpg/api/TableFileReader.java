package rpg.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TableFileReader {
	public static HashMap<String, String> read(final String path, final String seperator) {
		final HashMap<String, String> result = new HashMap<>();
		
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(TableFileReader.class.getResource(path).getFile())));
			
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
