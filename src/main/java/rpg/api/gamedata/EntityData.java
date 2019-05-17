package rpg.api.gamedata;

import java.io.IOException;
import java.util.UUID;

import rpg.api.Direction;
import rpg.api.entity.Entity;
import rpg.api.entity.EntityRing;
import rpg.api.entity.LivingEntity;
import rpg.api.entity.Player;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * A class representing the data of an {@link Entity}.
 * 
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer
 */
public class EntityData extends GameData {
	private Entity e;
	private final UUID uuid;
	
	/**
	 * Constructs a new data representation of an {@link Entity}.
	 * 
	 * @param entity
	 *            the {@link Entity} which data should be representated by this
	 *            {@link EntityData}
	 * @param path
	 *            the directory where the {@link EntityData} file
	 *            ("{@code path}/{@code entity.getUniqueId().toString()}.data")
	 *            is stored
	 */
	public EntityData(final Entity entity, final String path) {
		super(path + "/" + entity.getUniqueId().toString() + ".data");
		
		e = entity;
		uuid = entity.getUniqueId();
	}
	
	public EntityData(final UUID uuid, final String path) {
		super(path + "/" + uuid.toString() + ".data");
		
		this.uuid = uuid;
	}
	
	@Override
	public void load() throws IOException {
		super.load();
		
		e = null;
		
		switch((String) get("type")) {
			case "Player":
				e = new Player();
				break;
			case "EntityRing":
				e = new EntityRing();
				break;
		}
		
		if(e != null) {
			e.setLocation((Vec2D<?>) get("location"));
			e.setLookingDirection((Direction) get("direction"));
			e.setVelocity((ModifiableVec2D) get("velocity"));
			e.setUniqueId((UUID) get("uuid"));
			
			if(e instanceof LivingEntity) {
				final LivingEntity l = (LivingEntity) e;
				
				l.setHP((int) get("hp"));
				l.setMaxHP((int) get("max_hp"));
			}
			
			if(e instanceof Player) {
				final Player p = (Player) e;
				
				p.setXP((float) get("xp"));
				p.setMP((float) get("mp"));
			}
		}
	}
	
	@Override
	public void save() throws IOException {
		set("type", e.getClass().getSimpleName());
		
		set("location", e.getLocation());
		set("direction", e.getLookingDirection());
		set("velocity", e.getVelocity());
		set("display_name", e.getDisplayName());
		set("sprite", e.getSprite().getName());
		set("uuid", e.getUniqueId());
		set("hitbox", e.getHitbox());
		
		if(e instanceof LivingEntity) {
			final LivingEntity l = (LivingEntity) e;
			
			set("hp", l.getHP());
			set("max_hp", l.getMaxHP());
		}
		
		if(e instanceof Player) {
			final Player p = (Player) e;
			
			set("xp", p.getXP());
			set("mp", p.getMP());
		}
		
		super.save();
	}
	
	public Entity getEntity() {
		return e;
	}
}
