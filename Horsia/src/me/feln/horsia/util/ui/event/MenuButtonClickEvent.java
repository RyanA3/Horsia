package me.feln.horsia.util.ui.event;

import org.bukkit.inventory.ItemStack;

import me.feln.horsia.util.ui.menu.Menu;

public class MenuButtonClickEvent extends MenuClickEvent {

	public MenuButtonClickEvent(Menu menu, ItemStack button) {
		super(menu, button);
	}

}
