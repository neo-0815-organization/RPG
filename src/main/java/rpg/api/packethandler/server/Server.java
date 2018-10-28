package rpg.api.packethandler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import rpg.api.packethandler.PacketRegistry;
import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.packet.time.PacketPing;
import rpg.api.packethandler.packet.time.PacketPong;

public abstract class Server extends PacketRegistry {
	private final ServerSocket socket;
	private final Thread acceptingThread;
	private final Server INSTANCE = this;
	
	protected final HashMap<Socket, ClientConnection> clients = new HashMap<>();
	
	public Server(final int port) throws IOException {
		socket = new ServerSocket(port);
		
		acceptingThread = new Thread("Accepting-Thread -- " + socket.getLocalPort()) {
			
			@Override
			public void run() {
				while(!isInterrupted())
					try {
						final Socket client = socket.accept();
						final ClientConnection clientCon = new ClientConnection(INSTANCE, client);
						
						for(final Packet packet : getAllPackets())
							clientCon.registerPacket(packet);
						
						clientCon.startListening();
						clientCon.sendPacket(clientCon.getPacket(-1, 0)); // TODO change to enum usage '(-1, 0)'
						
						clients.put(client, clientCon);
					}catch(final IOException e) {
						e.printStackTrace();
					}
			}
		};
		
		registerPacket(new PacketPing());
		registerPacket(new PacketPong());
	}
	
	protected void start() {
		acceptingThread.start();
	}
	
	protected void stop() {
		acceptingThread.interrupt();
		
		for(final ClientConnection client : clients.values())
			client.stopListening();
	}
	
	/**
	 * Sends the {@link Packet} 'packet' to the {@link ClientConnection}
	 * associated with the {@link Socket} 'client'.
	 *
	 * @param client
	 *            - the {@link Socket}
	 * @param packet
	 *            - the {@link Packet}
	 * @throws IOException
	 *             if an I/O error occures
	 * @throws IllegalArgumentException
	 *             if 'client' hasn't an associated {@link ClientConnection}
	 */
	protected void sendPacket(final Socket client, final Packet packet) throws IOException, IllegalArgumentException {
		if(!clients.containsKey(client)) throw new IllegalArgumentException("This client is not connected to this server");
		
		clients.get(client).sendPacket(packet);
	}
	
	/**
	 * Sends the {@link Packet} 'packet' to all connected sockets.
	 *
	 * @param packet
	 *            - the {@link Packet}
	 * @throws IOException
	 *             if an I/O error occures
	 * @see #sendPacket(Socket, Packet)
	 */
	protected void broadcastPacket(final Packet packet) throws IOException {
		for(final Socket client : clients.keySet())
			sendPacket(client, packet);
	}
	
	protected abstract void onPacketReceived(Socket client, Packet packet);
	
	@Override
	public void registerPacket(final Packet packet) {
		super.registerPacket(packet);
		
		for(final Socket client : clients.keySet())
			clients.get(client).registerPacket(packet);
	}
}
