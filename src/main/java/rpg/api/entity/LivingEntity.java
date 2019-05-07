package rpg.api.entity;

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
}
