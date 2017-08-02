package me.NodeDigital.NodeItems.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.block.BlockStorage;
import me.NodeDigital.NodeItems.block.NodeBlock;
import me.NodeDigital.NodeItems.inventory.NodeItemsInventory;

public class InventoryListener implements Listener{

	public InventoryListener(NodeItemsMain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this,plugin);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Inventory clicked = e.getClickedInventory();
		
		if(clicked != null && clicked.getName().equalsIgnoreCase(NodeItemsInventory.inventory.getName())) {
			int slot = e.getSlot();
			
			if(clicked.getItem(slot) != null) {
				Player player = (Player) e.getWhoClicked();
				player.getInventory().addItem(clicked.getItem(slot));
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Inventory inv = e.getInventory();
		if(inv != null) {
			try {
				if(inv.getName().contains("#")) {
					int ID = Integer.parseInt(inv.getName().substring(inv.getName().indexOf('#')+1, inv.getName().length()));
					NodeBlock block = BlockStorage.getBlockByID(ID);
					if(block != null) {
						block.saveInventory();
					}
					
					if(inv.getName().contains("Secret Chest")) {
						block.getBlock().getWorld().playSound(block.getBlock().getLocation(), Sound.BLOCK_CHEST_CLOSE, 1F, 1F);
					}
				}
			}catch(Exception ex) {}
		}
	}
	
}
