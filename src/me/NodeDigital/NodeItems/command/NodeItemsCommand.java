package me.NodeDigital.NodeItems.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.NodeDigital.NodeItems.Variables;
import me.NodeDigital.NodeItems.inventory.NodeItemsInventory;
import me.NodeDigital.NodeItems.item.Backpack;
import net.md_5.bungee.api.ChatColor;

public class NodeItemsCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("backpack")) {
					if(args.length >= 2) {
						try {
							int id = Integer.parseInt(args[1]);
						
							player.getInventory().addItem(Backpack.getBackpack(player, id));
						}catch(Exception e) {
							player.sendMessage(Variables.PREFIX + ChatColor.RED + "The ID must be a number!");
						}
					}else {
						player.sendMessage(Variables.PREFIX + ChatColor.RED + "You must include an ID!");
					}
				}
			}else {
				player.openInventory(NodeItemsInventory.inventory);
			}
		}
		
		return false;
	}
	
}
