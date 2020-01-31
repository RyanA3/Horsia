package me.felnstaren.horsia.util.ui.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.item.ItemNBTEditor;
import me.felnstaren.horsia.util.ui.menu.event.MenuButtonClickEvent;
import me.felnstaren.horsia.util.ui.menu.event.MenuClickEvent;

public class MenuEventTrigger implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(!event.getView().getTitle().startsWith(Messenger.color("&8✯"))) return;
		event.setCancelled(true);
		if(event.getClickedInventory() == null) return;
		if(event.getCurrentItem() == null) return;
		if(!(event.getClickedInventory().getHolder() instanceof Player)) return;
		if(!event.getClickedInventory().equals(event.getView().getTopInventory())) return;
		
		ItemStack clicked = event.getCurrentItem();
		Menu menu = new Menu(event.getClickedInventory());
		if(ItemNBTEditor.hasTag(clicked, "button")) Bukkit.getPluginManager().callEvent(new MenuButtonClickEvent(menu, clicked));
		else Bukkit.getPluginManager().callEvent(new MenuClickEvent(menu, clicked));
	}
	
}
