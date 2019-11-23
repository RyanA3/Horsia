package me.feln.horsia.func.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.feln.horsia.config.DataHorse;
import me.feln.horsia.config.DataPlayer;
import me.feln.horsia.util.item.ItemEditor;
import me.feln.horsia.util.logger.Level;
import me.feln.horsia.util.logger.Logger;
import me.feln.horsia.util.ui.event.MenuButtonClickEvent;

public class Test implements Listener {

	@EventHandler
	public void onHorseClick(PlayerInteractEntityEvent event) {
		if(event.getRightClicked().getType() != EntityType.HORSE) return;
		Logger.log(Level.DEBUG, "Right clicked horse");
		
		Player p = event.getPlayer();
		Horse h = (Horse) event.getRightClicked();
		
		if(h.getCustomName() == null) return;
		
		DataPlayer dp = new DataPlayer(p.getName());
		DataHorse dh = new DataHorse(h);
		dp.setHorse(dh);
		dp.saveHorses();
		
		Logger.log(Level.DEBUG, "Created horse and stuff");
	}
	
	
	
	@EventHandler
	public void onMenuButton(MenuButtonClickEvent event) {
		Logger.log(Level.DEBUG, event.getMenu().getPlayer().getName() + " clicked button " + ItemEditor.getName(event.getClicked()));
	}
	
}
