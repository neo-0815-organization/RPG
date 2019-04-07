package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.entity.item.Inventory;
import rpg.api.eventhandling.EventType;
import rpg.api.units.DistanceValue;

/**
 * The class represents a player.
 * 
 * @author Neo Hornberger
 */
public class Player extends LivingEntity {
	private int				xpLevel, mpLevel;
	private float			xp, mp;
	private final Inventory	inv;
	
	/**
	 * Constructs a player with the standard display name ("entity.player.name").
	 */
	public Player() {
		super("entity.player");
		
		hitbox = new Hitbox(new DistanceValue(1d), new DistanceValue(1d));
		inv = new Inventory();
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
		this.xp = xp % 1;
	}
	
	public void addXP(final float xp) {
		xpLevel += (int) xp;
		
		setXP(xp);
	}
	
	public int getXPLevel() {
		return xpLevel;
	}
	
	public void setXPLevel(final int xpLevel) {
		this.xpLevel = xpLevel;
	}
	
	public float getMP() {
		return mp;
	}
	
	public void setMP(final float mp) {
		this.mp = mp % 1;
	}
	
	public void addMP(final float mp) {
		mpLevel += (int) mp;
		
		setMP(mp);
	}
	
	public int getMPLevel() {
		return mpLevel;
	}
	
	public void setMPLevel(final int mpLevel) {
		this.mpLevel = mpLevel;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
