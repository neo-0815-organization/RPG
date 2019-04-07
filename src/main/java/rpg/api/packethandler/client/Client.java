package rpg.api.packethandler.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import rpg.api.packethandler.Connection;
import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.packet.time.PacketPing;
import rpg.api.packethandler.packet.time.PacketPong;
import rpg.api.packethandler.server.Server;

/**
 * The abstract class Client represents a client that can communicate with a
 * {@link Server} instance.
 *
 * @author Neo Hornberger
 */
public abstract class Client extends Connection {
	private static Socket socket;
	
	/**
	 * Constructes a new {@link Client} and connects it to the server at
	 * 'host':'port'. (localhost:8080)
	 *
	 * @see Connection#Connection(Socket)
	 */
	public Client(final String host, final int port) throws UnknownHostException, IOException {
		super((socket = new Socket(host, port)).getOutputStream());
		
		initListeningThread(socket);
		
		registerPacket(new PacketPing());
		registerPacket(new PacketPong());
	}
	
	/**
	 * Sends the the {@link Packet} with the 'id' in the 'phase' to the
	 * connected {@link Server}.
	 *
	 * @param phase
	 *     the phase of the communication the {@link Packet} correspondes
	 * @param id
	 *     the id of the {@link Packet}
	 * @throws IOException
	 *     if an I/O error occures
	 * @see Connection#sendPacket(Packet)
	 */
	protected void sendPacket(final int phase, final int id) throws IOException {
		sendPacket(getPacket(phase, id));
	}
	
	/**
	 * Sends the the {@link Packet} with the 'id' in the 'phase' with the
	 * {@link Object}[] 'objects' as arguments to the connected {@link Server}.
	 *
	 * @param phase
	 *     the phase of the communication the {@link Packet} correspondes
	 * @param id
	 *     the id of the {@link Packet}
	 * @param objects
	 *     the arguments which will be used to initialize the {@link Packet}
	 * @throws IOException
	 *     if an I/O error occures
	 * @see Connection#sendPacket(Packet, Object...)
	 */
	protected void sendPacket(final int phase, final int id, final Object... objects) throws IOException {
		sendPacket(getPacket(phase, id), objects);
	}
}
