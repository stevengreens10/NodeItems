package me.NodeDigital.NodeItems.block;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

import me.NodeDigital.NodeItems.Config;
import me.NodeDigital.NodeItems.Variables;

public class BlockStorage {

	public static List<NodeBlock> nodeBlocks;
	
	public static void loadBlocks() {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		nodeBlocks = config.getBlocks("blocks");
		for(NodeBlock block : nodeBlocks) {
			Inventory inv = config.getInventory("blocks." + block.getID() + ".inventory");
			if(inv != null) {
				block.setInventory(inv);
			}
		}
	}
	
	public static NodeBlock getBlockByID(int ID) {
		for(NodeBlock block : nodeBlocks) {
			if(block.getID() == ID) {
				return block;
			}
		}
		return null;
	}
	
	public static void saveInventory(int ID, Inventory inventory) {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		config.setInventory("blocks." + ID + ".inventory", inventory);
	}
	
	public static void storeBlock(Block block, BlockType type) {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		int ID = generateBlockID();
		config.setBlock("blocks", block, type, ID);
		nodeBlocks.add(new NodeBlock(block, type, ID));
	}
	
	public static void removeBlock(NodeBlock nodeBlock) {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		config.removeBlock("blocks", nodeBlock);
		NodeBlock toRemove = null;
		for(NodeBlock block : nodeBlocks) {
			if(block.getID() == nodeBlock.getID()) {
				toRemove = block;
			}
		}
		if(toRemove != null)
			nodeBlocks.remove(toRemove);
	}
	
	private static int generateBlockID() {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		int id = 0;
		if(config.getConfig().getConfigurationSection("blocks") != null && config.getConfig().getConfigurationSection("blocks").getKeys(false) != null) {
			String [] IDstr = config.getConfig().getConfigurationSection("blocks").getKeys(false).toArray(new String[0]);
			boolean foundID = false;
			int[] IDs = new int[IDstr.length];
			for(int i = 0; i < IDstr.length; i++) {
				String s = IDstr[i];
				IDs[i] = Integer.parseInt(s);
			}
			
			Arrays.sort(IDs);
			for(int i = 0; i < IDs.length; i++) {
				int ID = IDs[i];
				if(ID != i) {
					id = i;
					foundID = true;
					break;
				}
			}
			if(!foundID) {
				id = IDs.length;
			}
		}else {
			id = 0;
		}
		return id;
	}
	
	public static NodeBlock getNodeBlock(Block b) {		
		
		for(NodeBlock nodeBlock : nodeBlocks) {
			if(nodeBlock.getBlock().getLocation().equals(b.getLocation())) {
				return nodeBlock;
			}
		}
		
		return null;
	}
	
}
