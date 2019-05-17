package rpg.api.entity.item;

import rpg.Logger;

public abstract class Weapon extends ItemStack {
	
	public Weapon(final String name) {
		this(name, false);
	}
	
	public Weapon(final String name, final boolean questItem) {
		super(name, questItem);
		
		type = ItemType.WEAPON;
		stackSize = 1;
	}
	
	public abstract IntRange getAttackPower();
	
	public static class IntRange {
		int from, to;
		
		public IntRange() {
			
		}
		
		public IntRange(final int to) {
			this.to = to;
		}
		
		public IntRange(final int from, final int to) {
			this.from = from;
			this.to = to;
		}
		
		public int getFrom() {
			return from;
		}
		
		public int getTo() {
			return to;
		}
		
		public void setFrom(final int from) {
			this.from = from;
		}
		
		public void setTo(final int to) {
			this.to = to;
		}
		
		public int getRandom() {
			final int temp = (int) (Math.random() * (to - from) + from);
			Logger.debug("[Weapon::IntRange] >> Damage:" + temp);
			return temp;
		}
	}
}
