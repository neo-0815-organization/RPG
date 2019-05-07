package rpg.api.gfx.menus.combatMenu;

import java.awt.Color;
import java.awt.Font;

import rpg.Statics;
import rpg.api.gfx.DrawingGraphics;

public class TextAnimation extends CombatAnimation {
	
	private final MovingPattern pattern;
	private final int x;
	private int y;
	private final String text;
	private final double maxTime;
	private double currentTime;
	
	private final Color color;
	private final Font font;
	
	public TextAnimation(final String text, final int centerX, final int centerY, final double time, final MovingPattern pattern, final Color color, final Font font) {
		this.text = text;
		x = centerX;
		y = centerY;
		this.pattern = pattern;
		maxTime = time;
		
		this.color = color;
		this.font = font;
	}
	
	public TextAnimation(final String text, final int centerX, final int centerY, final double time, final MovingPattern pattern, final Color color) {
		this(text, centerX, centerY, time, pattern, color, Statics.defaultFont);
	}
	
	public TextAnimation(final String text, final int centerX, final int centerY, final double time, final MovingPattern pattern, final Color color, final int fontSize) {
		this(text, centerX, centerY, time, pattern, color, Statics.defaultFont((double) fontSize));
	}
	
	public TextAnimation(final String text, final int centerX, final int centerY, final double time, final MovingPattern pattern, final int fontSize) {
		this(text, centerX, centerY, time, pattern, Color.BLACK, Statics.defaultFont((double) fontSize));
	}
	
	@Override
	public boolean update(final double timeSinceLastFrame) {
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
	public void draw(final DrawingGraphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawCenteredString(text, x, y);
	}
	
	public static enum MovingPattern {
		NOTHING,
		LIFTING;
	}
}
