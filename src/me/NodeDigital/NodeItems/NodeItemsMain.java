package me.NodeDigital.NodeItems;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.NodeDigital.NodeItems.command.NodeItemsCommand;
import me.NodeDigital.NodeItems.inventory.NodeItemsInventory;
import me.NodeDigital.NodeItems.item.NodeItems;
import me.NodeDigital.NodeItems.listeners.BlockListener;
import me.NodeDigital.NodeItems.listeners.GadgetListener;
import me.NodeDigital.NodeItems.listeners.InventoryListener;
import me.NodeDigital.NodeItems.listeners.ItemListener;
import me.NodeDigital.NodeItems.tasks.ArmorTasks;
import me.NodeDigital.NodeItems.tasks.ItemTasks;

public class NodeItemsMain extends JavaPlugin{
	public void onEnable() {
		super.onEnable();
		List<ItemStack> items = NodeItems.constructItems();
		NodeItemsInventory.constructInventory(items);
		
		getCommand("ni").setExecutor(new NodeItemsCommand());
		new GadgetListener(this);
		new InventoryListener(this);
		new ItemListener(this);
		new BlockListener(this);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ArmorTasks(), 0L, 10L);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ItemTasks(), 0L, 10L);

	}
	
	public void onDisable() {
		
	}
}
