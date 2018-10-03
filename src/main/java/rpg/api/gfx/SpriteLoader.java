package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteLoader {
	private static final HashMap<String, Sprite> loadedSprites = new HashMap<>();
	
	public static Sprite loadFromBufferedImage(final String spriteName, final int frameWidth, final int frameHeight, final BufferedImage... img) {
		final Sprite loadSprite = new Sprite(spriteName);
		
		loadedSprites.put(spriteName, loadSprite);
		
		return loadSprite;
	}
}
