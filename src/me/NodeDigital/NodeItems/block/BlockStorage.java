package me.NodeDigital.NodeItems.block;

import java.util.List;

import org.bukkit.block.Block;

import me.NodeDigital.NodeItems.Config;
import me.NodeDigital.NodeItems.Variables;

public class BlockStorage {

	public static List<NodeBlock> nodeBlocks;
	
	public static void loadBlocks() {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		nodeBlocks = config.getBlocks("blocks");
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
		for(NodeBlock block : nodeBlocks) {
			if(block.getID() == nodeBlock.getID()) {
				nodeBlocks.remove(block);
			}
		}
	}
	
	private static int generateBlockID() {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		int id = 0;
		if(config.getConfig().getConfigurationSection("blocks") != null && config.getConfig().getConfigurationSection("blocks").getKeys(false) != null) {
			String [] IDs = config.getConfig().getConfigurationSection("blocks").getKeys(false).toArray(new String[0]);
			boolean foundID = false;
			for(int i = 0; i < IDs.length; i++) {
				int ID = Integer.parseInt(IDs[i]);
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
