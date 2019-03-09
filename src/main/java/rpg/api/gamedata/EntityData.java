package rpg.api.gamedata;

import java.io.IOException;

import rpg.api.entity.Entity;

/**
 * A class representing the data of an {@link Entity}.
 * 
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer
 */
public class EntityData extends GameData {
	private final Entity e;
	
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
	}
	
	@Override
	public void save() throws IOException {
		set("location", e.getLocation());
		set("direction", e.getLookingDirection());
		set("velocity", e.getVelocity());
		set("display_name", e.getDisplayName());
		set("image", e.getImageName());
		set("uuid", e.getUniqueId());
		
		super.save();
	}
}
