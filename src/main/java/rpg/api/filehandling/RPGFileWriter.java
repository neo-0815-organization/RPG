package rpg.api.filehandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * A text file writing utility.
 * 
 * @author Neo Hornberger
 */
public class RPGFileWriter {
	
	public static void writeMap(final File file, final Map<String, String> map, final String separator) {
		try {
			final FileWriter writer = new FileWriter(file);
			
			map.forEach((key, value) -> {
				try {
					writer.write(key + separator + value);
				}catch(final IOException e) {
					e.printStackTrace();
				}
			});
			
			writer.close();
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
}
