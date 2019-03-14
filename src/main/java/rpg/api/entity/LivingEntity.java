package rpg.api.entity;

public abstract class LivingEntity extends Entity {
	protected int hp, maxHP;
	
	public LivingEntity(final String name) {
		super(name);
		
		hp = 10;
		maxHP = 10;
	}
	
	public LivingEntity(final String name, final int maxHP) {
		super(name);
		
		hp = maxHP;
		this.maxHP = maxHP;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void setHP(final int hp) {
		this.hp = hp;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
}
