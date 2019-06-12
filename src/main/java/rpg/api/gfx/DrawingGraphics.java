package rpg.api.gfx;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Stack;

public class DrawingGraphics extends Graphics2D {
	private final Stack<Graphics2D> gStack = new Stack<>();
	
	public DrawingGraphics(final Graphics context) {
		this((Graphics2D) context);
	}
	
	public DrawingGraphics(final Graphics2D context) {
		g(context);
	}
	
	protected Graphics2D g() {
		return gStack.peek();
	}
	
	private void g(final Graphics2D g2d) {
		gStack.push(g2d);
	}
	
	public void push() {
		g((Graphics2D) g().create());
	}
	
	public void pop() {
		if(gStack.isEmpty()) return;
		
		gStack.pop().dispose();
	}
	
	@Override
	public DrawingGraphics create() {
		return new DrawingGraphics((Graphics2D) g().create());
	}
	
	@Override
	public void translate(final int x, final int y) {
		g().translate(x, y);
	}
	
	@Override
	public Color getColor() {
		return g().getColor();
	}
	
	@Override
	public void setColor(final Color c) {
		g().setColor(c);
	}
	
	@Override
	public void setPaintMode() {
		g().setPaintMode();
	}
	
	@Override
	public void setXORMode(final Color c1) {
		g().setXORMode(c1);
	}
	
	@Override
	public Font getFont() {
		return g().getFont();
	}
	
	@Override
	public void setFont(final Font font) {
		g().setFont(font);
	}
	
	@Override
	public FontMetrics getFontMetrics(final Font f) {
		return g().getFontMetrics(f);
	}
	
	@Override
	public Rectangle getClipBounds() {
		return g().getClipBounds();
	}
	
	@Override
	public void clipRect(final int x, final int y, final int width, final int height) {
		g().clipRect(x, y, width, height);
	}
	
	@Override
	public void setClip(final int x, final int y, final int width, final int height) {
		g().setClip(x, y, width, height);
	}
	
	@Override
	public Shape getClip() {
		return g().getClip();
	}
	
	@Override
	public void setClip(final Shape clip) {
		g().setClip(clip);
	}
	
	@Override
	public void copyArea(final int x, final int y, final int width, final int height, final int dx, final int dy) {
		g().copyArea(x, y, width, height, dx, dy);
	}
	
	@Override
	public void drawLine(final int x1, final int y1, final int x2, final int y2) {
		g().drawLine(x1, y1, x2, y2);
	}
	
	@Override
	public void fillRect(final int x, final int y, final int width, final int height) {
		g().fillRect(x, y, width, height);
	}
	
	@Override
	public void clearRect(final int x, final int y, final int width, final int height) {
		g().clearRect(x, y, width, height);
	}
	
	@Override
	public void drawRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
		g().drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
	
	@Override
	public void fillRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
		g().fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
	
	@Override
	public void drawOval(final int x, final int y, final int width, final int height) {
		g().drawOval(x, y, width, height);
	}
	
	@Override
	public void fillOval(final int x, final int y, final int width, final int height) {
		g().fillOval(x, y, width, height);
	}
	
	@Override
	public void drawArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
		g().drawArc(x, y, width, height, startAngle, arcAngle);
	}
	
	@Override
	public void fillArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
		g().fillArc(x, y, width, height, startAngle, arcAngle);
	}
	
	@Override
	public void drawPolyline(final int[] xPoints, final int[] yPoints, final int nPoints) {
		g().drawPolyline(xPoints, yPoints, nPoints);
	}
	
	@Override
	public void drawPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
		g().drawPolygon(xPoints, yPoints, nPoints);
	}
	
	@Override
	public void fillPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
		g().fillPolygon(xPoints, yPoints, nPoints);
	}
	
	protected void drawString0(final String str, final int x, final int y) {
		g().drawString(str, x, y);
	}
	
	@Override
	public void drawString(final String str, final int x, final int y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		
		g().drawString(str, x, (int) (y + textSize.getHeight()));
	}
	
	public void drawCenteredString(final String str, final int x, final int y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		
		g().drawString(str, (int) (x - textSize.getWidth() / 2), (int) (y + textSize.getHeight()));
	}
	
	@Override
	public void drawString(final AttributedCharacterIterator iterator, final int x, final int y) {
		g().drawString(iterator, x, y);
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final ImageObserver observer) {
		return g().drawImage(img, x, y, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final int width, final int height, final ImageObserver observer) {
		return g().drawImage(img, x, y, width, height, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final Color bgcolor, final ImageObserver observer) {
		return g().drawImage(img, x, y, bgcolor, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int x, final int y, final int width, final int height, final Color bgcolor, final ImageObserver observer) {
		return g().drawImage(img, x, y, width, height, bgcolor, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1, final int sy1, final int sx2, final int sy2, final ImageObserver observer) {
		return g().drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}
	
	@Override
	public boolean drawImage(final Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1, final int sy1, final int sx2, final int sy2, final Color bgcolor, final ImageObserver observer) {
		return g().drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}
	
	@Override
	public void dispose() {
		g().dispose();
	}
	
	@Override
	public void draw(final Shape s) {
		g().draw(s);
		
	}
	
	@Override
	public boolean drawImage(final Image img, final AffineTransform xform, final ImageObserver obs) {
		return g().drawImage(img, xform, obs);
	}
	
	@Override
	public void drawImage(final BufferedImage img, final BufferedImageOp op, final int x, final int y) {
		g().drawImage(img, op, x, y);
	}
	
	@Override
	public void drawRenderedImage(final RenderedImage img, final AffineTransform xform) {
		g().drawRenderedImage(img, xform);
	}
	
	@Override
	public void drawRenderableImage(final RenderableImage img, final AffineTransform xform) {
		g().drawRenderableImage(img, xform);
	}
	
	protected void drawString0(final String str, final float x, final float y) {
		g().drawString(str, x, y);
	}
	
	@Override
	public void drawString(final String str, final float x, final float y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		
		g().drawString(str, x, (float) (y + textSize.getHeight()));
	}
	
	public void drawCenteredString(final String str, final float x, final float y) {
		final Rectangle2D textSize = g().getFontMetrics().getStringBounds(str, g());
		
		g().drawString(str, (float) (x - textSize.getWidth() / 2), (float) (y + textSize.getHeight()));
	}
	
	@Override
	public void drawString(final AttributedCharacterIterator iterator, final float x, final float y) {
		g().drawString(iterator, x, y);
	}
	
	@Override
	public void drawGlyphVector(final GlyphVector g, final float x, final float y) {
		g().drawGlyphVector(g, x, y);
	}
	
	@Override
	public void fill(final Shape s) {
		g().fill(s);
	}
	
	@Override
	public boolean hit(final Rectangle rect, final Shape s, final boolean onStroke) {
		return g().hit(rect, s, onStroke);
	}
	
	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		return g().getDeviceConfiguration();
	}
	
	@Override
	public void setComposite(final Composite comp) {
		g().setComposite(comp);
	}
	
	@Override
	public void setPaint(final Paint paint) {
		g().setPaint(paint);
	}
	
	@Override
	public void setStroke(final Stroke s) {
		g().setStroke(s);
	}
	
	@Override
	public void setRenderingHint(final Key hintKey, final Object hintValue) {
		g().setRenderingHint(hintKey, hintValue);
	}
	
	@Override
	public Object getRenderingHint(final Key hintKey) {
		return g().getRenderingHint(hintKey);
	}
	
	@Override
	public void setRenderingHints(final Map<?, ?> hints) {
		g().setRenderingHints(hints);
	}
	
	@Override
	public void addRenderingHints(final Map<?, ?> hints) {
		g().addRenderingHints(hints);
	}
	
	@Override
	public RenderingHints getRenderingHints() {
		return g().getRenderingHints();
	}
	
	@Override
	public void translate(final double tx, final double ty) {
		g().translate(tx, ty);
	}
	
	@Override
	public void rotate(final double theta) {
		g().rotate(theta);
	}
	
	@Override
	public void rotate(final double theta, final double x, final double y) {
		g().rotate(theta, x, y);
	}
	
	public void scale(final double s) {
		scale(s, s);
	}
	
	@Override
	public void scale(final double sx, final double sy) {
		g().scale(sx, sy);
	}
	
	@Override
	public void shear(final double shx, final double shy) {
		g().shear(shx, shy);
	}
	
	@Override
	public void transform(final AffineTransform Tx) {
		g().transform(Tx);
	}
	
	@Override
	public void setTransform(final AffineTransform Tx) {
		g().setTransform(Tx);
	}
	
	@Override
	public AffineTransform getTransform() {
		return g().getTransform();
	}
	
	@Override
	public Paint getPaint() {
		return g().getPaint();
	}
	
	@Override
	public Composite getComposite() {
		return g().getComposite();
	}
	
	@Override
	public void setBackground(final Color color) {
		g().setBackground(color);
	}
	
	@Override
	public Color getBackground() {
		return g().getBackground();
	}
	
	@Override
	public Stroke getStroke() {
		return g().getStroke();
	}
	
	@Override
	public void clip(final Shape s) {
		g().clip(s);
	}
	
	@Override
	public FontRenderContext getFontRenderContext() {
		return g().getFontRenderContext();
	}
}
