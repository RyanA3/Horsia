package me.feln.horsia.util.ui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.feln.horsia.util.Messenger;
import me.feln.horsia.util.item.ItemEditor;
import me.feln.horsia.util.item.ItemNBTEditor;

public class Menu {

	private Inventory inventory;
	private Player player;
	
	public Menu(Player player, String name, int size) {
		this.player = player;
		this.inventory = Bukkit.createInventory(player, size, Messenger.color("&8✯" + name));
	}
	
	public Menu(Inventory inventory) {
		this.inventory = inventory;
		this.player = (Player) inventory.getHolder();
	}
	
	
	
	
	public void setButton(ItemStack item, int slot) {
		ItemNBTEditor.addTag(item, "button");
		inventory.setItem(slot, item);
		player.updateInventory();
	}
	
	public void setButton(Material mat, String name, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		ItemNBTEditor.addTag(item, "button");
		setButton(item, slot);
	}
	
	public void setButton(Material mat, String name, String lore, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		ItemNBTEditor.addTag(item, "button");
		setButton(item, slot);
	}
	
	public void fill(Material mat) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, "");
		ItemNBTEditor.addTag(item, "element");
		for(int i = 0; i < inventory.getSize(); i++) if(inventory.getItem(i) == null) inventory.setItem(i, item);
		player.updateInventory();
	}
	
	
	
	
	
	public void open() {
		player.openInventory(inventory);
	}
	
	public void close() {
		player.closeInventory();
	}
	
	
	
	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
}
