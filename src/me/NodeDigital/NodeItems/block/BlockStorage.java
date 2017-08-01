package me.NodeDigital.NodeItems.block;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import me.NodeDigital.NodeItems.Config;
import me.NodeDigital.NodeItems.Variables;

public class BlockStorage {

	public static void storeBlock(Block block, BlockType type) {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		config.setBlock("blocks", block, type, generateBlockID());
	}
	
	public static void removeBlock(NodeBlock nodeBlock) {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		config.removeBlock("blocks", nodeBlock);
	}
	
	private static int generateBlockID() {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		int numBlocks;
		if(config.getConfig().getConfigurationSection("blocks") != null && config.getConfig().getConfigurationSection("blocks").getKeys(false) != null) {
			numBlocks = config.getConfig().getConfigurationSection("blocks").getKeys(false).size();
		}else {
			numBlocks = 0;
		}
		return numBlocks;
	}
	
	public static NodeBlock getNodeBlock(Block b) {
		Config config = new Config(Variables.FILEPATH + "storage/blocks.yml");
		List<NodeBlock> nodeBlocks = config.getBlocks("blocks");
		
		for(NodeBlock nodeBlock : nodeBlocks) {
			if(nodeBlock.getBlock().getLocation().equals(b.getLocation())) {
				return nodeBlock;
			}
		}
		
		return null;
	}
	
}
