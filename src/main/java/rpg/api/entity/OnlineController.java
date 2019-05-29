package rpg.api.entity;

import java.io.IOException;
import java.net.UnknownHostException;

import rpg.api.packethandler.client.Client;
import rpg.api.packethandler.packet.Packet;

/**
 * The class OnlineController used to control an {@link Entity} remotely.
 */
public class OnlineController extends Client implements Controller {
	private Entity entity;
	
	/**
	 * Constructor of {@link OnlineController}
	 *
	 * @see Client#Client(String, int)
	 */
	public OnlineController(final String host, final int port) throws UnknownHostException, IOException {
		super(host, port);
	}
	
	@Override
	protected void onPacketReceived(final Packet packet) {}
	
	@Override
	public Entity getEntity() {
		return entity;
	}
}
