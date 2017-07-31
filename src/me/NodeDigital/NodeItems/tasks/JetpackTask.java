package me.NodeDigital.NodeItems.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class JetpackTask implements Runnable{

	Player player;
	int ID;
	
	public JetpackTask(Player p) {
		player = p;
	}
	
	public void setID(int id) {
		ID = id;
	}
	
	@Override
	public void run() {
		if(player.isSneaking()) {
			player.setVelocity(player.getVelocity().setY(0.5));
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, (float) 0.25, 1);
			player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 5);
			player.setFallDistance(0);
		}else {
			Bukkit.getScheduler().cancelTask(ID);
		}
	}

}
