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

	protected Inventory inventory;
	protected String name;
	protected Player player;
	
	public Menu(Player player, String id, int size) {
		this.player = player;
		this.name = id;
		this.inventory = Bukkit.createInventory(player, size, Messenger.color("&8✯" + id));
		
	}
	
	public Menu(Inventory inventory) {
		this.inventory = inventory;
		this.player = (Player) inventory.getHolder();
		this.name = Messenger.uncolor(player.getOpenInventory().getTitle().replace("✯", ""));
	}
	
	
	
	
	public void setButton(ItemStack item, int slot) {
		ItemNBTEditor.addTag(item, "button");
		setElement(item, slot);
	}
	
	public void setButton(Material mat, String name, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		setButton(item, slot);
	}
	
	public void setButton(Material mat, String name, String lore, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		setButton(item, slot);
	}
	
	public void setButton(Material mat, String name, String lore, String button_id, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		ItemNBTEditor.addTag(item, button_id);
		setButton(item, slot);
	}
	
	public void setElement(ItemStack item, int slot) {
		ItemNBTEditor.addTag(item, "element");
		inventory.setItem(slot, item);
		player.updateInventory();
		
	}
	
	public void setElement(Material mat, String name, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		setElement(item, slot);
	}
	
	public void setElement(Material mat, String name, String lore, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		setElement(item, slot);
	}
	
	public void setElement(Material mat, String name, String lore, String element_id, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		ItemNBTEditor.addTag(item, element_id);
		setElement(item, slot);
	}
	
	public void fill(Material mat) {
		for(int i = 0; i < inventory.getSize(); i++) if(inventory.getItem(i) == null) setElement(mat, " ", i);
		player.updateInventory();
	}
	
	public void fill(Material mat, String name, String lore) {
		for(int i = 0; i < inventory.getSize(); i++) if(inventory.getItem(i) == null) setElement(mat, name, lore, i);
		player.updateInventory();
	}
	
	
	
	
	
	public void open() {
		player.closeInventory();
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
	
	public String getName() {
		return this.name;
	}
	
}
