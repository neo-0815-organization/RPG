package rpg.api.gfx;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.Player;
import rpg.api.filehandling.ResourceGetter;

public class HUD implements IDrawable {
	// @formatter:off
	private static final BufferedImage overlay = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/overlay.png")),
									   xp = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/xp.png"), Statics.scale * 1.1),
									   hp = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/hp.png"), Statics.scale * 1.1),
									   mp = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/mp.png"), Statics.scale * 1.1);
	// @formatter:on
	
	@Override
	public void draw(final Graphics2D g2d) {
		if(RPG.gameField.getPlayerController() != null) {
			final Player p = RPG.gameField.getPlayerController().getPlayer();
			
			drawImage(g2d, xp, p.getXP());
			drawImage(g2d, hp, Statics.scale(36 - 4), p.getHP() / (float) p.getMaxHP());
			drawImage(g2d, mp, Statics.scale(38 - 4), Statics.scale(36), p.getMP());
			
			//			g2d.drawImage(xp, 0, 0, xp.getWidth(), Math.round(p.getXP() * xp.getHeight()), null);
			//			g2d.drawImage(hp, Statics.scale(36), 0, hp.getWidth(), Math.round(p.getHP() / p.getMaxHP() * (float) hp.getHeight()), null);
			//			g2d.drawImage(mp, Statics.scale(38), Statics.scale(36), mp.getWidth(), Math.round(p.getMP() * mp.getHeight()), null);
			
			g2d.drawImage(overlay, 0, 0, null);
			
			g2d.setFont(Statics.defaultFont.deriveFont(26f));
			drawCenteredString(g2d, "" + p.getXPLevel(), Statics.scale * 19, Statics.scale * 80, 30);
			drawCenteredString(g2d, "" + p.getHP(), Statics.scale * 111, Statics.scale * 18, 30);
			drawCenteredString(g2d, "" + p.getMPLevel(), Statics.scale * 76, Statics.scale * 74, 30);
		}
	}
	
	private void drawImage(final Graphics2D g2d, final BufferedImage img, final float factor) {
		drawImage(g2d, img, 0, 0, factor);
	}
	
	private void drawImage(final Graphics2D g2d, final BufferedImage img, final int x, final float factor) {
		drawImage(g2d, img, x, 0, factor);
	}
	
	private void drawImage(final Graphics2D g2d, final BufferedImage img, final int x, final int y, final float factor) {
		final int calcY = img.getHeight() - Math.round(factor * img.getHeight());
		
		g2d.drawImage(img, x, calcY + y, x + img.getWidth(), y + img.getHeight(), 0, calcY, img.getWidth(), img.getHeight(), null);
	}
	
	private void drawCenteredString(final Graphics2D g2d, final String text, final double x, final double y, final double width) {
		final Font oldFont = g2d.getFont();
		
		if(g2d.getFontMetrics().getStringBounds(text, g2d).getWidth() > width) for(float i = 0; i < 50; i += 0.5) {
			g2d.setFont(oldFont.deriveFont(oldFont.getSize() - i));
			
			if(g2d.getFontMetrics().getStringBounds(text, g2d).getWidth() <= width) break;
		}
		
		drawCenteredString(g2d, text, x, y);
		
		g2d.setFont(oldFont);
	}
	
	private void drawCenteredString(final Graphics2D g2d, final String text, final double x, final double y) {
		final Rectangle2D textSize = g2d.getFontMetrics().getStringBounds(text, g2d);
		final float calcX = (float) (x - textSize.getWidth() / 2), calcY = (float) (y + textSize.getHeight() / 2);
		
		g2d.drawString(text, calcX, calcY);
	}
}
