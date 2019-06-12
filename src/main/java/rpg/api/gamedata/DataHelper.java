package rpg.api.gamedata;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import rpg.Logger;
import rpg.api.entity.item.Inventory;
import rpg.api.entity.item.ItemStack;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.Sprite.WalkableSprite;
import rpg.api.gfx.SpriteTheme;

public class DataHelper {

	private static Map<String, Object> map() {
		return new HashMap<>();
	}

	public static Map<String, Object> inventoryToMap(final Inventory inv, final String path) {
		final Map<String, Object> data = map();

		data.put("max_size", inv.getMaxSize());
		data.put("items", inv.getItems().parallelStream().map(item -> item.getUniqueId()).collect(Collectors.toList()));

		inv.getItems().forEach(item -> {
			try {
				new EntityData(item, path).save();
			} catch(final IOException e) {
				Logger.error(e);
			}
		});

		return data;
	}

	@SuppressWarnings("unchecked")
	public static Inventory mapToInventory(final Map<String, Object> data, final String path) {
		final Inventory inv = new Inventory((int) data.get("max_size"));

		((List<UUID>) data.get("items")).forEach(uuid -> {
			try {
				final EntityData ed = new EntityData(uuid, path);

				ed.load();

				inv.addItemStack((ItemStack) ed.getEntity());
			} catch(final IOException e) {
				Logger.error(e);
			}
		});

		return inv;
	}

	public static Map<String, Object> spriteToMap(final Sprite sprite) {
		final Map<String, Object> data = map();
		final boolean walkable = sprite instanceof WalkableSprite;

		data.put("name", sprite.getName());
		data.put("walkable", walkable);

		if(!walkable) {
			data.put("theme", sprite.getLoadedTheme().getPathModifier());
			data.put("delay", sprite.getFrameDelay());
		}

		return data;
	}

	public static Sprite mapToSprite(final Map<String, Object> data) {
		Sprite sprite;
		final String name = (String) data.get("name");

		if((boolean) data.get("walkable")) sprite = new WalkableSprite(name);
		else sprite = new Sprite(name, SpriteTheme.getByModifier((String) data.get("theme")), (double) data.get("delay"));

		return sprite;
	}
}
