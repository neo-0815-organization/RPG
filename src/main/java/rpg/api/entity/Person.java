package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.dialog.Dialog;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.menus.DialogMenu;
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
			new DialogMenu(new Dialog(dialogName), this).show();
			I_PRESSED = false;
		}
	}
}
