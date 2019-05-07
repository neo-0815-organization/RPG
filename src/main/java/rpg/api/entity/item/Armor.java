package rpg.api.entity.item;

import rpg.api.entity.item.Weapon.IntRange;

public abstract class Armor extends ItemStack{

	public Armor(final String name) {
		this(name, false);
	}
	
	public Armor(final String name, final boolean questItem) {
		super(name, questItem);
		
		this.type = ItemType.ARMOR;
		this.stackSize = 1;
	}
	
	public abstract IntRange getDefensePower();
	
	
	
}
