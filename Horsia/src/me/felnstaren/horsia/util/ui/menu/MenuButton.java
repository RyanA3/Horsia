package me.felnstaren.horsia.util.ui.menu;

import org.bukkit.inventory.ItemStack;

public abstract class MenuButton {

	private ItemStack item;
	
	public MenuButton(ItemStack item) {
		this.item = item;
	}
	
	public abstract void onPress(Menu menu);
	
	public ItemStack getItem() {
		return item;
	}
	
}
