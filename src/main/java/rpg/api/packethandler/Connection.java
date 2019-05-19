package rpg.api.packethandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import rpg.Logger;
import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.packet.time.PacketPong;

/**
 * The class Connection represents the connection between a client and a server.
 *
 * @author Neo Hornberger
 */
public abstract class Connection extends PacketRegistry {
	private final Connection INSTANCE = this;
	private final OutputStream out;
	
	private ListeningThread listeningThread;
	private long millis;
	private int ping;
	
	public Connection(final OutputStream out) {
		this.out = out;
	}
	
	/**
	 * Will be evaluated when the {@link Connection} has received a
	 * {@link Packet}.
	 *
	 * @param packet
	 *            the received {@link Packet}
	 */
	protected abstract void onPacketReceived(Packet packet);
	
	protected void onPong(final PacketPong packet) {
		ping = (int) ((long) packet.values().get("time") - millis);
	}
	
	/**
	 * Sends the {@link Packet} 'packet' with the {@link Object}[] 'objects' as
	 * arguments to the {@link OutputStream} of this {@link Connection}
	 * instance.
	 *
	 * @param packet
	 *            - the {@link Packet} that will be sent
	 * @param objects
	 *            - the arguments which will be used to initialize the
	 *            {@link Packet} 'packet'
	 * @throws IOException
	 *             if an I/O error occures
	 */
	protected void sendPacket(final Packet packet, final Object... objects) throws IOException {
		if(objects == null || objects.length == 0) packet.init();
		else packet.init(objects);
		
		final ByteBuffer packetContent = new ByteBuffer();
		packet.toBuffer(packetContent);
		
		final ByteBuffer buf = new ByteBuffer();
		buf.writeInt(packet.packetPhase());
		buf.writeInt(packet.packetId());
		buf.writeInt(packetContent.getWriteCursor());
		buf.write(packetContent.toByteArray());
		
		out.write(buf.toByteArray());
		out.flush();
	}
	
	/**
	 * Sends the {@link Packet} 'packet' with {@code null} as arguments.
	 *
	 * @param packet
	 *            - the {@link Packet} that will be sent
	 * @throws IOException
	 *             if an I/O error occures
	 * @see #sendPacket(Packet, Object...)
	 */
	protected void sendPacket(final Packet packet) throws IOException {
		sendPacket(packet);
	}
	
	protected void pingTest() {
		millis = System.currentTimeMillis();
		
		try {
			sendPacket(getPacket(-1, 0), millis);
		}catch(final IOException e) {
			Logger.error(e);
		}
	}
	
	/**
	 * Gets the ping.
	 *
	 * @return the ping in milliseconds
	 */
	public int getPing() {
		return ping;
	}
	
	/**
	 * Initializes the {@link ListeningThread} with the {@link Socket} 'socket'.
	 *
	 * @param socket
	 *            the {@link Socket} which will be used to construct the
	 *            {@link ListeningThread}
	 */
	protected void initListeningThread(final Socket socket) throws IOException {
		if(listeningThread == null) listeningThread = new ListeningThread(socket) {
			
			@Override
			protected Connection connection() {
				return INSTANCE;
			}
		};
	}
	
	/**
	 * Initializes the {@link ListeningThread} with the {@link InputStream} 'in'
	 * and the {@link OutputStream} 'out'.
	 *
	 * @param in
	 *            the {@link InputStream} which will be used to construct the
	 *            {@link ListeningThread}
	 * @param out
	 *            the {@link OutputStream} which will be used to construct the
	 *            {@link ListeningThread}
	 */
	protected void initListeningThread(final InputStream in, final OutputStream out) {
		if(listeningThread == null) listeningThread = new ListeningThread(in, out) {
			
			@Override
			protected Connection connection() {
				return INSTANCE;
			}
		};
	}
	
	/**
	 * Starts the {@link ListeningThread}.
	 *
	 * @see Thread#start()
	 */
	public void startListening() {
		if(listeningThread != null) listeningThread.start();
	}
	
	/**
	 * Interrupts the {@link ListeningThread}.
	 *
	 * @see Thread#interrupt()
	 */
	public void stopListening() {
		if(listeningThread != null) listeningThread.interrupt();
	}
}
