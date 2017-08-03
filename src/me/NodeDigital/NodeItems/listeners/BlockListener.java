package me.NodeDigital.NodeItems.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.block.BlockStorage;
import me.NodeDigital.NodeItems.block.BlockType;
import me.NodeDigital.NodeItems.block.NodeBlock;
import me.NodeDigital.NodeItems.item.NodeItems;
import net.md_5.bungee.api.ChatColor;

public class BlockListener implements Listener{

	public BlockListener(NodeItemsMain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		ItemStack heldItem = player.getInventory().getItemInMainHand();
		Block block = e.getBlock();
		
		if(heldItem != null && NodeItems.isItemSimilarTo(heldItem, NodeItems.SECRET_CHEST, true)) {
			BlockStorage.storeBlock(block, BlockType.SECRET_CHEST);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block block = e.getBlock();
		NodeBlock nodeBlock = BlockStorage.getNodeBlock(block);
		if(nodeBlock != null) {
			if(nodeBlock.getType() == BlockType.SECRET_CHEST) {
				if(nodeBlock.hasInventory()) {
				ItemStack[] contents = nodeBlock.getInventory().getContents();
					for(ItemStack item : contents) {
						if(item != null) {
							block.getWorld().dropItemNaturally(block.getLocation(), item);
						}
					}
				}
				
				nodeBlock.setInventory(null);

				block.getWorld().dropItemNaturally(block.getLocation(), NodeItems.SECRET_CHEST);
				e.setCancelled(true);
				block.setType(Material.AIR);
				BlockStorage.removeBlock(nodeBlock);
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			NodeBlock nodeBlock = BlockStorage.getNodeBlock(block);
			
			if(nodeBlock != null) {
				if(nodeBlock.getType() == BlockType.SECRET_CHEST && e.getPlayer().isSneaking()) {
					e.setCancelled(true);
					if(nodeBlock.getInventory() == null) {
						player.openInventory(nodeBlock.createInventory(null, 27, ChatColor.GOLD + "Secret Chest"));
					}else {
						player.openInventory(nodeBlock.getInventory());
					}
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1F, 1F);
				}
			}
		}
	}
	
}
