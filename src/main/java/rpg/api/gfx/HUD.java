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
	private static final double scaleFactor = 1.8, scaleFactor2 = Statics.scale * scaleFactor;
	private static final BufferedImage OVERLAY = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/overlay.png"), scaleFactor2),
									   XP_FILL = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/xp.png"), Statics.scale * (scaleFactor + 0.2)),
									   HP_FILL = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/hp.png"), Statics.scale * (scaleFactor + 0.1)),
									   MP_FILL = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/mp.png"), Statics.scale * (scaleFactor + 0.1)),
									   XP_ICON = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/xp-icon.png"), scaleFactor2),
									   HP_ICON = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/hp-icon.png"), Statics.scale * (scaleFactor - 0.1)),
									   MP_ICON = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/mp-icon.png"), scaleFactor2);
	private static final int HP_FILL_X = Statics.scale((36 - 2) * scaleFactor),
							 MP_FILL_X = Statics.scale((38 - 2) * scaleFactor),
							 MP_FILL_Y = Statics.scale(36 * scaleFactor),
							 XP_ICON_X = Statics.scale((17 + 15 - 4) * scaleFactor),
							 XP_ICON_Y = Statics.scale(73 * scaleFactor),
							 HP_ICON_X = Statics.scale((111 + 15 - 4) * scaleFactor),
							 HP_ICON_Y = Statics.scale(16 * scaleFactor),
							 MP_ICON_X = Statics.scale((76 + 15 - 4) * scaleFactor),
							 MP_ICON_Y = Statics.scale(65 * scaleFactor);
	// @formatter:on
	
	@Override
	public void draw(final Graphics2D g2d) {
		if(RPG.gameField.getPlayerController() != null) {
			final Player p = RPG.gameField.getPlayerController().getPlayer();
			
			drawImage(g2d, XP_FILL, p.getXP());
			drawImage(g2d, HP_FILL, HP_FILL_X, p.getHP() / (float) p.getMaxHP());
			drawImage(g2d, MP_FILL, MP_FILL_X, MP_FILL_Y, p.getMP());
			
			g2d.drawImage(OVERLAY, 0, 0, null);
			
			g2d.drawImage(XP_ICON, XP_ICON_X, XP_ICON_Y, null);
			g2d.drawImage(HP_ICON, HP_ICON_X, HP_ICON_Y, null);
			g2d.drawImage(MP_ICON, MP_ICON_X, MP_ICON_Y, null);
			// TODO: scale Images
			g2d.setFont(Statics.defaultFont(27d * scaleFactor));
			drawCenteredString(g2d, "" + p.getXPLevel(), 17 * scaleFactor2, 80 * scaleFactor2, 20 * scaleFactor2);
			drawCenteredString(g2d, "" + p.getHP(), 111 * scaleFactor2, 17 * scaleFactor2, 20 * scaleFactor2);
			drawCenteredString(g2d, "" + p.getMPLevel(), 76 * scaleFactor2, 74 * scaleFactor2, 20 * scaleFactor2);
			
			p.getInventory().draw(g2d);
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
