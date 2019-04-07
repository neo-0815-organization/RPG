package rpg.api.packethandler.packet;

import java.util.HashMap;

import rpg.api.packethandler.ByteBuffer;

/**
 * The abstract class Packet.
 *
 * @author Neo Hornberger
 */
public abstract class Packet {
	
	/**
	 * The id of this packet.
	 *
	 * @return the id
	 */
	public abstract int packetId();
	
	/**
	 * The phase of communcation to which this {@link Packet} correspondes.
	 *
	 * @return the phase
	 */
	public abstract int packetPhase();
	
	/**
	 * Reads data from the {@link ByteBuffer} 'buf'.
	 *
	 * @param buf
	 *     the {@link ByteBuffer}
	 */
	public abstract void fromBuffer(ByteBuffer buf);
	
	/**
	 * Writes data to the {@link ByteBuffer} 'buf'.
	 *
	 * @param buf
	 *     the {@link ByteBuffer}
	 */
	public abstract void toBuffer(ByteBuffer buf);
	
	/**
	 * Inits this packet with {@link Object}[] as arguments.
	 *
	 * @param objects
	 *     the arguments
	 */
	public void init(final Object... objects) {}
	
	/**
	 * Returns a {@link HashMap} with {@link String}s as keys and associated
	 * {@link Object}s as values.
	 *
	 * @return the {@link HashMap} values
	 */
	public HashMap<String, Object> values() {
		final HashMap<String, Object> fields = new HashMap<>();
		
		values(fields);
		
		return fields;
	}
	
	/**
	 * Returns a {@link HashMap} with {@link String}s as keys and associated
	 * {@link Object}s as values.
	 *
	 * @param fields
	 *     the {@link HashMap} which will contain the values
	 */
	public void values(final HashMap<String, Object> fields) {}
	
	/**
	 * Gets the response {@link Packet}.
	 * {@code null} if no response packet is defined.
	 *
	 * @return the response {@link Packet}
	 */
	public Packet response() {
		return null;
	}
}
