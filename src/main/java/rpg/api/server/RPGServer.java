package rpg.api.server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

import rpg.api.entity.Controller;
import rpg.api.packethandler.packet.Packet;
import rpg.api.packethandler.server.Server;

public class RPGServer extends Server {
	protected HashMap<UUID, Controller> controllers = new HashMap<>();
	
	public RPGServer(int port) throws IOException {
		super(port);
	}
	
	@Override
	protected void onPacketReceived(Socket client, Packet packet) {}
}
