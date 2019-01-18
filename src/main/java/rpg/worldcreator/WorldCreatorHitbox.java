package rpg.worldcreator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import rpg.api.vector.Vec2D;

public class WorldCreatorHitbox {
	private double scale = 1d;
	
	private ArrayList<Vec2D<?>> points = new ArrayList<>();
	private String type;
	
	public WorldCreatorHitbox(final String type) {
		this.type = type;
	}
	
	public void draw(final Graphics g) {
		final int[] x = new int[points.size()], y = new int[points.size()];
		
		g.setColor(new Color(64, 64, 64, 128));
		
		Vec2D<?> point;
		for(int i = 0; i < points.size(); i++) {
			point = points.get(i);
			
			x[i] = (int) (point.getX().getValuePixel() * scale);
			y[i] = (int) (point.getY().getValuePixel() * scale);
		}
		
		g.drawPolygon(x, y, points.size());
		g.setColor(new Color(64, 64, 64, 48));
		g.fillPolygon(x, y, points.size());
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
		
		if(isNull()) points.clear();
	}
	
	public ArrayList<Vec2D<?>> getPoints() {
		return points;
	}
	
	public void setScale(final double scale) {
		this.scale = scale;
	}
	
	public WorldCreatorHitbox copy() {
		final WorldCreatorHitbox hitbox = new WorldCreatorHitbox(type);
		
		hitbox.points = new ArrayList<>(points);
		
		return hitbox;
	}
	
	public boolean isNull() {
		return type == null;
	}
	
	public static final WorldCreatorHitbox nullBox = new WorldCreatorHitbox(null);
}
