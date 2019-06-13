package rpg.api.eventhandling;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import rpg.RPG;
import rpg.api.entity.CharacterSheet;
import rpg.api.entity.Player;
import rpg.api.eventhandling.events.CurrentMapEvent;
import rpg.api.eventhandling.events.Event;
import rpg.api.eventhandling.events.MoveEvent;
import rpg.api.eventhandling.events.SpeakEvent;
/**
 * A BundledListener is a bundle of listeners.
 * In order to have current results, it has to be reset.
 * @author Erik Diers, Jan Unterhuber, Alexander Schallenberg
 *
 */
public class BundledListener {
	private QuestEventListener[] listener;
	
	public BundledListener(EventCondition... listener) {
		
		this.listener = new QuestEventListener[listener.length];
		int i = 0;
		for (EventCondition cond : listener) {
			this.listener[i] = new QuestEventListener(cond) {
				@Override
				public EventType getEventType() {
					return super.getEventType();
				}
			};
			rpg.api.eventhandling.EventHandler.registerEventListener(this.listener[i++]);
		}
		
	}
	
	/**
	 * Returns whether all listernes are triggered
	 * @return {@code true} if all Listeners are triggered
	 */
	public boolean isTriggered() {
		for (QuestEventListener listener : this.listener) 
			if (!listener.triggered)return false;
		
		return true;
	}
	
	/**
	 * Resets the listeners
	 */
	public void reset() {
		for (QuestEventListener eventListener : listener) 
			eventListener.reset();
	}
	
	private class QuestEventListener implements EventListener {
		private EventCondition condition;
		private boolean triggered;
		
		public QuestEventListener(EventCondition condition) {
			this.condition = condition;
		}
		
		@Override
		public void onEvent(Event event) {
			if (condition.eventTriggered(event))triggered = true;
		}
		
		public void reset() {
			triggered = false;
		}
		
		@Override
		public EventType getEventType() {
			return condition.getEventType();
		}
		
	}
	
	/**
	 * Overwrite the method apply in order to set the method condition.
	 *
	 */
	public static interface EventCondition {
		boolean eventTriggered(Event e);
		EventType getEventType();
	}
	
	public static final class SpeakTo implements EventCondition{
		
		private String unlocalizedName;
		
		public SpeakTo(String name) {
			this.unlocalizedName = name + ".name";
		}
		@Override
		public EventType getEventType() {
			return EventType.SPEAK_EVENT;
		}

		@Override
		public boolean eventTriggered(Event e) {
			return ((SpeakEvent)e).getSpeakpartner().getUnlocalizedName().equals(unlocalizedName);
		}
	}
	
	public static final class CurrentMapCondition implements EventCondition {
		public String mapName;
		
		
		public CurrentMapCondition(String mapName) {
			this.mapName = mapName;
		}

		@Override
		public boolean eventTriggered(Event e) {
			return ((CurrentMapEvent)e).getWorldname().equals(mapName);
		}

		@Override
		public EventType getEventType() {
			return EventType.CURRENT_MAP_EVENT;
		}
	}
	
	public static final class PlayerCollideWithRect implements EventCondition {
		private Rectangle2D rect;
		
		public PlayerCollideWithRect(Rectangle2D rect) {
			this.rect = rect;
		}
		
		@Override
		public boolean eventTriggered(Event e) {
			Player player = RPG.gameField.getPlayerController().getPlayer();
			Rectangle2D playerRect = new Rectangle2D.Double(player.getLocation().getX().getValueTiles(),
					player.getLocation().getY().getValueTiles(),
					CharacterSheet.PLAYER_MAGICAN_FEMALE.getHitbox().getWidth().getValueTiles(),
					CharacterSheet.PLAYER_MAGICAN_FEMALE.getHitbox().getWidth().getValueTiles());
			return playerRect.intersects(rect);
		}

		@Override
		public EventType getEventType() {
			return EventType.CURRENT_MAP_EVENT;
		}
	}
	
	public static final class PressWASD implements EventCondition {
		private boolean S_PRESSED, W_PRESSED, A_PRESSED, D_PRESSED;
		
		@Override
		public boolean eventTriggered(Event e) {
			char pressed = ((MoveEvent)e).getDirectionKey();
			switch (pressed) {
			case 'W':
				W_PRESSED = true;
				break;
			case 'A':
				A_PRESSED = true;
				break;
			case 'S':
				S_PRESSED = true;
				break;
			case 'D':
				D_PRESSED = true;
				break;
			}
			return S_PRESSED && W_PRESSED && A_PRESSED && D_PRESSED;
		}

		@Override
		public EventType getEventType() {
			return EventType.MOVE_EVENT;
		}
	}
	
}
