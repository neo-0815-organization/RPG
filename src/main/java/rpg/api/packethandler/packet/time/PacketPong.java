package rpg.api.packethandler.packet.time;

import rpg.api.packethandler.packet.Packet;

/**
 * The {@link Packet} which transmits the request to end the ping test.
 *
 * @see PacketTime
 * @author Neo Hornberger
 */
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
