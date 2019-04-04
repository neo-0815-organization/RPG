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
	private static final int			MAX_INVENTORY_SIZE	= 16, HOTBAR_SLOTS = 4;
	private static final BufferedImage	HOTBAR				= ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/inventory/hotbar.png"), Statics.scale * 1.7),
			INVENTORY = ImageUtility.scale(ResourceGetter.getImage("/assets/textures/overlay/inventory/inventory.png"), Statics.scale * 1.7),
			SLOT = HOTBAR.getSubimage(0, 0, HOTBAR.getWidth() / HOTBAR_SLOTS, HOTBAR.getWidth() / HOTBAR_SLOTS);
	
	private static final int W_INV = HOTBAR.getWidth(), H_INV = INVENTORY.getHeight(), H_HOT = HOTBAR.getHeight(),
			X_INV = frameSize.width - W_INV, Y_HOT = frameSize.height - H_HOT, Y_INV = frameSize.height - H_INV - H_HOT,
			SLOT_SIZE = W_INV / HOTBAR_SLOTS;
	
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
		else if(items.size() < MAX_INVENTORY_SIZE) items.add(stack);
		else return false;
		
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
		g2d.drawImage(HOTBAR, X_INV, Y_HOT, null);
		
		for(int i = 0; i < HOTBAR_SLOTS && i < items.size(); i++) {
			if(items.get(i) == null) continue;
			final BufferedImage image = items.get(i).getSprite().getCurrentAnimationFrame();
			
			g2d.drawImage(image, X_INV + W_INV * i / HOTBAR_SLOTS, Y_HOT, null);
		}
		
		g2d.drawImage(SLOT, 0, Y_HOT, null);
		if(armorSlot != null) g2d.drawImage(armorSlot.getSprite().getCurrentAnimationFrame(), 0, Y_HOT, null);
		
		g2d.drawImage(SLOT, SLOT_SIZE, Y_HOT, null);
		if(weaponSlot != null) g2d.drawImage(weaponSlot.getSprite().getCurrentAnimationFrame(), 0, Y_HOT, null);
	}
	
	private void drawToggleVisible(final Graphics2D g2d) {
		g2d.drawImage(INVENTORY, X_INV, Y_INV, null);
		
		for(int i = HOTBAR_SLOTS; i < items.size(); i++) {
			if(items.get(i) == null) continue;
			final BufferedImage image = items.get(i).getSprite().getCurrentAnimationFrame();
			final int x = i % HOTBAR_SLOTS;
			final int y = (i - x) / HOTBAR_SLOTS;
			
			g2d.drawImage(image, X_INV + SLOT_SIZE * x, Y_HOT - SLOT_SIZE * y, null);
		}
		
		for(int i = 0; i < questItems.size(); i++) {
			if(questItems.get(i) == null) continue;
			
			final BufferedImage image = questItems.get(i).getSprite().getCurrentAnimationFrame();
			final int x = i % (HOTBAR_SLOTS + 1);
			final int y = (i - x) / HOTBAR_SLOTS;
			
			g2d.drawImage(SLOT, Statics.frameSize.width - SLOT_SIZE * x, SLOT_SIZE * y, null);
			g2d.drawImage(image, Statics.frameSize.width - SLOT_SIZE * x, SLOT_SIZE * y, null);
		}
	}
}
