package me.NodeDigital.NodeItems.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.NodeDigital.NodeItems.item.NodeItems;

public class ArmorTasks implements Runnable{

	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
//			ItemStack helmet = p.getInventory().getHelmet();
			ItemStack chestplate = p.getInventory().getChestplate();
//			ItemStack pants = p.getInventory().getLeggings();
//			ItemStack boots = p.getInventory().getBoots();
			
			if(chestplate != null && NodeItems.isItemSimilarTo(chestplate, NodeItems.INVISIBILITY_CLOAK, true)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 1), true);
			}
		}
	}

}
