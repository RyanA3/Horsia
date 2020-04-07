package me.felnstaren.horsia.function.horse.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.function.horse.HorseManager;
import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class HorseDeathListener implements Listener {

	private HorseManager horse_manager;
	
	public HorseDeathListener(HorseManager horse_manager) {
		this.horse_manager = horse_manager;
	}
	
	
	
	@EventHandler
	public void onHorseDeath(EntityDeathEvent event) {
		Logger.log(Level.DEBUG, "Handle Death");
		if(event.getEntity().getType() != EntityType.HORSE) return;
		Logger.log(Level.DEBUG, "Is Horse");
		Horse h = (Horse) event.getEntity();
		if(h.getOwner() == null) return;
		Logger.log(Level.DEBUG, "Has Owner");
		Player p = (Player) h.getOwner();
		DataHorse horse = new DataHorse(h);
		DataPlayer player = new DataPlayer(p.getName());
		
		if(!player.hasHorse(horse)) {
			Logger.log(Level.WARNING, "HANDLE DEATH > Horse-Owner mismatch");
			return;
		}
		Logger.log(Level.DEBUG, "Configs match realtime");
		
		h.getInventory().clear();
		horse.update(h);
		
		if(horse_manager.isDespawning(horse)) horse_manager.cancelDespawn(horse);
		player.setHorse(horse);
		player.saveHorses();
	}
	
}
