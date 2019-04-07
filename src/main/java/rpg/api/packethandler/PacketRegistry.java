package rpg.api.packethandler;

import java.util.ArrayList;
import java.util.HashMap;

import rpg.api.packethandler.packet.Packet;

/**
 * The class PacketRegistry makes it easy to deal with {@link Packet}s.
 *
 * @author Neo Hornberger
 */
public class PacketRegistry {
	protected final HashMap<Integer, HashMap<Integer, Packet>> packets = new HashMap<>();
	
	/**
	 * Registers the {@link Packet} 'packet' under its phase and id.
	 *
	 * @param packet
	 *     the {@link Packet} that will be registered
	 * @throws IllegalArgumentException
	 *     if an alreadya registered {@link Packet} has the same id as 'packet'
	 */
	public void registerPacket(final Packet packet) {
		final int phase = packet.packetPhase(),
				id = packet.packetId();
		
		if(!packets.containsKey(phase)) packets.put(phase, new HashMap<>());
		if(packets.get(phase).containsKey(id)) throw new IllegalArgumentException("Packet with id('" + id + "') is registered");
		
		packets.get(phase).put(id, packet);
	}
	
	/**
	 * Gets the {@link Packet} with the phase 'phase' and the id 'id'.
	 *
	 * @param phase
	 *     the phase the {@link Packet} should have
	 * @param id
	 *     the id the {@link Packet} should have
	 * @return the {@link Packet}
	 * @throws IllegalArgumentException
	 *     if phase 'phase' doesn't exist or {@link Packet} with id 'id' isn't
	 *     registered
	 */
	public Packet getPacket(final int phase, final int id) {
		if(!packets.containsKey(phase)) throw new IllegalArgumentException("Phase '" + phase + "' doesn't exist");
		if(!packets.get(phase).containsKey(id)) throw new IllegalArgumentException("Packet with id('" + id + "') isn't registered");
		
		return packets.get(phase).get(id);
	}
	
	/**
	 * Gets all registered {@link Packet}s.
	 *
	 * @return the {@link ArrayList} filled with all registered {@link Packet}s
	 */
	public ArrayList<Packet> getAllPackets() {
		final ArrayList<Packet> list = new ArrayList<>();
		
		for(final HashMap<Integer, Packet> map : packets.values())
			for(final Packet packet : map.values())
				list.add(packet);
			
		return list;
	}
}
