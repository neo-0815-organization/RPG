package rpg.api.packethandler.packet.time;

public class PacketPong extends PacketTime {
	
	@Override
	public int packetId() {
		return 1;
	}
	
	@Override
	public int packetPhase() {
		return -1;
	}
}
