package rpg.api.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import rpg.api.entity.Entity;
import rpg.api.gamedata.GameData;
import rpg.api.tile.Tile;

public class Save {
	public Background background;
	public LinkedList<Entity> entities = new LinkedList<>();
	public LinkedList<Tile> tiles = new LinkedList<>();
		
	private String name;
	private GameData data;
	
	public Save(String name) {
		this.name = name;
		
		data = new GameData(name + "/level.save");
	}
	
	public void load() {
		try {
			data.load();
			
			background = new Background((String) data.get("background"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		data.set("background", background.getName());
		data.set("entities", entities.parallelStream().map(e -> e.getUniqueId()).collect(Collectors.toList()));
		
		try {
			data.save();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
