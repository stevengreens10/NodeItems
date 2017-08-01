package me.NodeDigital.NodeItems.item;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NodeDigital.NodeItems.Config;
import me.NodeDigital.NodeItems.Variables;


public class Backpack {

	public static int generateID(Player p) {
		Config config = new Config(Variables.FILEPATH + "storage/" + p.getUniqueId().toString()+".yml");
		int id = 0;
		if(config.getConfig().getConfigurationSection("backpacks") != null && config.getConfig().getConfigurationSection("backpacks").getKeys(false) != null) {
			String [] IDs = config.getConfig().getConfigurationSection("backpacks").getKeys(false).toArray(new String[0]);
			boolean foundID = false;
			for(int i = 0; i < IDs.length; i++) {
				int ID = Integer.parseInt(IDs[i]);
				if(ID != i) {
					id = i;
					foundID = true;
					break;
				}
			}
			if(!foundID) {
				id = IDs.length;
			}
		}else {
			id = 0;
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
		Config config = new Config(Variables.FILEPATH + "storage/" + p.getUniqueId().toString()+".yml");
		return config.getInventory("backpacks." + Backpack.getID(backpack) + ".contents", 27, "Backpack");
	}
	
	public static void saveBackpack(Player p, ItemStack backpack, Inventory inventory) {
		UUID uuid = p.getUniqueId();
		Config config = new Config(Variables.FILEPATH + "storage/" + uuid.toString()+".yml");
		int ID = getID(backpack);
		config.set("backpacks."+ID + ".contents", "");
		config.setInventory("backpacks."+ID + ".contents", inventory);
	}
	
	public static int getID(ItemStack backpack) {
		String IDLine = backpack.getItemMeta().getLore().get(0);
		String IDStr = IDLine.substring(IDLine.indexOf('#')+1, IDLine.length());
		return Integer.parseInt(IDStr);
	}
	
	public static ItemStack getBackpack(Player player, int id) {
		ItemStack backpack = new ItemStack(NodeItems.BACKPACK);
		ItemMeta meta = backpack.getItemMeta();
		List<String> lore = meta.getLore();
		lore.set(0, "ID: " + player.getUniqueId() + "#" + id);
		meta.setLore(lore);
		backpack.setItemMeta(meta);
		return backpack;
	}
}
