package me.NodeDigital.NodeItems.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class NodeItems {
	public static ItemStack JETPACK;
	public static ItemStack JETBOOTS;
	public static ItemStack EXPLOSIVE_PICK;
	public static ItemStack EXPLOSIVE_BOW;

	public static void constructItems() {
		JETPACK = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta meta = JETPACK.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Jetpack");
		JETPACK.setItemMeta(meta);
		
		JETBOOTS = new ItemStack(Material.LEATHER_BOOTS);
		meta = JETBOOTS.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Jet boots");
		JETBOOTS.setItemMeta(meta);
		
		EXPLOSIVE_PICK = new ItemStack(Material.DIAMOND_PICKAXE);
		meta = EXPLOSIVE_PICK.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Explosive Pickaxe");
		EXPLOSIVE_PICK.setItemMeta(meta);
		
		EXPLOSIVE_BOW = new ItemStack(Material.BOW);
		meta = EXPLOSIVE_BOW.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Explosive Bow");
		EXPLOSIVE_BOW.setItemMeta(meta);
		
		
	}

	public static boolean isItemSimilarTo(ItemStack item1, ItemStack item2) {
		return (item1.getType() == item2.getType()
			&& item1.getItemMeta().getDisplayName() == item2.getItemMeta().getDisplayName());
	}
}
