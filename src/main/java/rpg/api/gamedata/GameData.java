package rpg.api.gamedata;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Location;
import rpg.api.Vec2D;
import rpg.api.entity.Entity;
import rpg.api.packethandler.ByteBuffer;

public class GameData {
	private final File file;
	private ByteBuffer buffer;
	
	public GameData(final String path) {
		file = new File(path);
	}
	
	public void write(final Object obj) {
		if(obj instanceof Map) {
			buffer.write(MAP_IDENTIFIER);;
		}else if(obj instanceof Boolean) {
			buffer.write(BOOLEAN_IDENTIFIER);
			
			buffer.writeBoolean((boolean) obj);
		}else if(obj instanceof Byte) {
			buffer.write(BYTE_IDENTIFIER);
			
			buffer.write((byte) obj);
		}else if(obj instanceof Short) {
			buffer.write(SHORT_IDENTIFIER);
			
			buffer.writeShort((short) obj);
		}else if(obj instanceof Integer) {
			buffer.write(INT_IDENTIFIER);
			
			buffer.writeInt((int) obj);
		}else if(obj instanceof Long) {
			buffer.write(LONG_IDENTIFIER);
			
			buffer.writeLong((long) obj);
		}else if(obj instanceof Float) {
			buffer.write(FLOAT_IDENTIFIER);
			
			buffer.writeFloat((float) obj);
		}else if(obj instanceof Double) {
			buffer.write(DOUBLE_IDENTIFIER);
			
			buffer.writeDouble((double) obj);
		}else if(obj instanceof Character) {
			buffer.write(CHAR_IDENTIFIER);
			
			buffer.writeChar((char) obj);
		}else if(obj instanceof String) {
			buffer.write(STRING_IDENTIFIER);
			
			buffer.writeString((String) obj);
		}else if(obj instanceof List) {
			buffer.write(LIST_IDENTIFIER);;
		}else if(obj instanceof Location) {
			buffer.write(LOCATION_IDENTIFIER);
			
			final Location l = (Location) obj;
			buffer.writeInt(l.getX());
			buffer.writeInt(l.getY());
		}else if(obj instanceof Direction) {
			buffer.write(DIRECTION_IDENTIFIER);;
		}else if(obj instanceof Vec2D) {
			buffer.write(VEC2D_IDENTIFIER);;
		}else if(obj instanceof UUID) {
			buffer.write(UUID_IDENTIFIER);
			
			buffer.writeInt(36);
			buffer.writeString(((UUID) obj).toString(), false);
		}else if(obj instanceof Entity) {
			buffer.write(ENTITY_IDENTIFIER);;
		}
	}
	
	public Object read() {
		switch(buffer.read()) {
			case MAP_IDENTIFIER:
				return null;
			case BOOLEAN_IDENTIFIER:
				return buffer.readBoolean();
			case BYTE_IDENTIFIER:
				return buffer.read();
			case SHORT_IDENTIFIER:
				return null;
			case INT_IDENTIFIER:
				return null;
			case LONG_IDENTIFIER:
				return null;
			case FLOAT_IDENTIFIER:
				return null;
			case DOUBLE_IDENTIFIER:
				return null;
			case CHAR_IDENTIFIER:
				return null;
			case STRING_IDENTIFIER:
				return null;
			case LIST_IDENTIFIER:
				return null;
			case LOCATION_IDENTIFIER:
				return null;
			case DIRECTION_IDENTIFIER:
				return null;
			case VEC2D_IDENTIFIER:
				return null;
			case UUID_IDENTIFIER:
				return null;
			case ENTITY_IDENTIFIER:
				return null;
		}
		
		return null;
	}
	
	private static final byte MAP_IDENTIFIER = 0;
	private static final byte BOOLEAN_IDENTIFIER = 1;
	private static final byte BYTE_IDENTIFIER = 2;
	private static final byte SHORT_IDENTIFIER = 3;
	private static final byte INT_IDENTIFIER = 4;
	private static final byte LONG_IDENTIFIER = 5;
	private static final byte FLOAT_IDENTIFIER = 6;
	private static final byte DOUBLE_IDENTIFIER = 7;
	private static final byte CHAR_IDENTIFIER = 8;
	private static final byte STRING_IDENTIFIER = 9;
	private static final byte LIST_IDENTIFIER = 10;
	private static final byte LOCATION_IDENTIFIER = 11;
	private static final byte DIRECTION_IDENTIFIER = 12;
	private static final byte VEC2D_IDENTIFIER = 13;
	private static final byte UUID_IDENTIFIER = 14;
	private static final byte ENTITY_IDENTIFIER = 15;
}
