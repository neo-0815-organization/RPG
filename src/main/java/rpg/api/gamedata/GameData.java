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
import rpg.api.collision.Hitbox;
import rpg.api.packethandler.ByteBuffer;
import rpg.api.vector.Vec2D;

/**
 * The class GameData, used to save and load data about the game into/from a
 * {@link File}.
 *
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer
 */
public class GameData {
	private final File file;
	private final ExtendedByteBuffer buffer;
	private HashMap<String, Object> data;
	
	/**
	 * Constructs a new representation of data.
	 * 
	 * @param path
	 *            the path to the {@link File}
	 */
	public GameData(final String path) {
		this(path, new HashMap<>());
	}
	
	/**
	 * Constructs a new representation of data with given defaults.
	 * 
	 * @param path
	 *            the path to the {@link File}
	 * @param data
	 *            the default data {@link HashMap}
	 * 
	 * @see #save()
	 */
	public GameData(final String path, final HashMap<String, Object> data) {
		file = new File(getClass().getResource("/").getFile() + "/" + path);
		
		this.data = data;
		buffer = new ExtendedByteBuffer();
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			
			try {
				file.createNewFile();
				
				if(!data.isEmpty()) save();
			}catch(final IOException e) {
				e.printStackTrace();
			}
		}else this.data.clear();
	}
	
	/**
	 * Sets the entry corresponding to the key.
	 *
	 * @param key
	 *            the key corresponding to the value to change
	 * @param value
	 *            the value to set
	 */
	public void set(final String key, final Object value) {
		data.put(key, value);
	}
	
	/**
	 * Gets the corresponding value of this key.
	 *
	 * @param key
	 *            the key to query the value from
	 * @return the value corresponding to this key
	 */
	public Object get(final String key) {
		return data.get(key);
	}
	
	/**
	 * Checks if this key has a corresponding value.
	 *
	 * @return {@code true} if this key corresponds to a valuehas a
	 *         corresponding value
	 */
	public boolean contains(final String key) {
		return data.containsKey(key);
	}
	
	/**
	 * Loads the data from the {@link File} corresponding to this
	 * {@link GameData}.
	 *
	 * @throws IOException
	 *             if I/O error occures
	 */
	@SuppressWarnings("unchecked")
	public void load() throws IOException {
		final FileInputStream in = new FileInputStream(file);
		final byte[] fileMagicNumber = new byte[MAGIC_NUMBER.length];
		
		in.read(fileMagicNumber);
		
		if(Arrays.equals(fileMagicNumber, MAGIC_NUMBER)) {
			buffer.clear();
			buffer.readFromInputStream(in);
			
			data = (HashMap<String, Object>) read();
		}
		
		in.close();
	}
	
	/**
	 * Saves the data to the {@link File} corresponding to this
	 * {@link GameData}.
	 *
	 * @throws IOException
	 *             if I/O error occures
	 */
	public void save() throws IOException {
		final FileOutputStream out = new FileOutputStream(file);
		
		out.write(MAGIC_NUMBER);
		
		buffer.clear();
		
		write(data);
		
		buffer.writeToOutputStream(out);
		
		out.close();
	}
	
	/**
	 * Writes the {@link Object} obj to the {@link ByteBuffer}.
	 *
	 * @param obj
	 *            the {@link Object} to write
	 */
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
			
			buffer.writeDirection((Direction) obj);
		}else if(obj instanceof Vec2D) {
			buffer.write(VEC2D_IDENTIFIER);
			
			buffer.writeVec2D((Vec2D<?>) obj);
		}else if(obj instanceof UUID) {
			buffer.write(UUID_IDENTIFIER);
			
			buffer.writeUUID((UUID) obj);
		}else if(obj instanceof Hitbox) {
			buffer.write(HITBOX_IDENTIFIER);
			
			buffer.writeHitbox((Hitbox) obj);
		}
	}
	
	/**
	 * Reads the next value stored from the {@link ByteBuffer}
	 *
	 * @return the {@link Object} read
	 */
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
				return buffer.readDirection();
			case VEC2D_IDENTIFIER:
				return buffer.readVec2D();
			case UUID_IDENTIFIER:
				return buffer.readUUID();
			case HITBOX_IDENTIFIER:
				return buffer.readHitbox();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder("{\n");
		
		data.entrySet().stream().map(entry -> "  " + entry.getKey() + "=" + entry.getValue() + ",\n").forEach(builder::append);
		
		builder.replace(builder.length() - 2, builder.length() - 1, "");
		
		return builder.append("}").toString();
	}
	
	/**
	 * Gets the {@link Map} of keys and values currently stored in this
	 * {@link GameData}.
	 *
	 * @return the data {@link Map}
	 */
	public Map<String, Object> getData() {
		return Collections.unmodifiableMap(data);
	}
	
	private static final byte[] MAGIC_NUMBER = "\u0033\u2663\u0000\u05D0\u03C9\u0000\u0000\u0000\n".getBytes();
	
	// @formatter:off
	private static final byte	MAP_IDENTIFIER = 0,
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
								HITBOX_IDENTIFIER = 14;
	// @formatter:on
}
