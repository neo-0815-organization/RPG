package rpg.worldcreator;

import java.awt.Point;

public class Data {
	// @formatter:off
	public static final String version = "1.05";
	public static final int tileSize = 32;
	public static final Point[] fillOffsets = new Point[] {
		new Point(-1, -1),	new Point(0, -1),	new Point(1, -1),
		new Point(-1, 0),						new Point(1, 0),
		new Point(-1, 1),	new Point(0, 1),	new Point(1, 1)
	};
	// @formatter:on
}
