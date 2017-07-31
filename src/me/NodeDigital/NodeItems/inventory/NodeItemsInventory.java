package me.NodeDigital.NodeItems.inventory;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class NodeItemsInventory {
	public static Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "NodeItems");
	
	public static void constructInventory(List<ItemStack> items) {
		int slot = 0;
		for(ItemStack item : items) {
			if(slot < 27) {
				inventory.setItem(slot, item);
				slot++;
			}
		}
	}
}
