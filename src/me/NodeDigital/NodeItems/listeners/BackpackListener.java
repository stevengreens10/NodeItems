package me.NodeDigital.NodeItems.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
			ItemStack heldItem = e.getPlayer().getInventory().getItemInMainHand();
			
			if(NodeItems.isItemSimilarTo(heldItem, NodeItems.BACKPACK, false)) {
				if(heldItem.getAmount() == 1) {
					ItemMeta backpackMeta = heldItem.getItemMeta();
					if(backpackMeta.getLore().get(0).equalsIgnoreCase("ID: <ID>")) {
						//TODO Set id for backpack
						int id = Backpack.generateID(p);
						List<String> lore = new ArrayList<String>();
						lore.add(backpackMeta.getLore().get(0).replaceFirst("<ID>", p.getUniqueId().toString()+"#"+id));
						backpackMeta.setLore(lore);
						heldItem.setItemMeta(backpackMeta);
						p.sendMessage("ID = " + p.getUniqueId().toString()+"#"+id);
					}
					
					//TODO Open backpack
				}else {
					p.sendMessage("You can not stack backpacks!");
				}
			}
		}
	}
	
}
