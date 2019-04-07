package rpg.api.entity.item;

import rpg.api.collision.Hitbox;
import rpg.api.entity.Entity;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.PathModifier;

public class ItemStack extends Entity {
	private static final int MAX_STACK_SIZE = 8;
	
	private ItemType type;
	private final boolean questItem;
	private int stackSize;
	
	public ItemStack(final String name) {
		this(name, false);
	}
	
	public ItemStack(final String name, final boolean questItem) {
		super("item." + name);
		
		this.questItem = questItem;
	}
	
	public boolean isQuestItem() {
		return questItem;
	}
	
	@Override
	public Hitbox getHitbox() {
		return null;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
	private enum ItemType implements PathModifier {
		NONE("none"),
		WEAPON("weapon"),
		ARMOR("armor");
		
		private String pathMod;
		
		private ItemType(final String pathMod) {
			this.pathMod = pathMod;
		}
		
		@Override
		public String getPathModifier() {
			return pathMod;
		}
		
		@Override
		public String getPrePath() {
			return "item.";
		}
	}
}
