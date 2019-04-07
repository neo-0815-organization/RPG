package rpg.api.packethandler.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import rpg.api.packethandler.Connection;
import rpg.api.packethandler.client.Client;
import rpg.api.packethandler.packet.Packet;

/**
 * The class ClientConnection is a server-side representation of a
 * {@link Client}.
 *
 * @author Neo Hornberger
 */
public class ClientConnection extends Connection {
	private final Server	server;
	private final Socket	client;
	
	/**
	 * Constructs a new {@link ClientConnection} which is connected to the
	 * {@link Server} 'server'.
	 *
	 * @param server
	 *     the {@link Server} the 'client' is connected to
	 * @param client
	 *     the client which is respresented by this {@link ClientConnection}
	 * @see Connection#Connection(OutputStream)
	 */
	public ClientConnection(final Server server, final Socket client) throws IOException {
		super(client.getOutputStream());
		
		this.server = server;
		this.client = client;
		
		initListeningThread(client);
	}
	
	@Override
	protected void onPacketReceived(final Packet packet) {
		server.onPacketReceived(client, packet);
	}
	
	@Override
	protected void sendPacket(final Packet packet) throws IOException {
		super.sendPacket(packet);
	}
}
