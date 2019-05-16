package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.dialog.Dialog;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.gfx.menus.DialogMenu;
import rpg.api.vector.ModifiableVec2D;

public class Person extends LivingEntity {
	private String dialogName;
	public static boolean E_PRESSED;

	public Person(String name, CharacterSheet character, int maxHP, String dialogName, ModifiableVec2D pos) {
		super(name, maxHP);
		this.dialogName = dialogName;
		this.setLocation(pos);
		 
		this.hitbox = character.getHitbox();
		setSprite(character.getSprite());
	}

	@Override
	public Hitbox getHitbox() {
		return hitbox;
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		if (E_PRESSED) {
			new DialogMenu(new Dialog(dialogName), this).show();
			E_PRESSED = false;
		}
	}
	
	private static final Sprite PLAYERSPRITE = new Sprite("player/thief/male/normal", SpriteTheme.NONE);
	static {
		PLAYERSPRITE.addAnimation("walking/right");
		PLAYERSPRITE.addAnimation("walking/up");
		PLAYERSPRITE.addAnimation("walking/down");
		PLAYERSPRITE.addAnimation("walking/left");
		
		PLAYERSPRITE.setAnimation("walking/down");
	}
	
	public static enum CharacterSheet {
		PLAYER {
			@Override
			public Hitbox getHitbox() {
				return new Hitbox(1D, 1D);
			}

			@Override
			public Sprite getSprite() {
				return PLAYERSPRITE.clone();
			}
		};
		
		
		public abstract Hitbox getHitbox();
		public abstract Sprite getSprite();
	}

}
