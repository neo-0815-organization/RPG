package rpg.worldcreator;

import java.awt.image.BufferedImage;

public class Image {
	private BufferedImage image;
	private int id, xShift, yShift;
	private Rotation rotation;
	
	public Image(BufferedImage image, int xShift, int yShift, Rotation rotation) {
		this.image = image;
		id = image != null ? RPGWorldCreator.getTextures().getFirst(RPGWorldCreator.getTextures().keyWithValueTwo(image)) : -1;
		this.xShift = xShift;
		this.yShift = yShift;
		this.rotation = rotation;
	}
	
	public Image(int id, int xShift, int yShift, Rotation rotation) {
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
	
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	
	public BufferedImage getImage() {
		if(image == null) return null;
		
		return RPGWorldCreator.rotateImage(image.getSubimage(xShift, yShift, 32, 32), rotation.getAngle());
	}
}
