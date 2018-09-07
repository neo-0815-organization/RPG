package rpg.api.packethandler.packet;

import rpg.api.packethandler.packet.time.PacketTime;

public class PacketPing extends PacketTime {
	
	@Override
	public int packetId() {
		return 0;
	}
	
	@Override
	public int packetPhase() {
		return 0;
	}
	
	@Override
	public Packet response() {
		return new PacketPong();
	}
}
