package me.NodeDigital.NodeItems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.inventory.NodeItemsInventory;

public class InventoryListener implements Listener{

	public InventoryListener(NodeItemsMain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this,plugin);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Inventory clicked = e.getClickedInventory();
		
		if(clicked.getName().equalsIgnoreCase(NodeItemsInventory.inventory.getName())) {
			int slot = e.getSlot();
			
			if(clicked.getItem(slot) != null) {
				Player player = (Player) e.getWhoClicked();
				player.getInventory().addItem(clicked.getItem(slot));
				e.setCancelled(true);
			}
		}
	}
	
}
