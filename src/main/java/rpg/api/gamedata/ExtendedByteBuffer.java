package rpg.api.gamedata;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.entity.Entity;
import rpg.api.entity.EntityCreator;
import rpg.api.packethandler.ByteBuffer;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.Vec2D;

public class ExtendedByteBuffer extends ByteBuffer {
	
	public void writeDirection(final Direction d) {
		write(d.getId());
	}
	
	public void writeVec2D(final Vec2D<?> v) {
		writeDouble(v.getX().getValueTiles());
		writeDouble(v.getY().getValueTiles());
	}
	
	public void writeUUID(final UUID u) {
		writeString(u.toString(), false);
	}
	
	public void writeEntity(final Entity e) {
		writeString(e.getUnlocalizedName());
		writeVec2D(e.getLocation());
		writeVec2D(e.getVelocity());
		writeDirection(e.getLookingDirection());
		writeString(e.getImageName());
		writeUUID(e.getUniqueId());
	}
	
	public Direction readDirection() {
		return Direction.getDirectionById(read());
	}
	
	public Vec2D<?> readVec2D() {
		return ModifiableVec2D.createXY(readDouble(), readDouble());
	}
	
	public UUID readUUID() {
		return UUID.fromString(readString(36));
	}
	
	public Entity readEntity() {
		return EntityCreator.create(readString(), readVec2D(), readVec2D(), readDirection(), readString(), readUUID());
	}
}
