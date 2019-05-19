package rpg.api.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.Player;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.AnchorableDrawingGraphics.DrawingAnchor;

public class HUD implements IDrawable {
	// @formatter:off
	private static final BufferedImage OVERLAY = ResourceGetter.getImage("/assets/textures/overlay/hud/overlay.png"),
									   XP_FILL = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/hud/xp.png"), 1.0),
									   HP_FILL = ResourceGetter.getImage("/assets/textures/overlay/hud/hp.png"),
									   MP_FILL = ResourceGetter.getImage("/assets/textures/overlay/hud/mp.png"),
									   XP_ICON = ResourceGetter.getImage("/assets/textures/overlay/hud/xp-icon.png"),
									   HP_ICON = ResourceGetter.getImage("/assets/textures/overlay/hud/hp-icon.png"),
									   MP_ICON = ResourceGetter.getImage("/assets/textures/overlay/hud/mp-icon.png");
	
	private static final int HP_FILL_X = 56,
							 MP_FILL_X = 59,
							 MP_FILL_Y = 56,
							 XP_ICON_X = 54,
							 XP_ICON_Y = 125,
							 HP_ICON_X = 200,
							 HP_ICON_Y = 35,
							 MP_ICON_X = 147,
							 MP_ICON_Y = 114;
	// @formatter:on
	
	@Override
	public void draw(final DrawingGraphics g) {
		if(RPG.gameField.getPlayerController() != null) {
			final Player p = RPG.gameField.getPlayerController().getPlayer();
			
			((AnchorableDrawingGraphics) g).push(DrawingAnchor.BOTTOM_LEFT);
			drawImage(g, XP_FILL, p.getXP() % 1);
			drawImage(g, HP_FILL, HP_FILL_X, p.getHP() / (float) p.getMaxHP());
			drawImage(g, MP_FILL, MP_FILL_X, MP_FILL_Y, p.getMP() % 1);
			g.pop();
			
			g.drawImage(OVERLAY, 0, 0, null);
			
			((AnchorableDrawingGraphics) g).push(DrawingAnchor.CENTER);
			g.drawImage(XP_ICON, XP_ICON_X, XP_ICON_Y, null);
			g.drawImage(HP_ICON, HP_ICON_X, HP_ICON_Y, null);
			g.drawImage(MP_ICON, MP_ICON_X, MP_ICON_Y, null);
			g.pop();
			
			// TODO scale Images
			g.setFont(Statics.defaultFont(20d));
			drawCenteredString(g, "" + p.getXPLevel(), 26, 114, 35);
			drawCenteredString(g, "" + p.getHP(), 170, 16, 35);
			drawCenteredString(g, "" + p.getMPLevel(), 118, 103, 35);
			
			p.getInventory().getRenderer().draw(g);
		}
	}
	
	private void drawImage(final DrawingGraphics g, final BufferedImage img, final float factor) {
		drawImage(g, img, 0, 0, factor);
	}
	
	private void drawImage(final DrawingGraphics g, final BufferedImage img, final int x, final float factor) {
		drawImage(g, img, x, 0, factor);
	}
	
	private void drawImage(final DrawingGraphics g, final BufferedImage img, final int x, final int y, final float factor) {
		final int calcY = img.getHeight() - Math.round(factor * img.getHeight());
		
		g.drawImage(img, x, calcY + y, x + img.getWidth(), y + img.getHeight(), 0, calcY, img.getWidth(), img.getHeight(), null);
	}
	
	private void drawCenteredString(final DrawingGraphics g, final String text, final float x, final float y, final double width) {
		final Font oldFont = g.getFont();
		
		if(g.getFontMetrics().getStringBounds(text, g).getWidth() > width) for(float i = 0; i < 50; i += 0.5) {
			g.setFont(oldFont.deriveFont(oldFont.getSize() - i));
			
			if(g.getFontMetrics().getStringBounds(text, g).getWidth() <= width) break;
		}
		
		g.drawCenteredString(text, x, y);
		
		g.setFont(oldFont);
	}
}
