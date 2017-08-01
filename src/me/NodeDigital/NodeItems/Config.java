package me.NodeDigital.NodeItems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Config {

	FileConfiguration config;
	String filePath;
	
	public Config(String path) {
		filePath = path;
		config = YamlConfiguration.loadConfiguration(new File(filePath));
	}
	
	public FileConfiguration getConfig() {
		try {
			config.load(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public void save() {
		try {
			config.save(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set(String path, Object o) {
		
		config.set(path, o);
	}
	
	public void setInventory(String path, Inventory inv) {
		ItemStack[] contents = inv.getContents();
		
		for(int i = 0; i < contents.length; i++) {
			ItemStack item = contents[i];
			if(item != null) {
				setItem(path + "." + i, item);
			}
		}
		
		save();
	}
	
	public void setItem(String path, ItemStack item) {
		config.set(path, item);
	}

	public Inventory getInventory(String path, int size, String title) {
		Inventory inventory = Bukkit.createInventory(null, size, title);
		for(int i = 0; i < size; i++) {
			ItemStack item = getItem(path + "." + i);
			if(item != null) {
				inventory.setItem(i, item);
			}
		}
		return inventory;
	}

	private ItemStack getItem(String path) {
		ItemStack item = config.getItemStack(path);
		return item;
	}
	
}
