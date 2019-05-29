package rpg.api.gamedata;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.collision.Hitbox;
import rpg.api.packethandler.ByteBuffer;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * An extension of the {@link ByteBuffer} class. It gives more options for
 * writing and reading data.
 * 
 * @author Neo Hornberger
 */
public class ExtendedByteBuffer extends ByteBuffer {
	
	/**
	 * Writes the {@link Direction} 'd' to the buffer.
	 * 
	 * @param d
	 *            the {@link Direction} to write
	 * @see ByteBuffer#write(byte)
	 */
	public void writeDirection(final Direction d) {
		write(d.getId());
	}
	
	/**
	 * Writes the {@link Vec2D} 'v' to the buffer.
	 * 
	 * @param v
	 *            the {@link Vec2D} to write
	 * @see ByteBuffer#writeDouble(double)
	 */
	public void writeVec2D(final Vec2D<?> v) {
		writeDouble(v.getX().getValueTiles());
		writeDouble(v.getY().getValueTiles());
	}
	
	/**
	 * Writes the {@link UUID} 'u' to the buffer.
	 * 
	 * @param u
	 *            the {@link UUID} to write
	 * @see ByteBuffer#writeString(String, boolean)
	 */
	public void writeUUID(final UUID u) {
		writeString(u.toString(), false);
	}
	
	/**
	 * Writes the {@link Hitbox} 'h' to the buffer.
	 *
	 * @param h
	 *            the {@link Hitbox} to write
	 * @see ByteBuffer#writeDouble(double)
	 */
	public void writeHitbox(final Hitbox h) {
		writeDouble(h.getWidth().getValueTiles());
		writeDouble(h.getHeight().getValueTiles());
	}
	
	/**
	 * Reads one byte from the buffer and interprets it as a {@link Direction}.
	 * 
	 * @return the {@link Direction} read
	 * @see ByteBuffer#read()
	 */
	public Direction readDirection() {
		return Direction.getDirectionById(read());
	}
	
	/**
	 * Reads two doubles from the buffer and interprets them as a {@link Vec2D}.
	 * 
	 * @return the {@link ModifiableVec2D} read
	 * @see ByteBuffer#readDouble()
	 */
	public Vec2D<?> readVec2D() {
		return ModifiableVec2D.createXY(readDouble(), readDouble());
	}
	
	/**
	 * Reads one {@link String} with the length {@code 36} from the buffer and
	 * interprets it as a {@link UUID}.
	 * 
	 * @return the {@link UUID} read
	 * @see ByteBuffer#readString(int)
	 */
	public UUID readUUID() {
		return UUID.fromString(readString(36));
	}
	
	/**
	 * Reads two doubles from the buffer and interprets them as a
	 * {@link Hitbox}.
	 * 
	 * @return the {@link Hitbox} read
	 * @see ByteBuffer#readDouble()
	 */
	public Hitbox readHitbox() {
		return new Hitbox(readDouble(), readDouble());
	}
}
