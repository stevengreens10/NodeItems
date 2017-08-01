package me.NodeDigital.NodeItems.block;

import org.bukkit.block.Block;

public class NodeBlock {

	private Block block;
	private BlockType type;
	private int ID;
	
	public NodeBlock(Block block, BlockType type, int ID) {
		this.block = block;
		this.type = type;
		this.ID = ID;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public BlockType getType() {
		return type;
	}
	
	public int getID() {
		return ID;
	}
	
}
