package rpg.api.gfx.menus.combatMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import rpg.Statics;

public class TextAnimation extends CombatAnimation {
	
	private MovingPattern pattern;
	private int x, y;
	private String text;
	private double maxTime, currentTime;
	
	private Color color;
	private Font font;
	
	public TextAnimation(String text, int centerX, int centerY, double time, MovingPattern pattern, Color color, Font font) {
		this.text = text;
		this.x = centerX;
		this.y = centerY;
		this.pattern = pattern;
		this.maxTime = time;
		
		this.color = color;
		this.font = font;
	}

	public TextAnimation(String text, int centerX, int centerY, double time, MovingPattern pattern, Color color) {
		this(text, centerX, centerY, time, pattern, color, Statics.defaultFont);
	}
	
	public TextAnimation(String text, int centerX, int centerY, double time, MovingPattern pattern, Color color, int fontSize) {
		this(text, centerX, centerY, time, pattern, color, new Font("Arial", 0, fontSize));
	}
	
	public TextAnimation(String text, int centerX, int centerY, double time, MovingPattern pattern, int fontSize) {
		this(text, centerX, centerY, time, pattern, Color.BLACK, new Font("Arial", 0, fontSize));
	}


	
	@Override
	public boolean update(double timeSinceLastFrame) {
		switch(pattern) {
		case NOTHING:
			break;
		case LIFTING:
			y -= 100 * timeSinceLastFrame;
		}
		
		currentTime += timeSinceLastFrame;
		return currentTime > maxTime;	
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.setFont(font);
		g2d.drawString(text, x - g2d.getFontMetrics().stringWidth(text) / 2, y - g2d.getFontMetrics().getHeight() / 2);
	}
	
	public static enum MovingPattern {
		NOTHING,
		LIFTING;
	}
}
