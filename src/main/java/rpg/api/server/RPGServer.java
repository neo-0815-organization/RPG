package rpg.api.server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

import rpg.api.entity.Controller;
import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.server.Server;

/**
 * The class RPGServer respresents a dedicated {@link Server} the user can
 * connect to and play on.
 *
 * @author Neo Hornberger
 */
public class RPGServer extends Server {
	protected HashMap<UUID, Controller> controllers = new HashMap<>();
	
	/**
	 * Constructs a new {@link RPGServer} and binds it to the port 'port'.
	 *
	 * @param port
	 *     the port to bind to
	 */
	public RPGServer(final int port) throws IOException {
		super(port);
	}
	
	@Override
	protected void onPacketReceived(final Socket client, final Packet packet) {}
}
