package rpg.api.packethandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.packet.time.PacketPong;

/**
 * This thread is listening to an {@link InputStream}, reading the transfered
 * bytes,<br>
 * writing these bytes to a {@link ByteBuffer}, executing the matching
 * {@link Packet}<br>
 * (and if possible sending the response {@link Packet} via an
 * {@link OutputStream}).
 *
 * @author Neo Hornberger
 */
public abstract class ListeningThread extends Thread {
	private final InputStream	in;
	private final OutputStream	out;
	
	/**
	 * Constructs the {@link ListeningThread} with the {@link Socket} 'socket'.
	 *
	 * @param socket
	 *     the {@link Socket}s {@link InputStream} and {@link OutputStream} will be
	 *     used to construct this {@link ListeningThread}
	 * @throws IOException
	 *     if an I/O error occures
	 */
	public ListeningThread(final Socket socket) throws IOException {
		super("Listening-Thread -- " + socket.getLocalAddress() + ":" + socket.getLocalPort());
		
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}
	
	/**
	 * Constructs the {@link ListeningThread} with the {@link InputStream} 'in' and
	 * the {@link OutputStream} 'out'.
	 *
	 * @param in
	 *     the {@link InputStream} that will be used to construct this
	 *     {@link ListeningThread}
	 * @param out
	 *     the {@link OutputStream} that will be used to construct this
	 *     {@link ListeningThread}
	 */
	public ListeningThread(final InputStream in, final OutputStream out) {
		super("Listening-Thread");
		
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void run() {
		while(!isInterrupted())
			try {
				if(in.available() > 0) {
					final ByteBuffer buf = new ByteBuffer().readFromInputStream(in);
					final Packet packet = connection().getPacket(buf.readInt(), buf.readInt());
					final ByteBuffer packetContent = new ByteBuffer().setLimit(buf.readInt());
					
					packetContent.write(buf.toByteArray());
					packet.fromBuffer(packetContent);
					
					if(packet instanceof PacketPong) connection().onPong((PacketPong) packet);
					connection().onPacketReceived(packet);
					
					if(packet.response() != null) connection().sendPacket(packet.response(), out);
				}
			} catch(final IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * The {@link Connection} instance.
	 */
	protected abstract Connection connection();
}
