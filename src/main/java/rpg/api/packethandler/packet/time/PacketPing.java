package rpg.api.packethandler.packet.time;

import rpg.api.packethandler.packet.Packet;

public class PacketPing extends PacketTime {
	
	@Override
	public int packetId() {
		return 0;
	}
	
	@Override
	public int packetPhase() {
		return -1;
	}
	
	@Override
	public Packet response() {
		return new PacketPong();
	}
}
