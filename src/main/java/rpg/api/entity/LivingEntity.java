package rpg.api.entity;

import rpg.api.entity.item.Weapon.IntRange;

public abstract class LivingEntity extends Entity {
	protected int hp, maxHP;
	
	public LivingEntity(final String name) {
		this(name, 10);
	}
	
	public LivingEntity(final String name, final int maxHP) {
		super(name);
		
		hp = maxHP;
		this.maxHP = maxHP;
	}
	
	/**
	 * Reduces the HP of the Entity, return true, when hp are smaller or equal then zero after applying damage
	 * @param damage
	 * @return hp - damage <= 0
	 */
	public boolean reduceHP(final int damage) {
		hp -= damage;
		return hp <= 0;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void setHP(final int hp) {
		this.hp = hp;
	}
	
	public void addHP(final int hp) {
		this.hp += hp;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
  
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	/** The damageRange (Override this)*/
	public IntRange getWeaponDamageRange() {
		return new IntRange(1, 5);
  }
}
