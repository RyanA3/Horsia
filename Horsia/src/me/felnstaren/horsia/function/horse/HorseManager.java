package me.felnstaren.horsia.function.horse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.config.Loader;
import me.felnstaren.horsia.config.Options;
import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class HorseManager {

	private HashMap<HorseDespawn, Integer> despawn = new HashMap<HorseDespawn, Integer>();
	private HashMap<HorseCall, Integer> spawn = new HashMap<HorseCall, Integer>();
	private List<HorseDespawn> fin_despawn = new ArrayList<HorseDespawn>();
	private List<HorseCall> fin_spawn = new ArrayList<HorseCall>();
	
	public HorseManager() {
		new BukkitRunnable() {
			public void run() {
				//Decrement timers, queue horses to spawn/despawn
				for(HorseDespawn h : despawn.keySet()) {
					despawn.put(h, despawn.get(h) - 1);
					if(despawn.get(h) <= 0) fin_despawn.add(h);
				}
				for(HorseCall h : spawn.keySet()) {
					spawn.put(h, spawn.get(h) - 1);
					if(spawn.get(h) <= 0) fin_spawn.add(h);
				}
				
				//Spawn/Despawn horses in queue
				if(!fin_despawn.isEmpty()) despawnHorses();
				if(!fin_spawn.isEmpty()) spawnHorses();
			}
		}.runTaskTimer(Loader.plugin, 100, 20);
	}
	
	
	
	private void despawnHorses() {
		for(HorseDespawn leave : fin_despawn) {
			leave.complete();
			despawn.remove(leave);
		}
		
		fin_despawn.clear();
	}
	
	private void spawnHorses() {
		for(HorseCall call : fin_spawn) {
			spawn.remove(call);
			call.complete();
			despawn(call.getCaller(), call.getHorse(), Options.despawn_time);
		}
		
		fin_spawn.clear();
	}
	
	
	
	public void despawn(DataPlayer owner, DataHorse horse, Integer wait) {
		Logger.log(Level.DEBUG, "Despawning horse");
		HorseDespawn leave = new HorseDespawn(owner, horse);
		if(despawn.containsKey(leave)) return;
		if(isDespawning(horse)) return;
		despawn.put(leave, wait);
	}
	
	public void call(DataPlayer caller, DataHorse horse, Location location, Integer wait) {
		Logger.log(Level.DEBUG, "Called horse");
		HorseCall call = new HorseCall(caller, horse, location);
		if(spawn.containsKey(call)) return;
		if(isCalled(call.getHorse())) return;
		spawn.put(call, wait);
	}
	
	public void cancelDespawn(DataHorse horse) {
		List<HorseDespawn> remove = new ArrayList<HorseDespawn>();
		
		for(HorseDespawn c : despawn.keySet()) 
			if(c.getHorse().matches(horse)) remove.add(c);
		
		if(remove.isEmpty()) return;
		for(HorseDespawn c : remove)
			despawn.remove(c);
	}
	
	public void cancelCall(DataHorse horse) {
		List<HorseCall> remove = new ArrayList<HorseCall>();
		
		for(HorseCall c : spawn.keySet()) 
			if(c.getHorse().matches(horse)) remove.add(c);
		
		if(remove.isEmpty()) return;
		for(HorseCall c : remove)
			spawn.remove(c);
	}
	
	public boolean isCalled(DataHorse horse) {
		for(HorseCall c : spawn.keySet()) 
			if(c.getHorse().matches(horse)) return true;
		
		return false;
	}
	
	public boolean isDespawning(DataHorse horse) {
		for(HorseDespawn c : despawn.keySet()) 
			if(c.getHorse().matches(horse)) return true;
		
		return false;
	}
	
}
