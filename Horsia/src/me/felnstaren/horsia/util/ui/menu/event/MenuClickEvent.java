package me.felnstaren.horsia.util.ui.menu.event;

import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.util.ui.menu.Menu;

public class MenuClickEvent extends MenuEvent {

	private ItemStack clicked;
	
	public MenuClickEvent(Menu menu, ItemStack clicked) {
		super(menu);
		this.clicked = clicked;
	}
	
	
	
	public ItemStack getClicked() {
		return this.clicked;
	}
	
}
