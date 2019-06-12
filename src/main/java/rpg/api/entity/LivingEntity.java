package rpg.api.entity;

import rpg.api.entity.item.Weapon.IntRange;

public abstract class LivingEntity extends Entity {
	protected int hp, maxHP;
	
	public LivingEntity(final String name) {
		this(name, 10);
	}
	
	public LivingEntity(final String name, final int maxHP) {
		super(name, true);
		
		hp = maxHP;
		this.maxHP = maxHP;
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
	
	/**
	 * Reduces the hp of this {@link Entity}. Returns the result of this
	 * operation.
	 * 
	 * @param hp
	 *            the damage this {@link Entity} should take
	 * @return if the hp is less than or equal to zero
	 */
	public boolean reduceHP(final int hp) {
		this.hp -= hp;
		
		return this.hp <= 0;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
	public void setMaxHP(final int maxHP) {
		this.maxHP = maxHP;
	}
	
	/** The damageRange (Override this) */
	public IntRange getWeaponDamageRange() {
		return new IntRange(1, 5);
	}
}
