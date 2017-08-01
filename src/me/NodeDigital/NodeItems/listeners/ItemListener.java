package me.NodeDigital.NodeItems.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.NodeItemsMain;
import me.NodeDigital.NodeItems.item.NodeItems;

public class ItemListener implements Listener{

	
	public ItemListener(NodeItemsMain plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		new BackpackListener(plugin);
	}
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent e) {
		Entity ent = e.getEntity();
		if(ent instanceof Player) {
			Player player = (Player) ent;
			ItemStack bow = e.getBow();
			if(bow != null && NodeItems.isItemSimilarTo(bow, NodeItems.EXPLOSIVE_BOW, false)) {
				ItemStack[] items = player.getInventory().getStorageContents();
				
				for(ItemStack item : items) {
					if(item != null && item.getType() == Material.FIREBALL) {
						item.setAmount(item.getAmount()-1);
						Entity fireball = player.getWorld().spawnEntity(player.getLocation().add(player.getLocation().getDirection().multiply(3)), EntityType.FIREBALL);
						fireball.setVelocity(player.getLocation().getDirection());
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		ItemStack heldItem = player.getInventory().getItemInMainHand();
		if(heldItem != null) {
			
			if(NodeItems.isItemSimilarTo(heldItem, NodeItems.EXPLOSIVE_PICK, false)) {
				Material[] validMaterials = { Material.COBBLESTONE, Material.STONE, Material.IRON_ORE, Material.COAL_ORE,
											Material.REDSTONE_ORE, Material.GOLD_ORE, Material.LAPIS_ORE, Material.EMERALD_ORE,
											Material.QUARTZ_ORE, Material.NETHERRACK, Material.DIRT };
				
				boolean isValid = false;
				for(Material mat : validMaterials) {
					if(block.getType() == mat) {
						isValid = true;
						
					}
				}
				
				if(isValid) {
					block.getWorld().createExplosion(block.getLocation(), 0f);
					block.getWorld().playSound(block.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);
					List<Block> blocks = new ArrayList<Block>();
					
					for(int x = -1; x <= 1; x++) {
						for(int y = -1; y <= 1; y++) {
							for(int z = -1; z <= 1; z++) {
								Block b = block.getRelative(x, y, z);
								
								for(Material mat : validMaterials) {
									if(b.getType() == mat) {
										b.breakNaturally(heldItem);
										

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
