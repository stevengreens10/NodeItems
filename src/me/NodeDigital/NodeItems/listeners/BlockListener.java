package me.NodeDigital.NodeItems.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.block.BlockStorage;
import me.NodeDigital.NodeItems.block.BlockType;
import me.NodeDigital.NodeItems.block.NodeBlock;
import me.NodeDigital.NodeItems.item.NodeItems;

public class BlockListener implements Listener{

	public BlockListener(NodeItemsMain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		ItemStack heldItem = player.getInventory().getItemInMainHand();
		Block block = e.getBlock();
		
		if(heldItem != null && NodeItems.isItemSimilarTo(heldItem, NodeItems.TESTBLOCK, true)) {
			BlockStorage.storeBlock(block, BlockType.TESTBLOCK);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block block = e.getBlock();
		NodeBlock nodeBlock = BlockStorage.getNodeBlock(block);
		if(nodeBlock != null) {
			if(nodeBlock.getType() == BlockType.TESTBLOCK) {
				block.getWorld().dropItemNaturally(block.getLocation(), NodeItems.TESTBLOCK);
				e.setCancelled(true);
				block.setType(Material.AIR);
				BlockStorage.removeBlock(nodeBlock);
			}
		}
	}
	
}