package rpg.api.entity.item;

import static rpg.Statics.frameSize;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import rpg.RPG;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.IDrawable;
import rpg.api.listener.key.KeyboardListener;

public class Inventory implements IDrawable {
	private static final int HOTBAR_SLOTS = 4, INVENTORY_ROWS = 4, MAX_INVENTORY_SIZE = HOTBAR_SLOTS * INVENTORY_ROWS;
	// @formatter:off
	private static final BufferedImage HOTBAR_SLOT = ResourceGetter.getImage("/assets/textures/overlay/inventory/hotbar_slot.png"),
									   HOTBAR_SELECTOR = ResourceGetter.getImage("/assets/textures/overlay/inventory/hotbar_selector.png"),
									   QUEST_SLOT = ResourceGetter.getImage("/assets/textures/overlay/inventory/quest_slot.png"),
									   INVENTORY_SLOT = ResourceGetter.getImage("/assets/textures/overlay/inventory/inventory_slot.png"),
									   INVENTORY_SELECTOR = ResourceGetter.getImage("/assets/textures/overlay/inventory/inventory_selector.png");
	
	private static final int W_INV = HOTBAR_SLOT.getWidth() * HOTBAR_SLOTS,
							 H_INV = INVENTORY_SLOT.getHeight() * (MAX_INVENTORY_SIZE / HOTBAR_SLOTS - 1),
							 SLOT_SIZE = HOTBAR_SLOT.getHeight(),
							 X_INV = frameSize.width - W_INV,
							 Y_HOT = frameSize.height - SLOT_SIZE,
							 Y_INV = frameSize.height - H_INV - SLOT_SIZE;
	// @formatter:on
	
	public boolean						showInv		= false, showQuest = false;
	private final LinkedList<ItemStack>	questItems	= new LinkedList<>(), items = new LinkedList<>();
	
	static {
		KeyboardListener.registerKey(KeyEvent.VK_E, (state) -> {
			switch(state) {
				case PRESSED:
				case RELEASED:
				case RELEASING:
					return;
				case PRESSING:
					RPG.gameField.getPlayerController().getPlayer().getInventory().showInv = !RPG.gameField.getPlayerController().getPlayer().getInventory().showInv;
					break;
			}
		});
		KeyboardListener.registerKey(KeyEvent.VK_R, (state) -> {
			switch(state) {
				case PRESSED:
				case RELEASED:
				case RELEASING:
					return;
				case PRESSING:
					RPG.gameField.getPlayerController().getPlayer().getInventory().showQuest = !RPG.gameField.getPlayerController().getPlayer().getInventory().showQuest;
					break;
			}
		});
	}
	
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
	public void draw(final DrawingGraphics g) {
		drawHotbar(g);
		System.out.println("aaaaaaa");
		if(showInv) drawInventory(g);
		if(showQuest) drawQuests(g);
	}
	
	private void drawQuests(final DrawingGraphics g) {
		int x, y;
		BufferedImage image;
		
		for(int i = 0; i < questItems.size(); i++) {
			x = i % HOTBAR_SLOTS;
			y = (i - x) / HOTBAR_SLOTS;
			
			if(questItems.get(i) == null) continue;
			
			image = questItems.get(i).getSprite().getCurrentAnimationFrame();
			
			g.drawImage(QUEST_SLOT, frameSize.width - SLOT_SIZE * (x + 1), SLOT_SIZE * y, null);
			g.drawImage(image, frameSize.width - SLOT_SIZE * (x + 1), SLOT_SIZE * y, null);
		}
	}
	
	private void drawHotbar(final DrawingGraphics g) {
		System.out.println("bbbbb");
		
		for(int i = 0; i < HOTBAR_SLOTS; i++) {
			g.drawImage(HOTBAR_SLOT, X_INV + SLOT_SIZE * i, Y_HOT, null);
			
			if(i == 0) g.drawImage(HOTBAR_SELECTOR, X_INV + SLOT_SIZE * i, Y_HOT, null);
			
			if(i >= items.size() || items.get(i) == null) continue;
			
			final BufferedImage image = items.get(i).getSprite().getCurrentAnimationFrame();
			
			g.drawImage(image, X_INV + SLOT_SIZE * i, Y_HOT, null);
		}
	}
	
	private void drawInventory(final DrawingGraphics g) {
		int x, y;
		BufferedImage image;
		
		for(int i = HOTBAR_SLOTS; i < MAX_INVENTORY_SIZE; i++) {
			x = i % HOTBAR_SLOTS;
			y = (i - x) / HOTBAR_SLOTS;
			
			g.drawImage(INVENTORY_SLOT, X_INV + SLOT_SIZE * x, Y_HOT - SLOT_SIZE * y, null);
			
			if(i == 0) g.drawImage(INVENTORY_SELECTOR, X_INV + SLOT_SIZE * i, Y_HOT - SLOT_SIZE * y, null);
			
			if(i >= items.size() || items.get(i) == null) continue;
			
			image = items.get(i).getSprite().getCurrentAnimationFrame();
			
			g.drawImage(image, X_INV + SLOT_SIZE * x, Y_HOT - SLOT_SIZE * y, null);
		}
	}
}
