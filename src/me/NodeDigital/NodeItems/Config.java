package me.NodeDigital.NodeItems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NodeDigital.NodeItems.block.BlockType;
import me.NodeDigital.NodeItems.block.NodeBlock;

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
			try {
				new File(filePath).createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
	
	public void removeBlock(String path, NodeBlock nodeBlock) {
		config.set(path + "." + nodeBlock.getID(), null);
		save();
	}
	
	public List<NodeBlock> getBlocks(String path){
		List<NodeBlock> nodeBlocks = new ArrayList<NodeBlock>();
		if(config.getConfigurationSection("blocks") != null && config.getConfigurationSection("blocks").getKeys(false) != null) {
			for(int i = 0; i < config.getConfigurationSection(path).getKeys(false).size(); i++) {
				int x = config.getInt(path + "." + i + ".x");
				int y = config.getInt(path + "." + i + ".y");
				int z = config.getInt(path + "." + i + ".z");
				String world = config.getString(path + "." + i + ".world");
				String typeStr = config.getString(path + "." + i + ".type");
				if(world != null && typeStr != null) {
					BlockType type = BlockType.valueOf(typeStr);
					Block block = new Location(Bukkit.getWorld(world),x,y,z).getBlock();
					nodeBlocks.add(new NodeBlock(block, type, i));
				}
				
			}
		}
		return nodeBlocks;
	}
	
	public void setBlock(String path, Block block, BlockType type, int ID) {
		Location loc = block.getLocation();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		String worldName = loc.getWorld().getName();
		config.set(path + "." + ID + ".x", x);
		config.set(path + "." + ID + ".y", y);
		config.set(path + "." + ID + ".z", z);
		config.set(path + "." + ID + ".world", worldName);
		config.set(path + "." + ID + ".material", block.getType().toString());
		config.set(path + "." + ID + ".type", type.toString());
		save();
		
	}
	
	public void setInventory(String path, Inventory inv) {
		config.set(path + ".size", inv.getSize());
		config.set(path + ".title", inv.getTitle());
		config.set(path +".contents", "");
		ItemStack[] contents = inv.getContents();
		
		for(int i = 0; i < contents.length; i++) {
			ItemStack item = contents[i];
			if(item != null) {
				setItem(path + ".contents." + i, item);
			}
		}
		
		save();
	}
	
	public void setItem(String path, ItemStack item) {
		config.set(path, item);
		save();
	}

	public Inventory getInventory(String path) {
		int size = config.getInt(path + ".size");
		String title = config.getString(path + ".title");
		if(title != null) {
			Inventory inventory = Bukkit.createInventory(null, size, title);
			for(int i = 0; i < size; i++) {
				ItemStack item = getItem(path + ".contents." + i);
				if(item != null) {
					inventory.setItem(i, item);
				}
			}
			return inventory;
		}else {
			return null;
		}
	}

	public Inventory getInventory(String path, int size, String title) {
		Inventory inventory = Bukkit.createInventory(null, size, title);
		for(int i = 0; i < size; i++) {
			ItemStack item = getItem(path + ".contents." + i);
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
