package rpg.api.scene;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import rpg.api.entity.Entity;
import rpg.api.gamedata.EntityData;
import rpg.api.gamedata.GameData;
import rpg.api.tile.Tile;

public class Save {
	private static final FileFilter DIR_FILTER = file -> file.isDirectory() && file.getName().startsWith("new_save_");
	private static final HashMap<String, Object> DEFAULT_SETTINGS = new HashMap<>();
	
	static {
		DEFAULT_SETTINGS.put("background", "testWorld");
		DEFAULT_SETTINGS.put("entities", Collections.EMPTY_LIST);
	}
	
	public Background background;
	public LinkedList<Entity> entities = new LinkedList<>();
	public LinkedList<Tile> tiles = new LinkedList<>();
	
	private final String name;
	private final GameData data;
	
	public Save(String name) {
		if(name == null) {
			final int num = Arrays.stream(new File(getClass().getResource("/").getFile() + "/saves/").listFiles(DIR_FILTER)).reduce(-1, (number, file) -> {
				return Math.max(number, Integer.valueOf(file.getName().replace("new_save_", "")));
			}, (a, b) -> a);
			
			name = "new_save_" + (num + 1);
		};
		
		this.name = name;
		data = new GameData("saves/" + name + "/level.save", DEFAULT_SETTINGS);
		
		load();
	}
	
	public void load() {
		try {
			data.load();
			
			background = new Background((String) data.get("background"));
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		data.set("background", background.getName());
		data.set("entities", entities.parallelStream().map(e -> e.getUniqueId()).collect(Collectors.toList()));
		
		entities.parallelStream().forEach(e -> {
			try {
				new EntityData(e, "saves/" + name + "/entities/").save();
			}catch(final IOException ex) {
				ex.printStackTrace();
			}
		});
		
		try {
			data.save();
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
}
