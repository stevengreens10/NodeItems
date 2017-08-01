package me.NodeDigital.NodeItems.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import me.NodeDigital.NodeItems.item.NodeItems;

public class ItemTasks implements Runnable{

	long iterations = 0;
	
	public void run() {
		iterations++;
		for(Player p : Bukkit.getOnlinePlayers()) {
			ItemStack heldItem = p.getInventory().getItemInMainHand();
			
			if(!p.isDead() && heldItem != null && NodeItems.isItemSimilarTo(heldItem, NodeItems.AUTO_BOW, true) && iterations % 3 == 0) {
				
				double lowestDistance = 100000;
				Entity closestEntity = null;
				EntityType[] validEntities = {EntityType.ZOMBIE, EntityType.BLAZE, 
						EntityType.CREEPER, EntityType.GHAST, EntityType.SKELETON, EntityType.SHULKER, 
						EntityType.WITHER_SKELETON, EntityType.WITHER, EntityType.ENDER_DRAGON,
						EntityType.WITCH, EntityType.VINDICATOR, EntityType.VEX, EntityType.SPIDER};
				
				for(Entity ent : p.getWorld().getEntities()) {
					double distance = ent.getLocation().distance(p.getLocation());
					boolean validEntity = false;
					
					
					
					for(EntityType type : validEntities) {
						if(ent.getType() == type) validEntity = true;
					}
					
					if(ent.getType() == EntityType.PIG_ZOMBIE) {
						PigZombie pigman = (PigZombie) ent;
						if(pigman.isAngry()) validEntity = true;
					}
					
					if(validEntity && distance < lowestDistance) {
						lowestDistance = distance;
						closestEntity = ent;
					}
				}
				if(closestEntity != null && lowestDistance <= 10) {
					Location spawnLocation = p.getLocation().add(0, 2, 0);
					for(ItemStack item : p.getInventory().getContents()) {
						if(item != null && item.getType() == Material.ARROW) {
							Arrow arrow = (Arrow) p.getWorld().spawnEntity(spawnLocation, EntityType.ARROW);
							arrow.setShooter((ProjectileSource) p); 
							Vector vector = closestEntity.getLocation().toVector().subtract(spawnLocation.toVector());
							arrow.setVelocity(vector.multiply(0.5));
							if(!heldItem.containsEnchantment(Enchantment.ARROW_INFINITE))
								item.setAmount(item.getAmount()-1);
							heldItem.setDurability((short) (heldItem.getDurability() - 2) );
							break;
						}
					}
					
				}
				
			}
		}
	}
}
