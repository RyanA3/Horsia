package me.felnstaren.horsia.util.ui.menu.event;

import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.util.ui.menu.Menu;

public class MenuButtonClickEvent extends MenuClickEvent {

	public MenuButtonClickEvent(Menu menu, ItemStack button) {
		super(menu, button);
	}

}
