package rpg.api.entity.item;

import static rpg.Statics.gameSize;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import rpg.RPG;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.IDrawable;
import rpg.api.listener.key.KeyboardListener;

public class PlayerInventory extends Inventory {
	private static final int HOTBAR_SLOTS = 4, INVENTORY_ROWS = 4, MAX_INVENTORY_SIZE = HOTBAR_SLOTS * INVENTORY_ROWS;
	
	private final LinkedList<ItemStack> questItems = new LinkedList<>();
	private final InventoryRenderer renderer = new InventoryRenderer(this);
	
	public int selectedSlot = 0;
	
	public PlayerInventory() {
		super(MAX_INVENTORY_SIZE);
	}
	
	public PlayerInventory(final Inventory inv) {
		super(inv.getMaxSize());
		
		inv.items.forEach(items::add);
	}
	
	@Override
	public boolean addItemStack(final ItemStack stack) {
		if(stack.isQuestItem()) {
			questItems.add(stack);
			
			return true;
		}else return super.addItemStack(stack);
	}
	
	@Override
	public void removeItemStack(final ItemStack stack) {
		if(stack.isQuestItem()) questItems.remove(stack);
		else super.removeItemStack(stack);
	}
	
	private void throwItem(final int slot) {
		if(slot >= items.size()) return;
		
		RPG.gameField.addEntity(items.get(slot));
		items.get(slot).setLocation(RPG.gameField.getPlayerController().getPlayer().getLocation());
		items.remove(slot);
	}
	
	public void throwCurrentItem() {
		throwItem(selectedSlot);
	}
	
	@Override
	public InventoryRenderer getRenderer() {
		return renderer;
	}
	
	public static class InventoryRenderer implements IDrawable {
		private static final int TEXT_SHOW_TIME = 2 * 1000;
		// @formatter:off
		private static final BufferedImage HOTBAR_SLOT = ResourceGetter.getImage("/assets/textures/overlay/inventory/hotbar_slot.png"),
										   HOTBAR_SELECTOR = ResourceGetter.getImage("/assets/textures/overlay/inventory/hotbar_selector.png"),
										   QUEST_SLOT = ResourceGetter.getImage("/assets/textures/overlay/inventory/quest_slot.png"),
										   INVENTORY_SLOT = ResourceGetter.getImage("/assets/textures/overlay/inventory/inventory_slot.png"),
										   INVENTORY_SELECTOR = ResourceGetter.getImage("/assets/textures/overlay/inventory/inventory_selector.png");
		
		private static final int W_INV = HOTBAR_SLOT.getWidth() * HOTBAR_SLOTS,
								 H_INV = INVENTORY_SLOT.getHeight() * (MAX_INVENTORY_SIZE / HOTBAR_SLOTS - 1),
								 SLOT_SIZE = HOTBAR_SLOT.getHeight(),
								 X_INV = gameSize.width - W_INV,
								 Y_HOT = gameSize.height - SLOT_SIZE,
								 Y_INV = gameSize.height - H_INV - SLOT_SIZE;
		// @formatter:on
		
		public boolean showInv = false;
		public boolean showQuest = false;
		
		private String text = "";
		private long textTime = 0;
		private final PlayerInventory playerInv;
		
		static {
			KeyboardListener.registerKey(KeyEvent.VK_E, (state) -> {
				switch(state) {
					case PRESSED:
					case RELEASED:
					case RELEASING:
						return;
					case PRESSING:
						final PlayerInventory inv = RPG.gameField.getPlayerController().getPlayer().getInventory();
						inv.renderer.showInv = !inv.renderer.showInv;
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
						final PlayerInventory inv = RPG.gameField.getPlayerController().getPlayer().getInventory();
						inv.renderer.showQuest = !inv.renderer.showQuest;
						break;
				}
			});
			KeyboardListener.registerKey(KeyEvent.VK_LEFT, (state) -> {
				switch(state) {
					case RELEASED:
					case RELEASING:
					case PRESSED:
						return;
					case PRESSING:
						final PlayerInventory inv = RPG.gameField.getPlayerController().getPlayer().getInventory();
						if(inv.selectedSlot >= 1) inv.selectedSlot--;
						break;
				}
			});
			KeyboardListener.registerKey(KeyEvent.VK_RIGHT, (state) -> {
				switch(state) {
					case RELEASED:
					case RELEASING:
					case PRESSED:
						return;
					case PRESSING:
						final PlayerInventory inv = RPG.gameField.getPlayerController().getPlayer().getInventory();
						if(inv.selectedSlot < MAX_INVENTORY_SIZE - 1) inv.selectedSlot++;
						break;
				}
			});
			KeyboardListener.registerKey(KeyEvent.VK_UP, (state) -> {
				switch(state) {
					case RELEASED:
					case RELEASING:
					case PRESSED:
						return;
					case PRESSING:
						final PlayerInventory inv = RPG.gameField.getPlayerController().getPlayer().getInventory();
						if(inv.selectedSlot < MAX_INVENTORY_SIZE - HOTBAR_SLOTS) inv.selectedSlot += HOTBAR_SLOTS;
						break;
				}
			});
			KeyboardListener.registerKey(KeyEvent.VK_DOWN, (state) -> {
				switch(state) {
					case RELEASED:
					case RELEASING:
					case PRESSED:
						return;
					case PRESSING:
						final PlayerInventory inv = RPG.gameField.getPlayerController().getPlayer().getInventory();
						if(inv.selectedSlot >= HOTBAR_SLOTS) inv.selectedSlot -= HOTBAR_SLOTS;
						break;
				}
			});
			KeyboardListener.registerKey(KeyEvent.VK_Q, (state) -> {
				switch(state) {
					case RELEASED:
					case RELEASING:
					case PRESSED:
						return;
					case PRESSING:
						RPG.gameField.getPlayerController().getPlayer().getInventory().throwCurrentItem();;
						break;
				}
			});
		}
		
		public InventoryRenderer(final PlayerInventory playerInv) {
			this.playerInv = playerInv;
		}
		
		@Override
		public void draw(final DrawingGraphics g) {
			drawHotbar(g);
			
			if(showInv) drawInventory(g);
			if(showQuest) drawQuests(g);
			
			if(showInv || System.currentTimeMillis() - textTime < TEXT_SHOW_TIME) g.drawCenteredString(text, gameSize.width / 2, 0);
		}
		
		public void setText(final String text) {
			this.text = text;
			
			textTime = System.currentTimeMillis();
		}
		
		private void drawQuests(final DrawingGraphics g) {
			int x, y;
			BufferedImage image;
			
			for(int i = 0; i < playerInv.questItems.size(); i++) {
				x = i % HOTBAR_SLOTS;
				y = (i - x) / HOTBAR_SLOTS;
				
				if(playerInv.questItems.get(i) == null) continue;
				
				image = playerInv.questItems.get(i).getSprite().getCurrentAnimationFrame();
				
				g.drawImage(QUEST_SLOT, gameSize.width - SLOT_SIZE * (x + 1), SLOT_SIZE * y, null);
				g.drawImage(image, gameSize.width - SLOT_SIZE * (x + 1), SLOT_SIZE * y, null);
			}
		}
		
		private void drawHotbar(final DrawingGraphics g) {
			for(int i = 0; i < HOTBAR_SLOTS; i++) {
				g.drawImage(HOTBAR_SLOT, X_INV + SLOT_SIZE * i, Y_HOT, null);
				
				if(i == playerInv.selectedSlot) g.drawImage(HOTBAR_SELECTOR, X_INV + SLOT_SIZE * i, Y_HOT, null);
				
				if(i >= playerInv.items.size() || playerInv.items.get(i) == null) continue;
				
				final BufferedImage image = playerInv.items.get(i).getSprite().getCurrentAnimationFrame();
				
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
				
				if(i == playerInv.selectedSlot) g.drawImage(INVENTORY_SELECTOR, X_INV + SLOT_SIZE * x, Y_HOT - SLOT_SIZE * y, null);
				
				if(i >= playerInv.items.size() || playerInv.items.get(i) == null) continue;
				
				image = playerInv.items.get(i).getSprite().getCurrentAnimationFrame();
				
				g.drawImage(image, X_INV + SLOT_SIZE * x, Y_HOT - SLOT_SIZE * y, null);
			}
		}
	}
}
