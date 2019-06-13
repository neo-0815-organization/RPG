package rpg.api.scene;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import rpg.Logger;
import rpg.api.collision.Hitbox;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.IImage;
import rpg.api.packethandler.ByteBuffer;
import rpg.api.tile.Fluid;
import rpg.api.tile.Tile;
import rpg.api.tile.fluids.FluidWater;
import rpg.api.tile.tiles.TileAnvil;
import rpg.api.tile.tiles.TileBarrel;
import rpg.api.tile.tiles.TileBarrier;
import rpg.api.tile.tiles.TileBottle;
import rpg.api.tile.tiles.TileBottle.BottleType;
import rpg.api.tile.tiles.TileBuilding;
import rpg.api.tile.tiles.TileBuilding.BuildingType;
import rpg.api.tile.tiles.TileBush;
import rpg.api.tile.tiles.TileCandle;
import rpg.api.tile.tiles.TileCandle.CandleType;
import rpg.api.tile.tiles.TileCanvas;
import rpg.api.tile.tiles.TileCastle;
import rpg.api.tile.tiles.TileCauldron;
import rpg.api.tile.tiles.TileChair;
import rpg.api.tile.tiles.TileChair.ChairType;
import rpg.api.tile.tiles.TileChalice;
import rpg.api.tile.tiles.TileChest;
import rpg.api.tile.tiles.TileClock;
import rpg.api.tile.tiles.TileComputer;
import rpg.api.tile.tiles.TileCrystal;
import rpg.api.tile.tiles.TileDoor;
import rpg.api.tile.tiles.TileDoor.DoorType;
import rpg.api.tile.tiles.TileDungeonEntrance;
import rpg.api.tile.tiles.TileFlag;
import rpg.api.tile.tiles.TileFlag.FlagType;
import rpg.api.tile.tiles.TileFlower;
import rpg.api.tile.tiles.TileFlower.FlowerType;
import rpg.api.tile.tiles.TileFurnace;
import rpg.api.tile.tiles.TileHeartCrystal;
import rpg.api.tile.tiles.TileLampStanding;
import rpg.api.tile.tiles.TileLeaf;
import rpg.api.tile.tiles.TileLeaf.LeafType;
import rpg.api.tile.tiles.TileLog;
import rpg.api.tile.tiles.TileLog.LogType;
import rpg.api.tile.tiles.TileMineEntrance;
import rpg.api.tile.tiles.TileMinecart;
import rpg.api.tile.tiles.TileMinecart.MinecartType;
import rpg.api.tile.tiles.TileMushroom;
import rpg.api.tile.tiles.TileMushroom.MushroomType;
import rpg.api.tile.tiles.TileOre;
import rpg.api.tile.tiles.TileOre.OreType;
import rpg.api.tile.tiles.TileOven;
import rpg.api.tile.tiles.TilePlaneWreck;
import rpg.api.tile.tiles.TilePlant;
import rpg.api.tile.tiles.TilePlant.PlantType;
import rpg.api.tile.tiles.TilePortalToSeyhan;
import rpg.api.tile.tiles.TileSeagrass;
import rpg.api.tile.tiles.TileSeagrass.SeagrassType;
import rpg.api.tile.tiles.TileShell;
import rpg.api.tile.tiles.TileShell.ShellType;
import rpg.api.tile.tiles.TileSmeltery;
import rpg.api.tile.tiles.TileSofa;
import rpg.api.tile.tiles.TileTable;
import rpg.api.tile.tiles.TileTable.TableType;
import rpg.api.tile.tiles.TileTent;
import rpg.api.tile.tiles.TileTent.TentType;
import rpg.api.tile.tiles.TileTrain;
import rpg.api.tile.tiles.TileTree;
import rpg.api.tile.tiles.TileTree.TreeType;
import rpg.api.tile.tiles.TileTreestump;
import rpg.api.tile.tiles.TileTreestump.TreestumpType;
import rpg.api.tile.tiles.TileWagon;
import rpg.api.tile.tiles.TileWagon.WagonType;
import rpg.api.tile.tiles.TileWorkbench;
import rpg.api.units.DistanceValue;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * Background class contains the background image and all {@link Tile}s.
 *
 * @author Erik Diers, Jan Unterhuber, Neo Hornberger
 */
public class Background implements IImage {
	private BufferedImage background;
	
	private final ArrayList<Fluid> fluids;
	private final ArrayList<Tile> tiles;
	private final String name;
	
	public Background() {
		this(null);
	}
	
	public Background(final String name) {
		this.name = name;
		
		fluids = new ArrayList<>();
		tiles = new ArrayList<>();
		
		if(name != null) try {
			loadFromFile();
		}catch(final IOException e) {
			Logger.error(e);
		}
	}
	
	@Override
	public void draw(final DrawingGraphics g) {
		if(name == null) return;
		
		draw(g, Vec2D.ORIGIN);
	}
	
	private void loadFromFile() throws IOException {
		final ZipInputStream zis = new ZipInputStream(ResourceGetter.getResource("/assets/worlds/" + name + ".world"));
		final HashMap<String, ByteArrayInputStream> streams = new HashMap<>();
		
		ZipEntry entry;
		ByteArrayOutputStream baos;
		while((entry = zis.getNextEntry()) != null) {
			baos = new ByteArrayOutputStream();
			
			while(zis.available() > 0)
				baos.write(zis.read());
			
			streams.put(entry.getName(), new ByteArrayInputStream(baos.toByteArray(), 0, baos.size() - 1));
		}
		
		background = ImageIO.read(streams.get("background"));
		
		final ByteBuffer buf = new ByteBuffer();
		
		// read mapping
		buf.readFromInputStream(streams.get("mapping"));
		final HashMap<Integer, HashMap<Integer, String>> neededTiles = new HashMap<>();
		
		final int mapCount = buf.readInt();
		int layerID, length, tileID;
		HashMap<Integer, String> map;
		String name;
		for(int i = 0; i < mapCount; i++) {
			layerID = buf.readInt();
			length = buf.readInt();
			
			map = new HashMap<>(length);
			
			for(int j = 0; j < length; j++) {
				tileID = buf.readInt();
				name = buf.readString();
				
				map.put(tileID, name);
			}
			
			neededTiles.put(layerID, map);
		}
		
		// read & create fluids
		buf.clear();
		buf.readFromInputStream(streams.get("fluids"));
		
		final int fluidCount = buf.readInt();
		Fluid fluid;
		for(int i = 0; i < fluidCount; i++) {
			final UnmodifiableVec2D location = UnmodifiableVec2D.createXY(buf.readInt(), buf.readInt());
			final int id = buf.readInt();
			
			fluid = TileBuilder.getFluid(neededTiles.get(0).get(id));
			fluid.setLocation(location);
			
			fluids.add(fluid);
		}
		
		// read & create tiles
		buf.clear();
		buf.readFromInputStream(streams.get("tiles"));
		
		final int tileCount = buf.readInt();
		Tile tile;
		for(int i = 0; i < tileCount; i++) {
			final UnmodifiableVec2D location = UnmodifiableVec2D.createXY(buf.readInt(), buf.readInt());
			final int id = buf.readInt();
			
			tile = TileBuilder.getTile(neededTiles.get(2).get(id));
			tile.setLocation(location);
			
			tiles.add(tile);
		}
		
		// read & create hitboxes
		buf.clear();
		buf.readFromInputStream(streams.get("hitboxes"));
		
		final int hitboxCount = buf.readInt();
		UnmodifiableVec2D location;
		int hitboxesOnLocation;
		TileBarrier t;
		for(int i = 0; i < hitboxCount; i++) {
			location = UnmodifiableVec2D.createXY(buf.readInt(), buf.readInt());
			hitboxesOnLocation = buf.readInt();
			
			for(int j = 0; j < hitboxesOnLocation; j++) {
				buf.readInt(); // INFO skip 'tileLayer' for backwards compatibility
				
				t = new TileBarrier(new Hitbox(new DistanceValue(buf.readDouble()), new DistanceValue(buf.readDouble())));
				t.setLocation(location);
				
				tiles.add(t);
			}
		}
		
		zis.close();
		streams.values().parallelStream().forEach(bais -> {
			try {
				bais.close();
			}catch(final IOException e) {
				Logger.error(e);
			}
		});
	}
	
	@Override
	public BufferedImage getImage() {
		return background;
	}
	
	public ArrayList<Fluid> getFluids() {
		return fluids;
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public String getName() {
		return name;
	}
	
	private static class TileBuilder {
		
		public static Fluid getFluid(final String name) {
			switch(name) {
				case "water_right":
					return new FluidWater();
			}
			
			throw new IllegalArgumentException("Fluid with name '" + name + "' isn't integrated yet.");
		}
		
		public static Tile getTile(final String name) {
			switch(name) {
				case "anvil":
					return new TileAnvil();
				case "barrack_dwarf":
					return new TileBuilding(BuildingType.DWARF_BARRACKS);
				case "barrel":
					return new TileBarrel();
				case "bush":
					return new TileBush();
				case "canvas_sunflower":
					return new TileCanvas();
				case "castle":
					return new TileCastle();
				case "cauldron":
					return new TileCauldron();
				case "chalice":
					return new TileChalice();
				case "chest":
					return new TileChest();
				case "clock":
					return new TileClock();
				case "computer":
					return new TileComputer();
				case "crystal":
					return new TileCrystal();
				case "door_tattice":
					return new TileDoor(DoorType.TATTICE);
				case "door":
					return new TileDoor(DoorType.NORMAL);
				case "dungeon_entrance":
					return new TileDungeonEntrance();
				case "furnace":
					return new TileFurnace();
				case "heart_crystal":
					return new TileHeartCrystal();
				case "house":
					return new TileBuilding(BuildingType.NORMAL_HOUSE);
				case "lamp_standing":
					return new TileLampStanding();
				case "mine_entrance":
					return new TileMineEntrance();
				case "oven":
					return new TileOven();
				case "plane_wreck":
					return new TilePlaneWreck();
				case "plant_soybeans":
					return new TilePlant(PlantType.SOYBEAN);
				case "plant":
					return new TilePlant(PlantType.NORMAL);
				case "portal_to_seyhan":
					return new TilePortalToSeyhan();
				case "smeltery":
					return new TileSmeltery();
				case "sofa":
					return new TileSofa();
				case "tent":
					return new TileTent(TentType.NORMAL);
				case "train":
					return new TileTrain();
				case "tree":
					return new TileTree(TreeType.NORMAL);
				case "workbench":
					return new TileWorkbench();
			}
			
			if(name.startsWith("bottle_")) switch(name.substring(7)) {
				case "beach":
					return new TileBottle(BottleType.BEACH);
				case "beach2":
					return new TileBottle(BottleType.BEACH2);
				case "water_holy":
					return new TileBottle(BottleType.HOLY_WATER);
				case "message":
					return new TileBottle(BottleType.MESSAGE);
				case "river":
					return new TileBottle(BottleType.RIVER);
			}
			else if(name.startsWith("candle_")) switch(name.substring(7)) {
				case "bronze":
					return new TileCandle(CandleType.BRONZE);
				case "gold":
					return new TileCandle(CandleType.GOLD);
				case "silver":
					return new TileCandle(CandleType.SILVER);
			}
			else if(name.startsWith("chair/")) switch(name.substring(6)) {
				case "front":
					return new TileChair(ChairType.FRONT);
				case "back":
					return new TileChair(ChairType.BACK);
				case "left":
					return new TileChair(ChairType.LEFT);
				case "right":
					return new TileChair(ChairType.RIGHT);
			}
			else if(name.startsWith("flag_")) switch(name.substring(5)) {
				case "blue":
					return new TileFlag(FlagType.BLUE);
				case "red":
					return new TileFlag(FlagType.RED);
				case "yellow":
					return new TileFlag(FlagType.YELLOW);
			}
			else if(name.startsWith("flower_")) switch(name.substring(7)) {
				case "cyan":
					return new TileFlower(FlowerType.CYAN);
				case "pink":
					return new TileFlower(FlowerType.PINK);
				case "purple_left":
					return new TileFlower(FlowerType.PURPLE_LEFT);
				case "purple_right":
					return new TileFlower(FlowerType.PURPLE_RIGHT);
				case "red_left":
					return new TileFlower(FlowerType.RED_LEFT);
				case "red_multiple":
					return new TileFlower(FlowerType.RED_MULTIPLE);
				case "red_right":
					return new TileFlower(FlowerType.RED_RIGHT);
				case "yellow_left_low":
					return new TileFlower(FlowerType.YELLOW_LEFT_LOW);
				case "yellow_left":
					return new TileFlower(FlowerType.YELLOW_LEFT);
				case "yellow_multiple":
					return new TileFlower(FlowerType.YELLOW_MULTIPLE);
				case "yellow_right_low":
					return new TileFlower(FlowerType.YELLOW_RIGHT_LOW);
				case "yellow_right":
					return new TileFlower(FlowerType.YELLOW_RIGHT);
			}
			else if(name.startsWith("house_")) switch(name.substring(6)) {
				case "dwarf_2":
					return new TileBuilding(BuildingType.DWARF_HOUSE_2);
				case "dwarf":
					return new TileBuilding(BuildingType.DWARF_HOUSE);
				case "fairy":
					return new TileBuilding(BuildingType.FAIRY_HOUSE);
			}
			else if(name.startsWith("leaf_")) switch(name.substring(5)) {
				case "green":
					return new TileLeaf(LeafType.GREEN);
				case "orange":
					return new TileLeaf(LeafType.ORANGE);
			}
			else if(name.startsWith("log")) switch(name.substring(3)) {
				case "1":
					return new TileLog(LogType.LEFT_SIDE);
				case "2":
					return new TileLog(LogType.NORMAL);
				case "3":
					return new TileLog(LogType.NORMAL_2);
			}
			else if(name.startsWith("minecart_")) switch(name.substring(9)) {
				case "empty":
					return new TileMinecart(MinecartType.EMPTY);
				case "gold_ore":
					return new TileMinecart(MinecartType.GOLD_ORE);
			}
			else if(name.startsWith("mushroom_")) switch(name.substring(9)) {
				case "blue":
					return new TileMushroom(MushroomType.BLUE);
				case "brown":
					return new TileMushroom(MushroomType.BROWN);
				case "green":
					return new TileMushroom(MushroomType.GREEN);
				case "red_detailed":
					return new TileMushroom(MushroomType.RED_DETAIL);
				case "red":
					return new TileMushroom(MushroomType.RED);
			}
			else if(name.startsWith("ore_")) switch(name.substring(4)) {
				case "blue":
					return new TileOre(OreType.BLUE);
				case "crystal_blue":
					return new TileOre(OreType.CRYSTAL_BLUE);
				case "crystal_red":
					return new TileOre(OreType.CRYSTAL_RED);
				case "empty":
					return new TileOre(OreType.EMPTY);
				case "gold":
					return new TileOre(OreType.GOLD);
				case "green":
					return new TileOre(OreType.GREEN);
				case "grey":
					return new TileOre(OreType.GREY);
				case "red":
					return new TileOre(OreType.RED);
				case "silver":
					return new TileOre(OreType.SILVER);
			}
			else if(name.startsWith("seagrass")) switch(name.substring(8)) {
				case "":
					return new TileSeagrass(SeagrassType.NORMAL);
				case "2":
					return new TileSeagrass(SeagrassType.TYPE_2);
			}
			else if(name.startsWith("shell")) switch(name.substring(5)) {
				case "_spike":
					return new TileShell(ShellType.SPIKE);
				case "":
					return new TileShell(ShellType.TYPE_1);
				case "2":
					return new TileShell(ShellType.TYPE_2);
			}
			else if(name.startsWith("table")) switch(name.substring(5)) {
				case "_light":
					return new TileTable(TableType.LIGHT);
				case "":
					return new TileTable(TableType.NORMAL);
			}
			else if(name.startsWith("tent_")) switch(name.substring(5)) {
				case "red":
					return new TileTent(TentType.RED);
				case "yellow":
					return new TileTent(TentType.YELLOW);
			}
			else if(name.startsWith("tree_")) switch(name.substring(5)) {
				case "apple":
					return new TileTree(TreeType.APPLE);
				case "apple2":
					return new TileTree(TreeType.APPLE_2);
				case "big_face":
					return new TileTree(TreeType.BIG_FACE);
				case "big":
					return new TileTree(TreeType.BIG);
				case "dead_2":
					return new TileTree(TreeType.DEAD_2);
				case "dead":
					return new TileTree(TreeType.DEAD);
				case "fire_snow":
					return new TileTree(TreeType.FIRE_SNOW);
				case "fire":
					return new TileTree(TreeType.FIRE);
				case "palm":
					return new TileTree(TreeType.PALM);
			}
			else if(name.startsWith("treestump")) switch(name.substring(9)) {
				case "_snowed":
					return new TileTreestump(TreestumpType.SNOWED);
				case "":
					return new TileTreestump(TreestumpType.NORMAL);
			}
			else if(name.startsWith("wagon")) switch(name.substring(5)) {
				case "_damaged":
					return new TileWagon(WagonType.DAMAGED);
				case "":
					return new TileWagon(WagonType.NORMAL);
			}
			
			throw new IllegalArgumentException("Tile with name '" + name + "' isn't integrated yet.");
		}
	}
}
