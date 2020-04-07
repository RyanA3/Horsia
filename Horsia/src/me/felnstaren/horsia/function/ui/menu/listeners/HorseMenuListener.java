package me.felnstaren.horsia.function.ui.menu.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.function.horse.WorldHorseChecker;
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
		
		
		
		if(ItemNBTEditor.hasTag(clicked, "feed_golden_apple")) {
			DataHorse horse = player.getHorse(inventory);
			if(horse.getHealth() > 0) {
				p.sendMessage(Messenger.color("&e&oThis horse does not need to be revived"));
				return;
			}
			
			if(!p.getInventory().contains(Material.GOLDEN_APPLE)) {
				p.sendMessage(Messenger.color("&e&oYou do not have any golden apples"));
				return;
			}
			
			p.getInventory().removeItem(new ItemStack(Material.GOLDEN_APPLE, 1));
			p.updateInventory();
			
			horse.heal(5.0);
			player.setHorse(horse);
			player.saveHorses();
			p.closeInventory();
			HorseMenu hmenu = new HorseMenu(event.getMenu().getPlayer(), horse);
			hmenu.open();
		}
		
		if(ItemNBTEditor.hasTag(clicked, "feed_hay_bale")) {
			DataHorse horse = player.getHorse(inventory);
			if(horse.getHealth() <= 0) {
				p.sendMessage(Messenger.color("&e&oThis horse needs to be revived"));
				return;
			}
			
			if(!p.getInventory().contains(Material.HAY_BLOCK)) {
				p.sendMessage(Messenger.color("&e&oYou do not have any hay bales"));
				return;
			}
			
			if(WorldHorseChecker.horseEntityExists(horse)) {
				p.sendMessage(Messenger.color("&e&oUse your hand dumbass"));
				return;
			}
				
			p.getInventory().removeItem(new ItemStack(Material.HAY_BLOCK, 1));
			p.updateInventory();
			
			horse.heal(4.5);
			player.setHorse(horse);
			player.saveHorses();
			p.closeInventory();
			HorseMenu hmenu = new HorseMenu(event.getMenu().getPlayer(), horse);
			hmenu.open();
		}
		
		if(ItemNBTEditor.hasTag(clicked, "feed_hay")) {
			DataHorse horse = player.getHorse(inventory);
			if(horse.getHealth() <= 0) {
				p.sendMessage(Messenger.color("&e&oThis horse needs to be revived"));
				return;
			}
			
			if(!p.getInventory().contains(Material.WHEAT)) {
				p.sendMessage(Messenger.color("&e&oYou do not have any hay"));
				return;
			}
			
			if(WorldHorseChecker.horseEntityExists(horse)) {
				p.sendMessage(Messenger.color("&e&oUse your hand dumbass"));
				return;
			}
				
			p.getInventory().removeItem(new ItemStack(Material.WHEAT, 1));
			p.updateInventory();
			
			horse.heal(0.5);
			player.setHorse(horse);
			player.saveHorses();
			p.closeInventory();
			HorseMenu hmenu = new HorseMenu(event.getMenu().getPlayer(), horse);
			hmenu.open();
		}
		
	}

}
