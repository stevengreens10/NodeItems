package me.NodeDigital.NodeItems;

import org.bukkit.plugin.java.JavaPlugin;

import me.NodeDigital.NodeItems.command.NodeItemsCommand;
import me.NodeDigital.NodeItems.item.NodeItems;
import me.NodeDigital.NodeItems.listeners.GadgetListener;
import me.NodeDigital.NodeItems.listeners.ItemListener;

public class NodeItemsMain extends JavaPlugin{
	public void onEnable() {
		super.onEnable();
		NodeItems.constructItems();
		
		getCommand("ni").setExecutor(new NodeItemsCommand());
		new GadgetListener(this);
		new ItemListener(this);
	}
	
	public void onDisable() {
		
	}
}
