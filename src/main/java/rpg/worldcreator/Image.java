package rpg.worldcreator;

import static rpg.worldcreator.Data.tileSize;

import java.awt.image.BufferedImage;

public class Image {
	private final BufferedImage image;
	private final int id, xShift, yShift;
	
	private BufferedImage subImage;
	private Rotation rotation;
	private double scaleFactor;
	
	public Image(final int id, final int xShift, final int yShift, final Rotation rotation, final double scaleFactor) {
		this(id >= 0 ? RPGWorldCreator.getTextures().getSecond(RPGWorldCreator.getTextures().keyWithValueOne(id)) : null, id, xShift, yShift, rotation, scaleFactor);
	}
	
	public Image(final BufferedImage image, final int id, final int xShift, final int yShift, final Rotation rotation, final double scaleFactor) {
		this.image = image;
		this.id = id;
		this.xShift = xShift;
		this.yShift = yShift;
		this.rotation = rotation;
		this.scaleFactor = scaleFactor;
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
	
	public double getScaleFactor() {
		return scaleFactor;
	}
	
	public void setScaleFactor(final double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
	public BufferedImage getImage() {
		if(image == null) return null;
		if(subImage == null) subImage = image.getSubimage(xShift, yShift, tileSize, tileSize);
		
		return RPGWorldCreator.scaleImage(RPGWorldCreator.rotateImage(subImage, rotation.getAngle()), (int) (subImage.getWidth() * scaleFactor), (int) (subImage.getHeight() * scaleFactor));
	}
	
	public Image copy() {
		return new Image(image, id, xShift, yShift, rotation, scaleFactor);
	}
	
	public boolean isNull() {
		return equals(nullImage) || image == null;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if(!(obj instanceof Image)) return false;
		if(this == obj) return true;
		
		final Image image = (Image) obj;
		
		if(id < 0 && image.id < 0) return true;
		
		return id == image.id && xShift == image.xShift && yShift == image.yShift && rotation == image.rotation;
	}
	
	public static final Image nullImage = new Image(-1, 0, 0, Rotation.NONE, 1d);
}
