package rpg.api.scene;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import rpg.RPG;
import rpg.api.entity.Controller;
import rpg.api.entity.Entity;
import rpg.api.entity.PlayerController;
import rpg.api.eventhandling.EventHandler;
import rpg.api.eventhandling.EventType;
import rpg.api.eventhandling.events.Event;
import rpg.api.gfx.HUD;
import rpg.api.listener.key.KeyboardListener;
import rpg.api.quests.QuestHandler;
import rpg.api.tile.Tile;

/**
 * The class GameField represents the game world.
 *
 * @author Erik Diers, Tim Ludwig, Neo Hornberger
 */
public class GameField extends Scene {
	public static boolean inGame = true;
	public static final double MAX_DELTA_TIME = 0.21, MIN_DELTA_TIME = 0.015;
	public static Background background;
	
	private double deltaTime;
	private long lastFrame = System.currentTimeMillis();
	
	private Thread update, draw;
	
	private final LinkedList<Entity> entities = new LinkedList<>();
	private final LinkedList<Tile> tiles = new LinkedList<>();
	private final LinkedList<Controller> controller = new LinkedList<>();
	private PlayerController playerController;
	
	private final HUD hud = new HUD();
	
	public GameField() {
		background = new Background();
		
		startUpdating();
		//		startDrawing();
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		background.draw(g2d);
		
		synchronized(entities) {
			for(final Entity e : entities)
				e.draw(g2d);
		}
		
		for(final Tile t : tiles)
			t.draw(g2d);
		
		hud.draw(g2d);
	}
	
	/**
	 * Starts a new Thread, which updates the Gamefield
	 */
	private void startUpdating() {
		final GameField me = this;
		update = new Thread("GameLoop") {
			@Override
			public void run() {
				while(inGame) {
					deltaTime = (System.currentTimeMillis() - lastFrame) / 1000d;
					lastFrame = System.currentTimeMillis();
					
					if(deltaTime > MAX_DELTA_TIME) deltaTime = MAX_DELTA_TIME;
					if(deltaTime < MIN_DELTA_TIME) deltaTime = MIN_DELTA_TIME;
					
					update(deltaTime);
					
					RPG.gameFrame.drawScene(me);
					KeyboardListener.updateKeys();
					Camera.update();
				}
			}
		};
		
		update.start();
	}
	
	/**
	 * Starts the loop as a thread that draws everything.
	 */
	private void startDrawing() {
		final GameField me = this;
		draw = new Thread("Draw") {
			
			@Override
			public void run() {
				while(inGame) {
					final long systemTime = System.currentTimeMillis();
					
					RPG.gameFrame.drawScene(me);
					//	System.out.println(System.currentTimeMillis() - systemTime);
				}
			}
		};
		//		draw.start();
	}
	
	/**
	 * Updates all {@link Tile}s and {@link Entity}s.
	 */
	private void update(final double deltaTime) {
		background.update(deltaTime);
		
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).update(deltaTime);
		
		for(int i = 0; i < tiles.size(); i++)
			tiles.get(i).update(deltaTime);
		
		updateEvents();
	}
	
	public void updateEvents() {
		EventHandler.handle(new Event(EventType.CURRENT_MAP_EVENT, background.getName()));
		QuestHandler.update();
	}
	
	@Deprecated
	public List<Tile> checkCollisionTiles(final Entity e) {
		final LinkedList<Tile> ts = new LinkedList<>();
		
		for(final Tile t : tiles)
			ts.add(t);
		
		return ts;
	}
	
	public List<Entity> checkCollisionEntities(final Entity e) {
		final LinkedList<Entity> entList = new LinkedList<>();
		
		for(final Entity ent : entities)
			if(ent != e && ent.getHitbox().checkCollision(ent.getLocation(), e.getHitbox(), e.getLocation())) entList.add(ent);
		
		return entList;
	}
	
	public void removeEntity(String name) {
		if(!name.contains(".name")) name += ".name";
		
		int i = 0;
		for(final Entity e : entities) {
			if(e.getUnlocalizedName().equalsIgnoreCase(name)) {
				entities.remove(i);
				
				return;
			}
			
			i++;
		}
	}
	
	/**
	 * Shuts down the {@link GameField}'s threads.
	 */
	public void shutdown() {
		update.interrupt();
		draw.interrupt();
	}
	
	public void addEntity(final Controller c) {
		controller.add(c);
		entities.add(c.getEntity());
	}
	
	public void addTile(final Tile t) {
		tiles.add(t);
	}
	
	public PlayerController getPlayerController() {
		return playerController;
	}
	
	public void setPlayerController(final PlayerController playerController) {
		this.playerController = playerController;
		
		entities.add(playerController.getEntity());
		Camera.setFocusEntity(playerController.getEntity());
	}
}
