package rpg.api;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public interface IImageable {
	
	public String getImageName();
	
	public void setImageName(String name);
	
	public String getDirectoryName();
	
	public default BufferedImage getImage() {
		try {
			// TODO Use 'SpriteLoader' and 'Sprite'
			return ImageIO.read(getClass().getResource("/assets/textures/" + getDirectoryName() + "/" + getImageName() + ".png"));
		}catch(final IOException e) {}
		
		return null;
	}
}
