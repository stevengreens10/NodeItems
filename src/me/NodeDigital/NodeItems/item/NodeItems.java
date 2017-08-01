package me.NodeDigital.NodeItems.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class NodeItems {
	public static ItemStack JETPACK;
	public static ItemStack JETBOOTS;
	public static ItemStack EXPLOSIVE_PICK;
	public static ItemStack EXPLOSIVE_BOW;
	public static ItemStack INVISIBILITY_CLOAK;
	public static ItemStack AUTO_BOW;
	public static ItemStack BACKPACK;
	public static List<ItemStack> items = new ArrayList<ItemStack>();

	public static List<ItemStack> constructItems() {
		JETPACK = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta meta = JETPACK.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Hold shift to fly into the air!");
		meta.setDisplayName(ChatColor.BLUE + "Jetpack");
		meta.setLore(lore);
		JETPACK.setItemMeta(meta);
		items.add(JETPACK);
		
		JETBOOTS = new ItemStack(Material.LEATHER_BOOTS);
		meta = JETBOOTS.getItemMeta();
		lore = new ArrayList<String>();
		meta.setDisplayName(ChatColor.BLUE + "Jet boots");
		lore.add(ChatColor.AQUA + "Sprint to activate.");
		meta.setLore(lore);
		JETBOOTS.setItemMeta(meta);
		items.add(JETBOOTS);
		
		EXPLOSIVE_PICK = new ItemStack(Material.DIAMOND_PICKAXE);
		meta = EXPLOSIVE_PICK.getItemMeta();
		lore = new ArrayList<String>();
		meta.setDisplayName(ChatColor.GOLD + "Explosive Pickaxe");
		lore.add(ChatColor.AQUA + "Look, it's a pickaxe that causes explosions.");
		lore.add(ChatColor.AQUA + "What more do you want?");
		meta.setLore(lore);
		EXPLOSIVE_PICK.setItemMeta(meta);
		items.add(EXPLOSIVE_PICK);
		
		EXPLOSIVE_BOW = new ItemStack(Material.BOW);
		meta = EXPLOSIVE_BOW.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Explosive Bow");
		lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Shoots fire charges");
		lore.add(ChatColor.AQUA + "You must have at least one arrow");
		meta.setLore(lore);

		EXPLOSIVE_BOW.setItemMeta(meta);
		items.add(EXPLOSIVE_BOW);
		
		INVISIBILITY_CLOAK = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		meta = INVISIBILITY_CLOAK.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GRAY + "Invisibility Cloak");
		lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Poof!");
		meta.setLore(lore);
		INVISIBILITY_CLOAK.setItemMeta(meta);
		items.add(INVISIBILITY_CLOAK);
		
		AUTO_BOW = new ItemStack(Material.BOW);
		meta = AUTO_BOW.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED + "Auto Bow");
		lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Will automatically shoot at nearest enemy");
		lore.add(ChatColor.AQUA + "When in your hand");
		meta.setLore(lore);
		AUTO_BOW.setItemMeta(meta);
		items.add(AUTO_BOW);
		
		BACKPACK = new ItemStack(Material.ENCHANTED_BOOK);
		meta = BACKPACK.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_AQUA + "Backpack");
		lore = new ArrayList<String>();
		lore.add("ID: <ID>");
		meta.setLore(lore);
		BACKPACK.setItemMeta(meta);
		items.add(BACKPACK);
		
		return items;
		
	}
	
	public static boolean isItemSimilarTo(ItemStack item1, ItemStack item2, boolean checkLore) {
		return (item1.getType() == item2.getType()
			&& item1.getItemMeta().getDisplayName().equalsIgnoreCase(item2.getItemMeta().getDisplayName())
			&& (!checkLore || item1.getItemMeta().getLore().equals(item2.getItemMeta().getLore())));
	}
}
