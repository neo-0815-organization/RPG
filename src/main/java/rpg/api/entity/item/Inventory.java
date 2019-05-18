package rpg.api.entity.item;

import java.util.LinkedList;

import rpg.api.gfx.IDrawable;

public class Inventory {
	private final int maxSize;
	
	protected final LinkedList<ItemStack> items = new LinkedList<>();
	
	public Inventory(final int maxSize) {
		this.maxSize = maxSize;
	}
	
	/**
	 * Adds an {@link ItemStack} to the inventory
	 * 
	 * @param stack
	 *            the {@link ItemStack} to add
	 * @return {@code true}, if the {@link ItemStack} has been added to the
	 *         inventory
	 */
	public boolean addItemStack(final ItemStack stack) {
		if(items.size() < maxSize) {
			items.add(stack);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Removes an {@link ItemStack} from the inventory
	 * 
	 * @param stack
	 *            the {@link ItemStack} to remove
	 */
	public void removeItemStack(final ItemStack stack) {
		items.remove(stack);
	}
	
	public IDrawable getRenderer() {
		return null;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public LinkedList<ItemStack> getItems() {
		return items;
	}
}
