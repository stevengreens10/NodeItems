package me.NodeDigital.NodeItems.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.NodeDigital.NodeItems.Variables;
import me.NodeDigital.NodeItems.item.NodeItems;
import net.md_5.bungee.api.ChatColor;

public class NodeItemsCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("jetpack")) {
					player.getInventory().addItem(NodeItems.JETPACK);
				}else if(args[0].equalsIgnoreCase("jetboots")) {
					player.getInventory().addItem(NodeItems.JETBOOTS);
				}else if(args[0].equalsIgnoreCase("explosivepick")) {
					player.getInventory().addItem(NodeItems.EXPLOSIVE_PICK);
				}else if(args[0].equalsIgnoreCase("explosivebow")) {
					player.getInventory().addItem(NodeItems.EXPLOSIVE_BOW);
				}
			}else {
				player.sendMessage(Variables.PREFIX + ChatColor.AQUA + "Try /ni <jetpack|jetboots|explosivepick>");
			}
		}
		
		return false;
	}
	
}
