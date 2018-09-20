package rpg.api.server;

import java.io.IOException;
import java.net.Socket;

import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.server.Server;

public class RPGServer extends Server {
	
	public RPGServer(int port) throws IOException {
		super(port);
	}
	
	@Override
	protected void onPacketReceived(Socket client, Packet packet) {}
}
