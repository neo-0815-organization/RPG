package rpg.api.entity;

import rpg.api.collision.Hitbox;
import rpg.api.gfx.Gender;
import rpg.api.gfx.PathModifier;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.Sprite.WalkableSprite;
import rpg.api.gfx.SpriteTheme;

public enum CharacterSheet implements PathModifier {
	PLAYER_THIEF_MALE(CharacterType.THIEF, Gender.MALE),
	PLAYER_THIEF_FEMALE(CharacterType.THIEF, Gender.FEMALE),
	PLAYER_NATUREGUARDIAN_MALE(CharacterType.NATURE, Gender.MALE),
	PLAYER_NATUREGUARDIAN_FEMALE(CharacterType.NATURE, Gender.FEMALE),
	PLAYER_MAGICAN_MALE(CharacterType.MAGE, Gender.MALE),
	PLAYER_MAGICAN_FEMALE(CharacterType.MAGE, Gender.FEMALE),
	
	DRAFT_GUARFIAN(CharacterType.DWARFT_GUARIAN_TYPE, Gender.MALE);

	protected CharacterType	type;
	protected Gender		gender;

	private CharacterSheet(final CharacterType type, final Gender gender) {
		this.type = type;
		this.gender = gender;
	}

	@Override
	public String getPrePath() {
		return "player/";
	}

	@Override
	public String getPathModifier() {
		return type.getName();
	}

	@Override
	public String getPostPath() {
		return gender.getPath() + "/normal";
	}

	public Hitbox getHitbox() {
		return new Hitbox(1d, 1.5);
	}

	public Sprite getSprite() {
		if (type.hasWalkanimation()) {
			return new WalkableSprite(getPath());
		} 
		Sprite s = Sprite.createSpriteErik();
		s.addAndSetAnimationSingleImageErik(type.getName());
		return s;
	}

	public static CharacterSheet getCharacterSheet(final CharacterType type, final Gender gender) {
		for(final CharacterSheet sheet : values())
			if(sheet.type == type && sheet.gender == gender) return sheet;

		return null;
	}
}
