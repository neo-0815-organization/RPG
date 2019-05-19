package rpg.api.packethandler.packet.time;

import java.util.HashMap;

import rpg.api.packethandler.ByteBuffer;
import rpg.api.packethandler.packet.Packet;

/**
 * The {@link Packet} which transmits a timestamp.
 *
 * @author Neo Hornberger
 */
public abstract class PacketTime extends Packet {
	protected long time;
	
	@Override
	public void init(final Object... objects) {
		if(objects.length != 0) if(objects[0] instanceof Long) {
			time = (long) objects[0];
			
			return;
		}
		
		time = System.currentTimeMillis();
	}
	
	@Override
	public void fromBuffer(final ByteBuffer buf) {
		time = buf.readLong();
	}
	
	@Override
	public void toBuffer(final ByteBuffer buf) {
		buf.writeLong(time);
	}
	
	@Override
	public void values(final HashMap<String, Object> fields) {
		fields.put("time", time);
	}
}
