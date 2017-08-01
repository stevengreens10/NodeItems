package me.NodeDigital.NodeItems.item;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;


public class Backpack {
	private static Map<UUID, Integer> lastID = new HashMap<UUID, Integer>();

	public static int generateID(Player p) {
		UUID uuid = p.getUniqueId();
		int id = 0;
		if(lastID.containsKey(uuid)) {
			id = lastID.get(uuid)+1;
			lastID.put(uuid, id);
		}else {
			lastID.put(uuid, 0);
		}
		
		return id;
	}
}
