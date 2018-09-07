package rpg.api.packethandler.packet.time;

import java.util.HashMap;

import rpg.api.packethandler.ByteBuffer;
import rpg.api.packethandler.packet.Packet;

public abstract class PacketTime extends Packet {
	protected long time;
	
	@Override
	public void init(Object... objects) {
		if(objects.length != 0) if(objects[0] instanceof Long) {
			time = (long) objects[0];
			
			return;
		}
		
		time = System.currentTimeMillis();
	}
	
	@Override
	public void fromBuffer(ByteBuffer buf) {
		time = buf.readLong();
	}
	
	@Override
	public void toBuffer(ByteBuffer buf) {
		buf.writeLong(time);
	}
	
	@Override
	public void values(HashMap<String, Object> fields) {
		fields.put("time", time);
	}
}
