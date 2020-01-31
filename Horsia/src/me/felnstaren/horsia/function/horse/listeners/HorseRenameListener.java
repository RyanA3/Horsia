package me.felnstaren.horsia.function.horse.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.item.ItemEditor;
import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class HorseRenameListener implements Listener {

	@EventHandler
	public void onRenameAttempt(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		Entity e = event.getRightClicked();
		
		if(!(e instanceof Horse)) return;

		ItemStack item = null;
		if(event.getHand() == EquipmentSlot.OFF_HAND) item = p.getInventory().getItemInOffHand();
		else item = p.getInventory().getItemInMainHand();
		
		if(item == null || item.getType() != Material.NAME_TAG ) return;
		
		
		Horse h = (Horse) e;
		DataPlayer player = new DataPlayer(p.getName());
		DataHorse horse = new DataHorse(h);
			
		if(h.getCustomName() == null || !player.hasHorse(horse)) {
			p.sendMessage(Messenger.color("&cClaim this horse first to rename it"));
			event.setCancelled(true);
			return;
		}
		
		String name = h.getCustomName();
		String rename = ItemEditor.getName(item);
		
		if(rename == null || rename.equals("Name Tag") || rename.equals("")) return;
		
		if(player.hasHorse(rename)) {
			p.sendMessage(Messenger.color("&cYou already have a horse with this name!"));
			event.setCancelled(true);
		}
		
		player.renameHorse(horse, rename);
		player.saveHorses();
		Logger.log(Level.DEBUG, "Player " + p.getName() + " renamed horse from " + name + " to " + rename + "!");
	}
	
}
