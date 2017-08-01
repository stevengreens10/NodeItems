package me.NodeDigital.NodeItems.item;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.Config;
import me.NodeDigital.NodeItems.Variables;


public class Backpack {

	public static int generateID(Player p) {
		
		Config config = new Config(Variables.FILEPATH + "storage/" + p.getUniqueId().toString()+".yml");
		int numBackpacks;
		if(config.getConfig().getConfigurationSection("backpacks").getKeys(false) != null) {
			numBackpacks = config.getConfig().getConfigurationSection("backpacks").getKeys(false).size();
		}else {
			Bukkit.broadcastMessage("No backpacks");
			numBackpacks = 0;
		}
		
		Bukkit.broadcastMessage("" + numBackpacks);
		
		return numBackpacks;
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
}
