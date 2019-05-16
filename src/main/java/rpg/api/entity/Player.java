package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.entity.Person.CharacterSheet;
import rpg.api.entity.item.Inventory;
import rpg.api.eventhandling.EventType;
import rpg.api.units.DistanceValue;

/**
 * The class represents a player.
 * 
 * @author Neo Hornberger
 */
public class Player extends LivingEntity {
	private float xp, mp;
	private final Inventory inv;
	
	/**
	 * Constructs a player with the standard display name
	 * ("entity.player.name").
	 */
	public Player() {
		super("entity.player");
		
		hitbox = new Hitbox(new DistanceValue(1d), new DistanceValue(1d));
		inv = new Inventory();
		
		setSprite(CharacterSheet.PLAYER_NATUREGUARDIAN_MALE.getSprite());
	}
	
	@Override
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	public float getXP() {
		return xp;
	}
	
	public void setXP(final float xp) {
		this.xp = xp;
	}
	
	public void addXP(final float xp) {
		this.xp += xp;
	}
	
	public int getXPLevel() {
		return (int) xp;
	}
	
	public void setXPLevel(final int level) {
		xp = level + xp % 1;
	}
	
	public float getMP() {
		return mp;
	}
	
	public void setMP(final float mp) {
		this.mp = mp;
	}
	
	public void addMP(final float mp) {
		this.mp += mp;
	}
	
	public int getMPLevel() {
		return (int) mp;
	}
	
	public void setMPLevel(final int level) {
		mp = level + mp % 1;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
