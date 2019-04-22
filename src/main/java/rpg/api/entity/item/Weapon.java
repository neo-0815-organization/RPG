package rpg.api.entity.item;

import rpg.api.gfx.menus.combatMenu.CombatAnimationSheet;

public abstract class Weapon extends ItemStack{
	public static final CombatAnimationSheet ATTACK_ANIMATION_SHEET = new CombatAnimationSheet("attack.png", 4, 0.25, 1);
	
	public Weapon(final String name) {
		this(name, false);
	}
	
	public Weapon(final String name, final boolean questItem) {
		super(name, questItem);
		
		this.type = ItemType.WEAPON;
		this.stackSize = 1;
	}
	
	public abstract IntRange getAttackPower();
	
	
	public static class IntRange {
		int from, to;
		
		public IntRange() {
			
		}
		
		public IntRange(int to) {
			this.to = to;
		}
		
		public IntRange(int from, int to) {
			this.from = from;
			this.to = to;
		}
		
		public int getFrom() {
			return from;
		}
		
		public int getTo() {
			return to;
		}
		
		public void setFrom(int from) {
			this.from = from;
		}
		
		public void setTo(int to) {
			this.to = to;
		}
	}
}
