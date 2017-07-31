package me.NodeDigital.NodeItems.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JetBootsTask implements Runnable{

	Player player;
	int ID;
	
	public JetBootsTask(Player p) {
		player = p;
	}
	
	public void setID(int id) {
		ID = id;
	}
	
	@Override
	public void run() {
		if(player.isSprinting()) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 2));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, (float) 0.25, 5L);
			player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 5);
		}else {
			Bukkit.getScheduler().cancelTask(ID);
		}
	}

}
