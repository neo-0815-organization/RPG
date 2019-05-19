package rpg.worldcreator;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

public class Utility {
	
	public static File addExtension(File file, final FileNameExtensionFilter filter) {
		final String extension = "." + filter.getExtensions()[0];
		
		if(!file.getName().endsWith(extension)) file = new File(file.getAbsolutePath() + extension);
		
		return file;
	}
	
	public static boolean deleteDirectory(final File dir) {
		boolean result = false;
		
		for(final File file : dir.listFiles()) {
			if(file.isDirectory()) deleteDirectory(file);
			
			result &= file.delete();
		}
		
		return dir.delete() && result;
	}
}
