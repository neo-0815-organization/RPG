package rpg.api.filereading;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceGetter {
	
	public static InputStream resource(final String name) {
		return ResourceGetter.class.getResourceAsStream(name);
	}
	
	public static InputStreamReader reader(final String name) {
		return new InputStreamReader(resource(name));
	}
	
	public static BufferedReader bufferedReader(final String name) {
		return new BufferedReader(reader(name));
	}
}
