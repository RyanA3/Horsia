package me.felnstaren.horsia.function.ui.menu.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.item.ItemEditor;
import me.felnstaren.horsia.util.item.ItemNBTEditor;
import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;
import me.felnstaren.horsia.util.ui.menu.HorseMenu;
import me.felnstaren.horsia.util.ui.menu.StableMenu;
import me.felnstaren.horsia.util.ui.menu.event.MenuButtonClickEvent;

public class HorseMenuListener implements Listener {
	
	@EventHandler
	public void onMenuButton(MenuButtonClickEvent event) {
		Logger.log(Level.DEBUG, event.getMenu().getPlayer().getName() + " clicked button " + ItemEditor.getName(event.getClicked()));
		Player p = event.getMenu().getPlayer();
		DataPlayer player = new DataPlayer(p.getName());
		ItemStack clicked = event.getClicked();
		String inventory = Messenger.uncolor(event.getMenu().getName());
		
		if(ItemNBTEditor.hasTag(clicked, "horse_button")) {
			DataHorse horse = player.getHorse(Messenger.uncolor(ItemEditor.getName(clicked)));
			HorseMenu hmenu = new HorseMenu(event.getMenu().getPlayer(), horse);
			hmenu.open();
		}
		
		if(ItemNBTEditor.hasTag(clicked, "open_stable")) {
			StableMenu stable = new StableMenu(p);
			stable.open();
		}
		
		if(ItemNBTEditor.hasTag(clicked, "release_horse")) {
			DataHorse horse = player.getHorse(inventory);
			player.removeHorse(horse);
			StableMenu stable = new StableMenu(p);
			stable.open();
		}
		
		if(ItemNBTEditor.hasTag(clicked, "call_horse")) {
			DataHorse horse = player.getHorse(inventory);
			p.chat("/call " + horse.getName());
			p.closeInventory();
		}
		
		if(ItemNBTEditor.hasTag(clicked, "rename_horse")) {
			p.sendMessage(Messenger.color("&c&oThis has not been implemented yet"));
		}
	}

}
