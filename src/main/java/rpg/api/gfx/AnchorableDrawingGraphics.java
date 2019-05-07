package rpg.api.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.util.Stack;

public class AnchorableDrawingGraphics extends DrawingGraphics {
	private final Stack<DrawingAnchor> aStack = new Stack<>();
	
	private DrawingAnchor defaultAnchor = DrawingAnchor.TOP_LEFT;
	
	public AnchorableDrawingGraphics(final Graphics context) {
		this((Graphics2D) context);
	}
	
	public AnchorableDrawingGraphics(final Graphics2D context) {
		this(context, DrawingAnchor.DEFAULT);
	}
	
	public AnchorableDrawingGraphics(final Graphics context, final DrawingAnchor defaultAnchor) {
		this((Graphics2D) context, defaultAnchor);
	}
	
	public AnchorableDrawingGraphics(final Graphics2D context, final DrawingAnchor defaultAnchor) {
		super(context);
		
		a(defaultAnchor);
		setDefaultAnchor(defaultAnchor);
	}
	
	private DrawingAnchor a() {
		return aStack.peek();
	}
	
	private void a(final DrawingAnchor anchor) {
		aStack.push(anchor == DrawingAnchor.DEFAULT ? defaultAnchor : anchor);
	}
	
	private Point p(final int x, final int y, final double width, final double height) {
		return p(x, y, (float) width, (float) height);
	}
	
	private Point p(final int x, final int y, final float width, final float height) {
		return a().apply(x, y, width, height);
	}
	
	private Point p(final float x, final float y, final double width, final double height) {
		return a().apply(x, y, (float) width, (float) height);
	}
	
	@Override
	public void push() {
		push(a());
	}
	
	public void push(final DrawingAnchor anchor) {
		super.push();
		
		a(anchor);
	}
	
	@Override
	public void pop() {
		super.pop();
		
		if(!aStack.isEmpty()) aStack.pop();
	}
	
	public DrawingAnchor getAnchor() {
		return a();
	}
	
	public void setAnchor(final DrawingAnchor anchor) {
		aStack.pop();
		a(anchor);
	}
	
	public void resetAnchor() {
		setAnchor(defaultAnchor);
	}
	
	public DrawingAnchor getDefaultAnchor() {
		return defaultAnchor;
	}
	
	public void setDefaultAnchor(final DrawingAnchor defaultAnchor) {
		this.defaultAnchor = defaultAnchor == DrawingAnchor.DEFAULT ? this.defaultAnchor : defaultAnchor;
	}
	
	public enum DrawingAnchor {
		TOP_LEFT,
		CENTER_LEFT,
		BOTTOM_LEFT,
		TOP_CENTER,
		CENTER,
		BOTTOM_CENTER,
		TOP_RIGHT,
		CENTER_RIGHT,
		BOTTOM_RIGHT,
		
		DEFAULT;
		
		public Point apply(final int x, final int y, final float width, final float height) {
			return apply((float) x, (float) y, width, height);
		}
		
		public Point apply(final float x, final float y, final float width, final float height) {
			switch(this) {
				case TOP_LEFT:
					return new Point(x, y);
				case CENTER_LEFT:
					return new Point(x, y - height / 2);
				case BOTTOM_LEFT:
					return new Point(x, y - height);
				case TOP_CENTER:
					return new Point(x - width / 2, y);
				case CENTER:
					return new Point(x - width / 2, y - height / 2);
				case BOTTOM_CENTER:
					return new Point(x - width / 2, y - height);
				case TOP_RIGHT:
					return new Point(x - width, y);
				case CENTER_RIGHT:
					return new Point(x - width, y - height / 2);
				case BOTTOM_RIGHT:
					return new Point(x - width, y - height);
				
				default:
					return null;
			}
		}
	}
	
	private static class Point {
		public float x, y;
		
		public Point(final float x, final float y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return (int) x;
		}
		
		public int getY() {
			return (int) y;
		}
	}
	
	@Override
	public AnchorableDrawingGraphics create() {
		return new AnchorableDrawingGraphics(super.create());
	}
	
	@Override
	public void clipRect(final int x, final int y, final int width, final int height) {
		final Point p = p(x, y, width, height);
		
		super.clipRect(p.getX(), p.getY(), width, height);
	}
	
	@Override
	public void setClip(final int x, final int y, final int width, final int height) {
		final Point p = p(x, y, width, height);
		
		super.setClip(p.getX(), p.getY(), width, height);
	}
	
	@Override
	public void setClip(final Shape clip) {
		final Point p = p(clip.getBounds().x, clip.getBounds().y, clip.getBounds().getWidth(), clip.getBounds().getHeight());
		
		clip.getBounds().x = p.getX();
		clip.getBounds().y = p.getY();
		
		super.setClip(clip);
	}
	
	@Override
	public void copyArea(final int x, final int y, final int width, final int height, final int dx, final int dy) {
		final Point p = p(x, y, width, height), dp = p(dx, dy, width, height);
		
		super.copyArea(p.getX(), p.getY(), width, height, dp.getX(), dp.getY());
	}
	
	@Override
	public void drawRect(final int x, final int y, final int width, final int height) {
		final Point p = p(x, y, width, height);
		
		super.drawRect(p.getX(), p.getY(), width, height);
	}
	
	@Override
	public void drawLine(final int x1, final int y1, final int x2, final int y2) {
		final int width = x2 - x1, height = y2 - y1;
		final Point p = p(x1, y1, width, height);
		
		super.drawLine(p.getX(), p.getY(), p.getX() + width, p.getY() + height);
	}
	
	@Override
	public void fillRect(final int x, final int y, final int width, final int height) {
		final Point p = p(x, y, width, height);
		
		super.fillRect(p.getX(), p.getY(), width, height);
	}
	
	@Override
	public void clearRect(final int x, final int y, final int width, final int height) {
		final Point p = p(x, y, width, height);
		
		super.clearRect(p.getX(), p.getY(), width, height);
	}
	
	@Override
	public void drawRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
		final Point p = p(x, y, width, height);
		
		super.drawRoundRect(p.getX(), p.getY(), width, height, arcWidth, arcHeight);
	}
	
	@Override
	public void fillRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
		final Point p = p(x, y, width, height);
		
		super.fillRoundRect(p.getX(), p.getY(), width, height, arcWidth, arcHeight);
	}
	
	@Override
	public void drawOval(final int x, final int y, final int width, final int height) {
		final Point p = p(x, y, width, height);
		
		super.drawOval(p.getX(), p.getY(), width, height);
	}
	
	@Override
	public void fillOval(final int x, final int y, final int width, final int height) {
		final Point p = p(x, y, width, height);
		
		super.fillOval(p.getX(), p.getY(), width, height);
	}
	
	@Override
	public void drawArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
		final Point p = p(x, y, width, height);
		
		super.drawArc(p.getX(), p.getY(), width, height, startAngle, arcAngle);
	}
	
	@Override
	public void fillArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
		final Point p = p(x, y, width, height);
		
		super.fillArc(p.getX(), p.getY(), width, height, startAngle, arcAngle);
	}
	
	@Override
	public void drawPolyline(final int[] xPoints, final int[] yPoints, final int nPoints) {
		super.drawPolyline(xPoints, yPoints, nPoints);
	}
	
	@Override
	public void drawPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
		super.drawPolygon(xPoints, yPoints, nPoints);
	}
	
	@Override
	public void fillPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
		super.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	@Override
	public void drawString(final String str, final int x, final int y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		final Point p = p(x, (int) (y + textSize.getHeight()), textSize.getWidth(), textSize.getHeight());
		
		super.drawString(str, p.getX(), p.getY());
	}
	
	@Override
	public void drawCenteredString(final String str, final int x, final int y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		final Point p = p((int) (x - textSize.getWidth() / 2), (int) (y + textSize.getHeight()), textSize.getWidth(), textSize.getHeight());
		
		super.drawString(str, p.getX(), p.getY());
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final ImageObserver observer) {
		final Point p = p(x, y, img.getWidth(observer), img.getHeight(observer));
		
		return super.drawImage(img, p.getX(), p.getY(), observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final int width, final int height, final ImageObserver observer) {
		final Point p = p(x, y, width, height);
		
		return super.drawImage(img, p.getX(), p.getY(), width, height, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final Color bgcolor, final ImageObserver observer) {
		final Point p = p(x, y, img.getWidth(observer), img.getHeight(observer));
		
		return super.drawImage(img, p.getX(), p.getY(), bgcolor, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final int width, final int height, final Color bgcolor, final ImageObserver observer) {
		final Point p = p(x, y, width, height);
		
		return super.drawImage(img, p.getX(), p.getY(), width, height, bgcolor, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1, final int sy1, final int sx2, final int sy2, final ImageObserver observer) {
		final int width = dx2 - dx1, height = dy2 - dy1;
		final Point p = p(dx1, dy2, width, height);
		
		return super.drawImage(img, p.getX(), p.getY(), p.getX() + width, p.getY() + height, sx1, sy1, sx2, sy2, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1, final int sy1, final int sx2, final int sy2, final Color bgcolor, final ImageObserver observer) {
		final int width = dx2 - dx1, height = dy2 - dy1;
		final Point p = p(dx1, dy2, width, height);
		
		return super.drawImage(img, p.getX(), p.getY(), p.getX() + width, p.getY() + height, sx1, sy1, sx2, sy2, bgcolor, observer);
	}
	
	@Override
	public void draw(final Shape s) {
		final Point p = p(s.getBounds().x, s.getBounds().y, s.getBounds().getWidth(), s.getBounds().getHeight());
		
		s.getBounds().x = p.getX();
		s.getBounds().y = p.getY();
		
		super.draw(s);
	}
	
	@Override
	public void drawImage(final BufferedImage img, final BufferedImageOp op, final int x, final int y) {
		final Point p = p(x, y, img.getWidth(), img.getHeight());
		
		super.drawImage(img, op, p.getX(), p.getY());
	}
	
	@Override
	public void drawString(final String str, final float x, final float y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		final Point p = p(x, (float) (y + textSize.getHeight()), textSize.getWidth(), textSize.getHeight());
		
		super.drawString(str, p.x, p.y);
	}
	
	@Override
	public void drawCenteredString(final String str, final float x, final float y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		final Point p = p((float) (x - textSize.getWidth() / 2), (float) (y + textSize.getHeight()), textSize.getWidth(), textSize.getHeight());
		
		super.drawString(str, p.x, p.y);
	}
	
	@Override
	public void fill(final Shape s) {
		final Point p = p(s.getBounds().x, s.getBounds().y, s.getBounds().getWidth(), s.getBounds().getHeight());
		
		s.getBounds().x = p.getX();
		s.getBounds().y = p.getY();
		
		super.fill(s);
	}
	
	@Override
	public boolean hit(final Rectangle rect, final Shape s, final boolean onStroke) {
		final Point pRect = p(rect.x, rect.y, rect.width, rect.height), pS = p(s.getBounds().x, s.getBounds().y, s.getBounds().width, s.getBounds().height);
		
		rect.x = pRect.getX();
		rect.y = pRect.getY();
		
		s.getBounds().x = pS.getX();
		s.getBounds().y = pS.getY();
		
		return super.hit(rect, s, onStroke);
	}
	
	@Override
	public void clip(final Shape s) {
		final Point p = p(s.getBounds().x, s.getBounds().y, s.getBounds().getWidth(), s.getBounds().getHeight());
		
		s.getBounds().x = p.getX();
		s.getBounds().y = p.getY();
		
		super.clip(s);
	}
}
