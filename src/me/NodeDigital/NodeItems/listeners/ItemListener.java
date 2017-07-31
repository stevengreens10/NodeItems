package me.NodeDigital.NodeItems.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.item.NodeItems;

public class ItemListener implements Listener{

	
	public ItemListener(NodeItemsMain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		ItemStack heldItem = player.getInventory().getItemInMainHand();
		if(heldItem != null) {
			
			if(NodeItems.isItemSimilarTo(heldItem, NodeItems.EXPLOSIVE_PICK)) {
				Material[] validMaterials = { Material.COBBLESTONE, Material.STONE, Material.IRON_ORE, Material.COAL_ORE,
											Material.REDSTONE_ORE, Material.GOLD_ORE, Material.LAPIS_ORE, Material.EMERALD_ORE,
											Material.QUARTZ_ORE, Material.NETHERRACK };
				
				boolean isValid = false;
				for(Material mat : validMaterials) {
					if(block.getType() == mat) {
						isValid = true;
					}
				}
				
				if(isValid) {
					List<Block> blocks = new ArrayList<Block>();
					
					for(int xOff = -1; xOff <= 1; xOff++) {
						for(int yOff = -1; yOff <= 1; yOff++) {
							if(xOff == 0 && yOff == 0) {
								continue;
							}else {
								
								Location loc;
								String direction = getCardinalDirection(player);
								if(direction.equalsIgnoreCase("N") || direction.equalsIgnoreCase("NE") || direction.equalsIgnoreCase("NW") || 
								direction.equalsIgnoreCase("S") || direction.equalsIgnoreCase("SE") || direction.equalsIgnoreCase("SW")) {
									
									loc = block.getLocation().add(0, yOff, xOff);
								}else {
									loc = block.getLocation().add(xOff, yOff, 0);
								}
								Block b = loc.getBlock();
								for(Material mat: validMaterials) {
									if(b.getType() == mat) {
										blocks.add(b);
									}
								}
							}
						}
					}
					
					for(Block blk : blocks) {
						blk.breakNaturally(heldItem);
					}
				}
			}
			
		}
	}
	
	public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
         if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
	
}