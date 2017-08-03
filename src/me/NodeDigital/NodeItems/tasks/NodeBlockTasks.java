package me.NodeDigital.NodeItems.tasks;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.block.BlockStorage;
import me.NodeDigital.NodeItems.block.NodeBlock;

public class NodeBlockTasks implements Runnable {

	public void run() {
		for(NodeBlock block : BlockStorage.nodeBlocks) {
			Location loc = block.getBlock().getLocation();
			
			if(loc.getBlock().getType() != block.getBlock().getType()) {
				ItemStack[] contents = block.getInventory().getContents();
				for(ItemStack item : contents) {
					if(item != null) {
						block.getBlock().getWorld().dropItemNaturally(block.getBlock().getLocation(), item);
					}
				}
				BlockStorage.removeBlock(block);
			}else {
				if(block.hasInventory()) {
					block.saveInventory();
				}
			}
		}
	}

}
