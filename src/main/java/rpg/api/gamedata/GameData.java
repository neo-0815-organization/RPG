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
	private File file;
	private ByteBuffer buffer;
	
	public GameData(String path) {
		file = new File(path);
	}
	
	public void writeBoolean(boolean b) {
		writeIdentifier(b);
		buffer.writeBoolean(b);
	}
	
	public void writeByte(byte b) {
		writeIdentifier(b);
		buffer.write(b);
	}
	
	public void writeShort(short s) {
		writeIdentifier(s);
		buffer.writeShort(s);
	}
	
	public void writeInt(int i) {
		writeIdentifier(i);
		buffer.writeInt(i);
	}
	
	public void writeLong(long l) {
		writeIdentifier(l);
		buffer.writeLong(l);
	}
	
	public void writeFloat(float f) {
		writeIdentifier(f);
		buffer.writeFloat(f);
	}
	
	public void writeDouble(double d) {
		writeIdentifier(d);
		buffer.writeDouble(d);
	}
	
	public void writeChar(char c) {
		writeIdentifier(c);
		buffer.writeChar(c);
	}
	
	public void writeString(String s) {
		writeIdentifier(s);
		buffer.writeString(s);
	}
	
	public void writeList(List<?> l) {
		writeIdentifier(l);
		
	}
	
	private void writeIdentifier(Object obj) {
		byte id = -1;
		
		if(obj instanceof Map) id = MAP_IDENTIFIER;
		else if(obj instanceof Boolean) id = BOOLEAN_IDENTIFIER;
		else if(obj instanceof Byte) id = BYTE_IDENTIFIER;
		else if(obj instanceof Short) id = SHORT_IDENTIFIER;
		else if(obj instanceof Integer) id = INT_IDENTIFIER;
		else if(obj instanceof Long) id = LONG_IDENTIFIER;
		else if(obj instanceof Float) id = FLOAT_IDENTIFIER;
		else if(obj instanceof Double) id = DOUBLE_IDENTIFIER;
		else if(obj instanceof Character) id = CHAR_IDENTIFIER;
		else if(obj instanceof String) id = STRING_IDENTIFIER;
		else if(obj instanceof List) id = LIST_IDENTIFIER;
		else if(obj instanceof Location) id = LOCATION_IDENTIFIER;
		else if(obj instanceof Direction) id = DIRECTION_IDENTIFIER;
		else if(obj instanceof Vec2D) id = VEC2D_IDENTIFIER;
		else if(obj instanceof UUID) id = UUID_IDENTIFIER;
		else if(obj instanceof Entity) id = ENTITY_IDENTIFIER;
		
		buffer.write(id);
	}
	
	private static final byte MAP_IDENTIFIER 		= 0;
	private static final byte BOOLEAN_IDENTIFIER 	= 1;
	private static final byte BYTE_IDENTIFIER 		= 2;
	private static final byte SHORT_IDENTIFIER 		= 3;
	private static final byte INT_IDENTIFIER 		= 4;
	private static final byte LONG_IDENTIFIER 		= 5;
	private static final byte FLOAT_IDENTIFIER 		= 6;
	private static final byte DOUBLE_IDENTIFIER 	= 7;
	private static final byte CHAR_IDENTIFIER 		= 8;
	private static final byte STRING_IDENTIFIER		= 9;
	private static final byte LIST_IDENTIFIER 		= 10;
	private static final byte LOCATION_IDENTIFIER 	= 11;
	private static final byte DIRECTION_IDENTIFIER 	= 12;
	private static final byte VEC2D_IDENTIFIER 		= 13;
	private static final byte UUID_IDENTIFIER 		= 14;
	private static final byte ENTITY_IDENTIFIER 	= 15;
	
}
