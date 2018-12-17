package rpg.worldcreator;

import static rpg.worldcreator.Data.tileSize;

import java.awt.image.BufferedImage;

public class Image {
	private final BufferedImage image;
	private final int id, xShift, yShift;
	private Rotation rotation;
	
	public Image(final BufferedImage image, final int xShift, final int yShift, final Rotation rotation) {
		this.image = image;
		id = image != null ? RPGWorldCreator.getTextures().getFirst(RPGWorldCreator.getTextures().keyWithValueTwo(image)) : -1;
		this.xShift = xShift;
		this.yShift = yShift;
		this.rotation = rotation;
	}
	
	public Image(final int id, final int xShift, final int yShift, final Rotation rotation) {
		image = id < 0 ? RPGWorldCreator.getTextures().getSecond(RPGWorldCreator.getTextures().keyWithValueOne(id)) : null;
		this.id = id < -1 ? -1 : id;
		this.xShift = xShift;
		this.yShift = yShift;
		this.rotation = rotation;
	}
	
	public BufferedImage getOriginalImage() {
		return image;
	}
	
	public int getId() {
		return id;
	}
	
	public int getXShift() {
		return xShift;
	}
	
	public int getYShift() {
		return yShift;
	}
	
	public Rotation getRotation() {
		return rotation;
	}
	
	public void setRotation(final Rotation rotation) {
		this.rotation = rotation;
	}
	
	public BufferedImage getImage() {
		if(image == null) return null;
		
		return RPGWorldCreator.rotateImage(image.getSubimage(xShift, yShift, tileSize, tileSize), rotation.getAngle());
	}
}
