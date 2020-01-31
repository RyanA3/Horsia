package me.felnstaren.horsia.util.ui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.item.ItemEditor;
import me.felnstaren.horsia.util.item.ItemNBTEditor;

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
	
	public void setButton(ItemStack item, String name, int slot) {
		ItemEditor.setName(item, Messenger.color(name));
		setButton(item, slot);
	}
	
	public void setButton(Material material, String name, int slot) {
		setButton(new ItemStack(material), name, slot);
	}
	
	public void setButton(ItemStack item, String name, String lore, int slot) {
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		setButton(item, slot);
	}
	
	public void setButton(Material material, String name, String lore, int slot) {
		setButton(new ItemStack(material), name, lore, slot);
	}
	
	public void setButton(ItemStack item, String name, String lore, String button_id, int slot) {
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		ItemNBTEditor.addTag(item, button_id);
		setButton(item, slot);
	}
	
	public void setButton(Material material, String name, String lore, String button_id, int slot) {
		setButton(new ItemStack(material), name, lore, button_id, slot);
	}
	
	
	
	public void setElement(ItemStack item, int slot) {
		ItemNBTEditor.addTag(item, "element");
		inventory.setItem(slot, item);
		player.updateInventory();
		
	}
	
	public void setElement(ItemStack item, String name, int slot) {
		ItemEditor.setName(item, Messenger.color(name));
		setElement(item, slot);
	}
	
	public void setElement(Material material, String name, int slot) {
		setElement(new ItemStack(material), name, slot);
	}
	
	public void setElement(ItemStack item, String name, String lore, int slot) {
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		setElement(item, slot);
	}
	
	public void setElement(Material material, String name, String lore, int slot) {
		setElement(new ItemStack(material), name, lore, slot);
	}
	
	public void setElement(ItemStack item, String name, String lore, String element_id, int slot) {
		ItemEditor.setName(item, Messenger.color(name));
		ItemEditor.setLore(item, Messenger.color(lore));
		ItemNBTEditor.addTag(item, element_id);
		setElement(item, slot);
	}
	
	public void setElement(Material material, String name, String lore, String element_id, int slot) {
		setElement(new ItemStack(material), name, lore, element_id, slot);
	}
	
	
	
	public void fill(ItemStack item) {
		for(int i = 0; i < inventory.getSize(); i++) if(inventory.getItem(i) == null) setElement(item, " ", i);
		player.updateInventory();
	}
	
	public void fill(Material material) {
		fill(new ItemStack(material));
	}
	
	public void fill(ItemStack item, String name, String lore) {
		for(int i = 0; i < inventory.getSize(); i++) if(inventory.getItem(i) == null) setElement(item, name, lore, i);
		player.updateInventory();
	}
	
	public void fill(Material material, String name, String lore) {
		fill(new ItemStack(material), name, lore);
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
