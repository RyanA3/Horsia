package me.felnstaren.horsia.function.horse.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.config.Options;
import me.felnstaren.horsia.function.horse.HorseManager;

public class HorseDamageListener implements Listener {

	private HorseManager horse_manager;
	
	public HorseDamageListener(HorseManager horse_manager) {
		this.horse_manager = horse_manager;
	}
	
	
	
	@EventHandler
	public void onHorseDamage(EntityDamageEvent event) {
		if(event.getEntityType() != EntityType.HORSE) return;
		Horse h = (Horse) event.getEntity();
		if(h.getOwner() == null) return;
		
		DataPlayer player = new DataPlayer(h.getOwner().getName());
		DataHorse horse = new DataHorse(h);
		
		player.setHorse(horse);
		player.saveHorses();
		
		if(horse_manager.isDespawning(horse))
			horse_manager.cancelDespawn(horse);
		horse_manager.despawn(player, horse, Options.despawn_time);
	}
	
}
