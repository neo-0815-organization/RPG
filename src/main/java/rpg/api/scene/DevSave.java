package rpg.api.scene;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashMap;

import rpg.Logger;

public final class DevSave extends Save {
	private static final HashMap<String, Object> DEFAULT_DEV_SETTINGS = new HashMap<>();
	
	static {
		DEFAULT_DEV_SETTINGS.put("background", "dev/world");
		DEFAULT_DEV_SETTINGS.put("entities", Collections.EMPTY_LIST);
	}
	
	public DevSave() {
		super("dev", DEFAULT_DEV_SETTINGS);
	}
	
	@Override
	public void save() {
		final File dir = new File(getClass().getResource("/").getFile(), filePath);
		
		try {
			Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
				
				@Override
				public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult postVisitDirectory(final Path dir, final IOException e) throws IOException {
					if(e == null) {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					}else // directory iteration failed
						throw e;
				}
			});
			
			dir.delete();
		}catch(final IOException e) {
			Logger.error(e);
		}
	}
}
