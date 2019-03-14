package rpg.api.entity;

/**
 * The class represents a player.
 * 
 * @author Neo Hornberger
 */
public class Player extends LivingEntity {
	private int xpLevel, mpLevel;
	private float xp, mp;
	
	/**
	 * Constructs a player with the standard display name
	 * ("entity.player.name").
	 */
	public Player() {
		super("entity.player");
	}
	
	public float getXP() {
		return xp;
	}
	
	public void setXP(final float xp) {
		this.xp = xp % 1;
	}
	
	public void addXP(final float xp) {
		this.xp += xp;
		
		if(this.xp >= 1) for(int i = 0; i < (int) this.xp; i++) {
			if(this.xp >= 1) {
				this.xp--;
				
				i--;
			}
			
			xpLevel++;
		}
		else if(this.xp < 0) {
			for(int i = 0; i < this.xp * -1; i++) {
				if(this.xp <= -1) {
					this.xp++;
					
					i--;
				}
				
				xpLevel--;
			}
			
			this.xp = 1 + this.xp;
		}
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
		this.mp += mp;
		
		if(this.mp >= 1) for(int i = 0; i < (int) this.mp; i++) {
			if(this.mp >= 1) {
				this.mp--;
				
				i--;
			}
			
			mpLevel++;
		}
		else if(this.mp < 0) {
			for(int i = 0; i < this.mp * -1; i++) {
				if(this.mp <= -1) {
					this.mp++;
					
					i--;
				}
				
				mpLevel--;
			}
			
			this.mp = 1 + this.mp;
		}
	}
	
	public int getMPLevel() {
		return mpLevel;
	}
	
	public void setMPLevel(final int mpLevel) {
		this.mpLevel = mpLevel;
	}
}
