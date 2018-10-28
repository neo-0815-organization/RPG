package rpg.api.gamedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Vec2D;
import rpg.api.entity.Entity;
import rpg.api.packethandler.ByteBuffer;

public class GameData {
	private final File file;
	private final ByteBuffer buffer;
	private HashMap<String, Object> data;
	
	public GameData(final String path) {
		file = new File(getClass().getResource("/").getFile() + "/" + path);
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			
			try {
				file.createNewFile();
			}catch(final IOException e) {
				e.printStackTrace();
			}
		}
		
		buffer = new ByteBuffer();
		data = new HashMap<>();
	}
	
	public void set(final String key, final Object value) {
		data.put(key, value);
	}
	
	public Object get(final String key) {
		return data.get(key);
	}
	
	public boolean contains(final String key) {
		return data.containsKey(key);
	}
	
	@SuppressWarnings("unchecked")
	public void load() throws IOException {
		final FileInputStream in = new FileInputStream(file);
		final byte[] fileMagicNumber = new byte[MAGIC_NUMBER.length];
		
		in.read(fileMagicNumber);
		
		if(Arrays.equals(fileMagicNumber, MAGIC_NUMBER)) {
			buffer.clear();
			
			data = (HashMap<String, Object>) read();
		}
		
		in.close();
	}
	
	public void save() throws IOException {
		final FileOutputStream out = new FileOutputStream(file);
		
		out.write(MAGIC_NUMBER);
		
		buffer.clear();
		
		write(data);
		
		buffer.writeToOutputStream(out);
		
		out.close();
	}
	
	protected void write(final Object obj) {
		if(obj instanceof Map) {
			buffer.write(MAP_IDENTIFIER);
			
			final Map<?, ?> m = (Map<?, ?>) obj;
			buffer.writeInt(m.size());
			for(final Object key : m.keySet()) {
				write(key);
				write(m.get(key));
			}
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
			buffer.write(LIST_IDENTIFIER);
			
			final List<?> l = (List<?>) obj;
			buffer.writeInt(l.size());
			for(final Object o : l)
				write(o);
		}else if(obj instanceof Direction) {
			buffer.write(DIRECTION_IDENTIFIER);
			
			buffer.write(((Direction) obj).getId());
		}else if(obj instanceof Vec2D) {
			buffer.write(VEC2D_IDENTIFIER);
			
			final Vec2D v = (Vec2D) obj;
			buffer.writeDouble(v.getX().getValueTiles());
			buffer.writeDouble(v.getY().getValueTiles());
		}else if(obj instanceof UUID) {
			buffer.write(UUID_IDENTIFIER);
			
			buffer.writeString(((UUID) obj).toString(), false);
		}else if(obj instanceof Entity) {
			buffer.write(ENTITY_IDENTIFIER);;
			// TODO write Entity
		}
	}
	
	protected Object read() {
		switch(buffer.read()) {
			case MAP_IDENTIFIER:
				final Map<Object, Object> m = new HashMap<>();
				final int size = buffer.readInt();
				
				for(int i = 0; i < size; i++)
					m.put(read(), read());
				
				return m;
			case BOOLEAN_IDENTIFIER:
				return buffer.readBoolean();
			case BYTE_IDENTIFIER:
				return buffer.read();
			case SHORT_IDENTIFIER:
				return buffer.readShort();
			case INT_IDENTIFIER:
				return buffer.readInt();
			case LONG_IDENTIFIER:
				return buffer.readLong();
			case FLOAT_IDENTIFIER:
				return buffer.readFloat();
			case DOUBLE_IDENTIFIER:
				return buffer.readDouble();
			case CHAR_IDENTIFIER:
				return buffer.readChar();
			case STRING_IDENTIFIER:
				return buffer.readString();
			case LIST_IDENTIFIER:
				final List<Object> l = new ArrayList<>();
				final int length = buffer.readInt();
				
				for(int i = 0; i < length; i++)
					l.add(read());
				
				return l;
			case DIRECTION_IDENTIFIER:
				return Direction.getDirectionById(buffer.read());
			case VEC2D_IDENTIFIER:
				return Vec2D.createXY(buffer.readDouble(), buffer.readDouble());
			case UUID_IDENTIFIER:
				return UUID.fromString(buffer.readString(36));
			case ENTITY_IDENTIFIER:
				return null; // TODO read Entity
		}
		
		return null;
	}
	
	public Map<String, Object> getData() {
		return Collections.unmodifiableMap(data);
	}
	
	private static final byte[] MAGIC_NUMBER = "\u0033\u2663\u0000\u05D0\u03C9\u0000\u0000\u0000\n".getBytes();
	
	// @formatter:off
	private static final byte MAP_IDENTIFIER = 0,
			BOOLEAN_IDENTIFIER = 1,
			BYTE_IDENTIFIER = 2,
			SHORT_IDENTIFIER = 3,
			INT_IDENTIFIER = 4,
			LONG_IDENTIFIER = 5,
			FLOAT_IDENTIFIER = 6,
			DOUBLE_IDENTIFIER = 7,
			CHAR_IDENTIFIER = 8,
			STRING_IDENTIFIER = 9,
			LIST_IDENTIFIER = 10,
			DIRECTION_IDENTIFIER = 11,
			VEC2D_IDENTIFIER = 12,
			UUID_IDENTIFIER = 13,
			ENTITY_IDENTIFIER = 14;
}
