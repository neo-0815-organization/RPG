package rpg.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public interface IImagable {
	
	public String getImageName();
	
	public void setImageName(String name);
	
	public String getDirectoryName();
	
	public default File getFile() {
		return new File("assets/textures/" + getDirectoryName() + "/" + getImageName() + ".png");
	}
	
	public default BufferedImage getImage() {
		try {
			return ImageIO.read(getFile());
		}catch(final IOException e) {}
		
		return null;
	}
}
