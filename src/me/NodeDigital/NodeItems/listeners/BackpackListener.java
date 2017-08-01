package me.NodeDigital.NodeItems.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.item.Backpack;
import me.NodeDigital.NodeItems.item.NodeItems;

public class BackpackListener implements Listener{

	public BackpackListener(NodeItemsMain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			ItemStack backpack = e.getPlayer().getInventory().getItemInMainHand();
			
			if(NodeItems.isItemSimilarTo(backpack, NodeItems.BACKPACK, false)) {
				if(backpack.getAmount() == 1) {
					ItemMeta backpackMeta = backpack.getItemMeta();
					List<String> lore = backpackMeta.getLore();
					if(lore.get(0).equalsIgnoreCase("ID: <ID>")) {
						int id = Backpack.generateID(p);
						lore.set(0, lore.get(0).replaceFirst("<ID>", p.getUniqueId().toString()+"#"+id));
						backpackMeta.setLore(lore);
						backpack.setItemMeta(backpackMeta);
					}
					
					if(lore.get(0).substring(4, lore.get(0).indexOf('#')).equalsIgnoreCase(p.getUniqueId().toString())) {
						Backpack.openBackpack(p, backpack);
					}else {
						p.sendMessage("This is not your backpack!");
					}
				}else {
					p.sendMessage("You can not stack backpacks!");
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if(e.getInventory().getName().equalsIgnoreCase("Backpack")) {
			ItemStack backpack = p.getInventory().getItemInMainHand();
			if(NodeItems.isItemSimilarTo(backpack, NodeItems.BACKPACK, false)) {
				Backpack.saveBackpack(p, backpack, e.getInventory());
			}
		}
	}
	
	public void onDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		ItemStack backpack = p.getInventory().getItemInMainHand();
		
		if(NodeItems.isItemSimilarTo(backpack, NodeItems.BACKPACK, false)) {
			e.setCancelled(true);
			p.sendMessage("You can not drop backpacks!");
		}
	}
	
}
