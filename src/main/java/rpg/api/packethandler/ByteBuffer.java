package rpg.api.packethandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * The class ByteBuffer stores bytes that will be sent through an
 * {@link OutputStream} and reads bytes from an {@link InputStream}. All types
 * that uses more than one byte are stored in the big-endian format.
 *
 * @author Neo Hornberger
 */
public class ByteBuffer {
	private final ArrayList<Byte> bytes = new ArrayList<>();
	private int limit = -1, writeCursor = 0, readCursor = 0;
	
	/**
	 * Constructs a new empty {@link ByteBuffer}.
	 */
	public ByteBuffer() {}
	
	/**
	 * Constructs a new {@link ByteBuffer} containing all bytes of the byte[]
	 * 'bytes'.
	 *
	 * @param bytes
	 *            the bytes to write
	 * @see #write(byte[])
	 */
	public ByteBuffer(final byte[] bytes) {
		write(bytes);
	}
	
	/**
	 * Writes the byte 'b' to the buffer.
	 *
	 * @param b
	 *            the byte to write
	 */
	public void write(final byte b) {
		if(limit > -1 && writeCursor >= limit) throw new IllegalStateException("Reached limit of " + limit + " elements");
		
		bytes.add(b);
		
		writeCursor++;
	}
	
	/**
	 * Writes the bytes contained in the byte[] 'bytes' to the buffer.
	 *
	 * @param bytes
	 *            the bytes to write
	 * @see #write(byte)
	 */
	public void write(final byte[] bytes) {
		for(final byte b : bytes)
			write(b);
	}
	
	/**
	 * Writes the int 'i' to the buffer.
	 *
	 * @param i
	 *            the int to write
	 * @see #write(byte)
	 */
	public void writeInt(final int i) {
		write((byte) (i >>> 24));
		write((byte) (i >>> 16));
		write((byte) (i >>> 8));
		write((byte) i);
	}
	
	/**
	 * Writes the char 'c' to the buffer.
	 *
	 * @param c
	 *            the char to write
	 * @see #writeShort(short)
	 */
	public void writeChar(final char c) {
		writeShort((short) c);
	}
	
	/**
	 * Writes the short 's' to the buffer.
	 *
	 * @param s
	 *            the short to write
	 * @see #write(byte)
	 */
	public void writeShort(final short s) {
		write((byte) (s >>> 8));
		write((byte) s);
	}
	
	/**
	 * Writes the long 'l' to the buffer.
	 *
	 * @param l
	 *            the long to write
	 * @see #write(byte)
	 */
	public void writeLong(final long l) {
		write((byte) (l >>> 56));
		write((byte) (l >>> 48));
		write((byte) (l >>> 40));
		write((byte) (l >>> 32));
		write((byte) (l >>> 24));
		write((byte) (l >>> 16));
		write((byte) (l >>> 8));
		write((byte) l);
	}
	
	/**
	 * Writes the {@link String} 's' to the buffer.
	 *
	 * @param s
	 *            the {@link String} to write
	 * @see #writeString(String, boolean)
	 */
	public void writeString(final String s) {
		writeString(s, true);
	}
	
	/**
	 * Writes the {@link String} 's' and if 'writeLength' is {@code true} its
	 * length to the buffer.
	 *
	 * @param s
	 *            the {@link String} to write
	 * @param writeLength
	 *            whether the length of the {@link String} 's' should be written
	 *            in front of it
	 * @see #writeInt(int)
	 * @see #write(byte)
	 */
	public void writeString(final String s, final boolean writeLength) {
		if(writeLength) writeInt(s.length());
		
		for(final byte b : s.getBytes())
			write(b);
	}
	
	/**
	 * Writes the double 'd' to the buffer.
	 *
	 * @param d
	 *            the double to write
	 * @see #writeLong(long)
	 */
	public void writeDouble(final double d) {
		writeLong(Double.doubleToRawLongBits(d));
	}
	
	/**
	 * Writes the float 'f' to the buffer.
	 *
	 * @param f
	 *            the float to write
	 * @see #writeInt(int)
	 */
	public void writeFloat(final float f) {
		writeInt(Float.floatToRawIntBits(f));
	}
	
	/**
	 * Writes the boolean 'b' to the buffer as one byte.
	 * ({@code 00000001 = true} or {@code 00000000 = false})
	 *
	 * @param b
	 *            the boolean to write
	 * @see #write(byte)
	 */
	public void writeBoolean(final boolean b) {
		write((byte) (b ? 1 : 0));
	}
	
	/**
	 * Reads one byte from the buffer and interprets it as a byte.
	 *
	 * @return the byte read
	 */
	public byte read() {
		final byte b = bytes.get(readCursor);
		
		readCursor++;
		
		return b;
	}
	
	/**
	 * Reads four bytes from the buffer and interprets them as an int.
	 *
	 * @return the int read
	 * @see #read()
	 */
	public int readInt() {
		return (read() & 0xFF) << 24 | (read() & 0xFF) << 16 | (read() & 0xFF) << 8 | read() & 0xFF;
	}
	
	/**
	 * Reads one int from the buffer and interprets it as a char.
	 *
	 * @return the char read
	 * @see #readShort()
	 */
	public char readChar() {
		return (char) readShort();
	}
	
	/**
	 * Reads two bytes from the buffer and interprets them as a short.
	 *
	 * @return the short read
	 * @see #read()
	 */
	public short readShort() {
		return (short) ((read() & 0xFF) << 8 | read() & 0xFF);
	}
	
	/**
	 * Reads eight bytes from the buffer and interprets them as a long.
	 *
	 * @return the long read
	 * @see #read()
	 */
	public long readLong() {
		return ((long) read() & 0xFF) << 56 | ((long) read() & 0xFF) << 48 | ((long) read() & 0xFF) << 40 | ((long) read() & 0xFF) << 32 | ((long) read() & 0xFF) << 24 | ((long) read() & 0xFF) << 16 | ((long) read() & 0xFF) << 8 | (long) read() & 0xFF;
	}
	
	/**
	 * Reads one int {@code n} and {@code n} chars from the buffer and
	 * interprets the chars read as a {@link String}.
	 *
	 * @return the {@link String} read
	 * @see #readInt()
	 * @see #readString(int)
	 */
	public String readString() {
		return readString(readInt());
	}
	
	/**
	 * Reads 'length' chars from the buffer and interprets them as a String.
	 *
	 * @return the {@link String} read
	 * @see #read()
	 */
	public String readString(final int length) {
		byte[] bytes = new byte[length];
		
		for(int i = 0; i < length; i++)
			bytes[i] = read();
		
		return new String(bytes);
	}
	
	/**
	 * Reads one long from the buffer and interprets it as a double.
	 *
	 * @return the double read
	 * @see #readLong()
	 */
	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}
	
	/**
	 * Reads one int from the buffer and interprets it as a float.
	 *
	 * @return the float read
	 * @see #readInt()
	 */
	public float readFloat() {
		return Float.intBitsToFloat(readInt());
	}
	
	/**
	 * Reads one byte from the buffer and interprets it as a boolean.
	 *
	 * @return the boolean read
	 * @see #read()
	 */
	public boolean readBoolean() {
		return read() == 1;
	}
	
	/**
	 * Clears this {@link ByteBuffer} and sets both cursors to zero.
	 */
	public void clear() {
		bytes.clear();
		
		writeCursor = 0;
		readCursor = 0;
	}
	
	/**
	 * Returns all non-written bytes of this {@link ByteBuffer} in form of a
	 * byte[].
	 *
	 * @return the non-written bytes
	 */
	public byte[] toByteArray() {
		final byte[] byteArray = new byte[bytes.size() - readCursor];
		
		for(int i = readCursor; i < bytes.size(); i++)
			byteArray[i - readCursor] = bytes.get(i);
		
		return byteArray;
	}
	
	/**
	 * Writes all non-written bytes of this {@link ByteBuffer} to the
	 * {@link OutputStream} 'out'.
	 *
	 * @param out
	 *            - the {@link OutputStream} to which it will write
	 * @return the current {@link ByteBuffer} instance
	 * @throws IOException
	 *             if an I/O error occures
	 */
	public ByteBuffer writeToOutputStream(final OutputStream out) throws IOException {
		out.write(toByteArray());
		out.flush();
		
		return this;
	}
	
	/**
	 * Reads all bytes from the {@link InputStream} 'in' and store it in the
	 * current instance.
	 *
	 * @param in
	 *            - the {@link InputStream} from which it will read
	 * @return the current {@link ByteBuffer} instance
	 * @throws IOException
	 *             if an I/O error occurres.
	 */
	public ByteBuffer readFromInputStream(final InputStream in) throws IOException {
		while(in.available() > 0)
			write((byte) in.read());
		
		return this;
	}
	
	/**
	 * Gets the limit of this {@link ByteBuffer}.
	 *
	 * @return the limit of this {@link ByteBuffer}
	 */
	public int getLimit() {
		return limit;
	}
	
	/**
	 * Sets the limit of this {@link ByteBuffer}.
	 *
	 * @param limit
	 *            the new limit of this {@link ByteBuffer}
	 * @return the current {@link ByteBuffer} instance
	 */
	public ByteBuffer setLimit(final int limit) {
		if(bytes.size() <= limit) this.limit = limit;
		
		return this;
	}
	
	/**
	 * Gets the position of the write cursor.
	 *
	 * @return the write cursor position
	 */
	public int getWriteCursor() {
		return writeCursor;
	}
	
	/**
	 * Gets the position of the read cursor.
	 *
	 * @return the read cursor position
	 */
	public int getReadCursor() {
		return readCursor;
	}
}
