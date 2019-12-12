package me.feln.horsia.func.horse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Horse;
import org.bukkit.scheduler.BukkitRunnable;

import me.feln.horsia.config.DataHorse;
import me.feln.horsia.config.Loader;
import me.feln.horsia.util.logger.Level;
import me.feln.horsia.util.logger.Logger;

public class HorseDespawner {

	private HashMap<Horse, Integer> despawn = new HashMap<Horse, Integer>();
	private List<Horse> remove = new ArrayList<Horse>();
	
	public HorseDespawner() {
		new BukkitRunnable() {
			public void run() {
				if(!remove.isEmpty()) {
					for(Horse horse : remove) despawn.remove(horse);
					remove.clear();
				}
				
				if(!despawn.isEmpty()) {
					Logger.log(Level.DEBUG, "Decrementing time on horse despawn (" + despawn.size() + ")");
					for(Horse horse : despawn.keySet()) {
						despawn.put(horse, despawn.get(horse) - 1);
						if(despawn.get(horse) <= 0) {
							remove.add(horse);
							horse.remove();
						}
					}
				}
			}
		}.runTaskTimer(Loader.plugin, 20, 20);
	}
	
	
	
	public void despawnHorse(Horse horse) {
		horse.remove();
	}
	
	public void despawnHorse(Horse horse, int delay) {
		if(isDespawning(horse)) return;
		if(delay > 0) despawn.put(horse, delay);
		else despawnHorse(horse);
	}
	
	public void cancelDespawn(Horse horse) {
		if(!isDespawning(horse)) return;
		if(despawn.isEmpty()) return;
		for(Horse check : despawn.keySet()) {
			DataHorse dcheck = new DataHorse(check);
			DataHorse dhorse = new DataHorse(horse);
			if(dcheck.matches(dhorse)) remove.add(check);
		}
	}
	
	public boolean isDespawning(Horse horse) {
		if(despawn.isEmpty()) return false;
		for(Horse check : despawn.keySet()) {
			DataHorse dcheck = new DataHorse(check);
			DataHorse dhorse = new DataHorse(horse);
			if(dcheck.matches(dhorse)) return true;
		}
		
		return false;
	}
	
}
