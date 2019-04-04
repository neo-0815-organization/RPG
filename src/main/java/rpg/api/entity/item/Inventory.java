package rpg.api.entity.item;

import static rpg.Statics.frameSize;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import rpg.Statics;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.IDrawable;
import rpg.api.gfx.ImageUtility;

public class Inventory implements IDrawable {
	private static final BufferedImage HOTBAR = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/inventory/hotbar.png"), Statics.scale * 1.7),
			INVENTORY = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/inventory/inventory.png"), Statics.scale * 1.7);
	
	private static final int MAX_INVENTORY_SIZE = 16, INVENTORY_WIDTH = 4;
	
	public boolean						showFull	= false;
	private final LinkedList<ItemStack>	questItems	= new LinkedList<>(), items = new LinkedList<>();
	private ItemStack					armorSlot, weaponSlot;
	
	/**
	 * Adds an {@link ItemStack} to the inventory
	 * 
	 * @param stack
	 *                  the {@link ItemStack} to add
	 * @return {@code true}, if the {@link ItemStack} has been added to the
	 *         inventory
	 */
	public boolean addItemStack(final ItemStack stack) {
		if(stack.isQuestItem()) questItems.add(stack);
		else if(items.size() >= MAX_INVENTORY_SIZE) return false;
		else items.add(stack);
		
		return true;
	}
	
	/**
	 * Removes an {@link ItemStack} from the inventory
	 * 
	 * @param stack
	 *                  the {@link ItemStack} to remove
	 */
	public void removeItemStack(final ItemStack stack) {
		if(stack.isQuestItem()) questItems.remove(stack);
		else items.remove(stack);
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		drawAlwaysVisible(g2d);
		
		if(showFull) drawToggleVisible(g2d);
	}
	
	private void drawAlwaysVisible(final Graphics2D g2d) {
		g2d.drawImage(HOTBAR, frameSize.width - HOTBAR.getWidth(), frameSize.height - HOTBAR.getHeight(), null);
		
//		for(int i = 0; i < 4 && i < items.size(); i++) TODO: Draw Items
//			items.get(i).draw(g2d, location);
	}
	
	private void drawToggleVisible(final Graphics2D g2d) {
		g2d.drawImage(INVENTORY, frameSize.width - INVENTORY.getWidth(), frameSize.height - HOTBAR.getHeight() - INVENTORY.getHeight(), null);
	}
}
