package rpg.api.gfx.menus;

import java.awt.Graphics2D;

import rpg.api.dialog.Dialog;
import rpg.api.entity.Entity;
import rpg.api.gfx.framework.Menu;

public class DialogMenu extends Menu {
	private final Dialog dialog;
	private final Entity entity;
	
	public DialogMenu(final Dialog dialog, final Entity entity) {
		this.dialog = dialog;
		this.entity = entity;
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		g2d.drawImage(entity.getSprite().getCurrentAnimationFrame(), 0, 0, null);
		
		super.draw(g2d);
	}
}
