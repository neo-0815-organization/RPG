package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.dialog.Dialog;
import rpg.api.eventhandling.EventHandler;
import rpg.api.eventhandling.EventType;
import rpg.api.eventhandling.events.SpeakEvent;
import rpg.api.gfx.menus.MenuDialog;
import rpg.api.vector.ModifiableVec2D;

public class Person extends LivingEntity {
	private final String dialogName;
	public static boolean I_PRESSED;
	
	public Person(final String name, final CharacterSheet character, final int maxHP, final String dialogName, final ModifiableVec2D pos) {
		super(name, maxHP);
		this.dialogName = dialogName;
		setLocation(pos);
		
		hitbox = character.getHitbox();
		setSprite(character.getSprite());
	}
	
	@Override
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		if(I_PRESSED) {
			new MenuDialog(new Dialog(dialogName), this).show();
			
			EventHandler.handle(new SpeakEvent(this));
			
			I_PRESSED = false;
		}
	}
}
