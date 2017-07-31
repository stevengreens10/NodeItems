package me.NodeDigital.NodeItems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.item.NodeItems;
import me.NodeDigital.NodeItems.tasks.JetBootsTask;
import me.NodeDigital.NodeItems.tasks.JetpackTask;

public class GadgetListener implements Listener{
	
	NodeItemsMain plugin;
	
	public GadgetListener(NodeItemsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		ItemStack chestplate = p.getInventory().getChestplate();
		if(chestplate != null && e.isSneaking()) {
			if(NodeItems.isItemSimilarTo(chestplate, NodeItems.JETPACK, false)) {
				JetpackTask jetpackTask = new JetpackTask(p);
				jetpackTask.setID(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, jetpackTask, 0L, 3L));
			}
		}
	}
	
	@EventHandler
	public void onSprint(PlayerToggleSprintEvent e) {
		if(e.isSprinting()) {
			Player p = e.getPlayer();
			ItemStack boots = p.getInventory().getBoots();
			
			if(boots != null && NodeItems.isItemSimilarTo(boots, NodeItems.JETBOOTS, false)) {
				JetBootsTask task = new JetBootsTask(p);
				task.setID(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0L, 10L));
			}
		}
	}
}
