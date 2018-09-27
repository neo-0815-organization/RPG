package rpg.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public interface IImageable {
	
	public String getImageName();
	
	public void setImageName(String name);
	
	public String getDirectoryName();
	
	public default BufferedImage getImage() {
		try {
			return ImageIO.read(new File(""));
		}catch(final IOException e) {}
		
		return null;
	}
}
