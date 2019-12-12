package me.feln.horsia.func.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityMountEvent;

import me.feln.horsia.config.DataHorse;
import me.feln.horsia.config.DataPlayer;
import me.feln.horsia.func.horse.HorseManager;
import me.feln.horsia.util.logger.Level;
import me.feln.horsia.util.logger.Logger;

public class HorseMountListener implements Listener {

	private HorseManager hman;
	
	public HorseMountListener(HorseManager hman) {
		this.hman = hman;
	}
	
	
	
	@EventHandler
	public void onMount(EntityMountEvent event) {
		Logger.log(Level.DEBUG, "Entity mounted");
		if(!(event.getEntity() instanceof Player)) return;
		if(!(event.getMount() instanceof Horse)) return;
		Horse h = (Horse) event.getMount();
		DataPlayer player = new DataPlayer(event.getEntity().getName());
		
		if(!h.isAdult()) return;
		if(h.getCustomName() == null) return;
		
		DataHorse horse = new DataHorse(h);
		if(!player.hasHorse(horse)) return;
		if(!hman.isDespawning(horse)) return;
		
		hman.cancelDespawn(horse);
		Logger.log(Level.DEBUG, "Cancelled despawn of horse; " + horse.getName());
	}
	
}
