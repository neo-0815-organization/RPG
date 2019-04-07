package rpg.api.packethandler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import rpg.api.packethandler.PacketRegistry;
import rpg.api.packethandler.client.Client;
import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.packet.time.PacketPing;
import rpg.api.packethandler.packet.time.PacketPong;

/**
 * The abstract class Server represents a server on which {@link Client}s can
 * connect to.
 *
 * @author Neo Hornberger
 */
public abstract class Server extends PacketRegistry {
	private final ServerSocket	socket;
	private final Thread		acceptingThread;
	private final Server		INSTANCE	= this;
	
	protected final HashMap<Socket, ClientConnection> clients = new HashMap<>();
	
	/**
	 * Constructs a new {@link Server} and bind it to the port 'port'.
	 *
	 * @param port
	 *     the port the {@link Server} will bound to
	 * @throws IOException
	 *     if an I/O error occures when opening the {@link ServerSocket}
	 */
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
					} catch(final IOException e) {
						e.printStackTrace();
					}
			}
		};
		
		registerPacket(new PacketPing());
		registerPacket(new PacketPong());
	}
	
	/**
	 * Starts the accepting thread of this {@link Server}.
	 *
	 * @see Thread#start()
	 */
	protected void start() {
		acceptingThread.start();
	}
	
	/**
	 * Interrupts the accepting thread of this {@link Server} and stops listening in
	 * all {@link ClientConnection}s.
	 *
	 * @see Thread#interrupt()
	 * @see ClientConnection#stopListening()
	 */
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
	 *     - the client
	 * @param packet
	 *     - the {@link Packet} that will be sent
	 * @throws IOException
	 *     if an I/O error occures
	 * @throws IllegalArgumentException
	 *     if 'client' hasn't an associated {@link ClientConnection}
	 */
	protected void sendPacket(final Socket client, final Packet packet) throws IOException, IllegalArgumentException {
		if(!clients.containsKey(client)) throw new IllegalArgumentException("This client is not connected to this server");
		
		clients.get(client).sendPacket(packet);
	}
	
	/**
	 * Broadcasts the {@link Packet} 'packet' to all connected sockets.
	 *
	 * @param packet
	 *     - the {@link Packet} that will be broadcasted
	 * @throws IOException
	 *     if an I/O error occures
	 * @see #sendPacket(Socket, Packet)
	 */
	protected void broadcastPacket(final Packet packet) throws IOException {
		for(final Socket client : clients.keySet())
			sendPacket(client, packet);
	}
	
	/**
	 * Will be evaluated when the {@link Server} has received a {@link Packet} from
	 * a client.
	 *
	 * @param client
	 *     the client that has sent the {@link Packet} 'packet'
	 * @param packet
	 *     the {@link Packet} that was sent by the client
	 */
	protected abstract void onPacketReceived(Socket client, Packet packet);
	
	@Override
	public void registerPacket(final Packet packet) {
		super.registerPacket(packet);
		
		for(final Socket client : clients.keySet())
			clients.get(client).registerPacket(packet);
	}
}
