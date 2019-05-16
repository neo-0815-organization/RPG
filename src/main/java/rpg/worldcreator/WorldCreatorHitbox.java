package rpg.worldcreator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Dimension2D;

public class WorldCreatorHitbox {
	private double scale = 1d;
	
	private Dimension dim = new Dimension();
	private boolean type;
	
	public WorldCreatorHitbox() {
		this(false);
	}
	
	public WorldCreatorHitbox(final boolean type) {
		this.type = type;
	}
	
	public void draw(final Graphics g) {
		g.setColor(new Color(64, 64, 128, 48));
		g.fillRect(0, 0, (int) (dim.width * Data.tileSize * scale), (int) (dim.height * Data.tileSize * scale));
		
		g.setColor(new Color(64, 64, 128, 128));
		g.drawRect(0, 0, (int) (dim.width * Data.tileSize * scale), (int) (dim.height * Data.tileSize * scale));
	}
	
	public boolean getType() {
		return type;
	}
	
	public void setType(final boolean type) {
		this.type = type;
		
		dim.setSize(0, 0);
	}
	
	public Dimension getDimension() {
		return dim;
	}
	
	public void setScale(final double scale) {
		this.scale = scale;
	}
	
	public WorldCreatorHitbox copy() {
		final WorldCreatorHitbox hitbox = new WorldCreatorHitbox(type);
		
		hitbox.dim = new Dimension(dim);
		
		return hitbox;
	}
	
	public boolean isNull() {
		return type;
	}
	
	public static final WorldCreatorHitbox nullBox = new WorldCreatorHitbox(true);
	
	public class Dimension extends Dimension2D {
		private double width, height;
		
		public Dimension() {
			width = 0;
			height = 0;
		}
		
		public Dimension(final Dimension dim) {
			width = dim.width;
			height = dim.height;
		}
		
		@Override
		public double getWidth() {
			return width;
		}
		
		@Override
		public double getHeight() {
			return height;
		}
		
		@Override
		public void setSize(final double width, final double height) {
			this.width = width;
			this.height = height;
		}
	}
}
