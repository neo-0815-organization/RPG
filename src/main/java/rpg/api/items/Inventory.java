package rpg.api.items;

import java.util.ArrayList;

public class Inventory {
	private Item armorSlot;
	private ArrayList<Item> items = new ArrayList<>();
	
	private int slotCount = 20;
	
	
	public boolean addItem(Item item) {
		if (items.size() < slotCount - 1) {
			items.add(item);
			return true;
		}
		return false;
	}
}
