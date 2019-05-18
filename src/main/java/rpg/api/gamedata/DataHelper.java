package rpg.api.gamedata;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import rpg.api.entity.item.Inventory;
import rpg.api.entity.item.ItemStack;

public class DataHelper {
	
	public static Map<String, Object> inventoryToMap(final Inventory inv, final String path) {
		final HashMap<String, Object> data = new HashMap<>();
		
		data.put("max_size", inv.getMaxSize());
		data.put("items", inv.getItems().parallelStream().map(item -> item.getUniqueId()).collect(Collectors.toList()));
		
		inv.getItems().forEach(item -> {
			try {
				new EntityData(item, path).save();
			}catch(final IOException e) {
				e.printStackTrace();
			}
		});
		
		return data;
	}
	
	public static Inventory mapToInventory(final Map<String, Object> data, final String path) {
		final Inventory inv = new Inventory((int) data.get("max_size"));
		
		((List<UUID>) data.get("items")).forEach(uuid -> {
			try {
				final EntityData ed = new EntityData(uuid, path);
				
				ed.load();
				
				inv.addItemStack((ItemStack) ed.getEntity());
			}catch(final IOException e) {
				e.printStackTrace();
			}
		});
		
		return inv;
	}
}
