package me.NodeDigital.NodeItems.item;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.Variables;


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
	
	public static void openBackpack(Player p, ItemStack backpack) {
		Inventory inv = getInventory(p, backpack);
		
		if(inv != null) {
			p.openInventory(inv);
		}
	}
	
	public static Inventory getInventory(Player p, ItemStack backpack) {
		//TODO
		return null;
	}
	
	public static void saveBackpack(Player p, ItemStack backpack) {
		UUID uuid = p.getUniqueId();
		File file = new File(Variables.FILEPATH + "storage/" + uuid.toString()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		int ID = getID(backpack);
		config.set("backpacks."+ID, "Test");
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO
	}
	
	public static int getID(ItemStack backpack) {
		String IDLine = backpack.getItemMeta().getLore().get(0);
		String IDStr = IDLine.substring(IDLine.indexOf('#')+1, IDLine.length());
		return Integer.parseInt(IDStr);
	}
}
