package me.feln.horsia.func.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

import me.feln.horsia.config.DataHorse;
import me.feln.horsia.config.DataPlayer;
import me.feln.horsia.config.Options;
import me.feln.horsia.func.horse.HorseManager;
import me.feln.horsia.util.logger.Level;
import me.feln.horsia.util.logger.Logger;

public class HorseDismountListener implements Listener {

	private HorseManager hman;
	
	public HorseDismountListener(HorseManager hman) {
		this.hman = hman;
	}
	
	
	
	@EventHandler
	public void onDismount(EntityDismountEvent event) {
		Logger.log(Level.DEBUG, "Entity dismounted");
		if(!(event.getEntity() instanceof Player)) return;
		if(!(event.getDismounted() instanceof Horse)) return;
		Horse h = (Horse) event.getDismounted();
		Player p = (Player) event.getEntity();
		DataPlayer player = new DataPlayer(p.getName());
		
		if(!h.isAdult()) return;
		if(h.getCustomName() == null) return;

		DataHorse horse = new DataHorse(h);
		if(!player.hasHorse(horse)) return;
		if(hman.isDespawning(horse)) return;
		
		hman.despawn(player, horse, Options.despawn_time);
		Logger.log(Level.DEBUG, "Queuing despawn of horse " + horse.getName());
	}
	
}
