package me.NodeDigital.NodeItems.block;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class NodeBlock {

	private Block block;
	private BlockType type;
	private int ID;
	private Inventory inventory = null;
	
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
	
	public Inventory createInventory(InventoryHolder owner, int size, String name) {
		inventory = Bukkit.createInventory(owner, size, name);
		return inventory;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void saveInventory() {
		BlockStorage.saveInventory(ID, inventory);
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
