package rpg.api.gamedata;

import java.io.IOException;

import rpg.api.entity.Entity;

public class EntityData extends GameData {
	private final Entity e;
	
	public EntityData(final Entity e, final String path) {
		super(path + "/" + e.getUniqueId().toString() + ".data");
		
		this.e = e;
	}
	
	@Override
	public void save() throws IOException {
		write(e.getLocation());
		write(e.getLookingDirection());
		write(e.getVelocity());
		write(e.getDisplayName());
		write(e.getImageName());
		write(e.getUniqueId());
		
		super.save();
	}
}
