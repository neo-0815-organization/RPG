package rpg.api.packethandler.packet.client;

import java.util.HashMap;
import java.util.UUID;

import rpg.api.packethandler.ByteBuffer;
import rpg.api.packethandler.packet.Packet;

public class PacketHandshake extends Packet {
	protected UUID uuid;
	
	@Override
	public void init(Object... objects) {
		if(objects.length != 0) if(objects[0] instanceof UUID) {
			uuid = (UUID) objects[0];
			
			return;
		}else if(objects[0] instanceof String) try {
			uuid = UUID.fromString((String) objects[0]);
			
			return;
		}catch(final IllegalArgumentException e) {}
		
		uuid = new UUID(0L, 0L);
	}
	
	@Override
	public int packetId() {
		return 0;
	}
	
	@Override
	public int packetPhase() {
		return 0;
	}
	
	@Override
	public void fromBuffer(ByteBuffer buf) {
		uuid = new UUID(buf.readLong(), buf.readLong());
	}
	
	@Override
	public void toBuffer(ByteBuffer buf) {
		buf.writeLong(uuid.getMostSignificantBits());
		buf.writeLong(uuid.getLeastSignificantBits());
	}
	
	@Override
	public void values(HashMap<String, Object> fields) {
		fields.put("uuid", uuid);
	}
}
