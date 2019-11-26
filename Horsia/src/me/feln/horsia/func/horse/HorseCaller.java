package me.feln.horsia.func.horse;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import me.feln.horsia.config.DataHorse;
import me.feln.horsia.config.Loader;
import me.feln.horsia.util.logger.Level;
import me.feln.horsia.util.logger.Logger;

public class HorseCaller {

	private List<HorseCall> calls = new ArrayList<HorseCall>();
	private List<HorseCall> queue_add = new ArrayList<HorseCall>();
	private List<HorseCall> queue_remove = new ArrayList<HorseCall>();
	
	public HorseCaller() {
		new BukkitRunnable() {
			public void run() {
				if(!queue_add.isEmpty()) {
					calls.addAll(queue_add);
					queue_add.clear();
				}
				
				if(!calls.isEmpty()) {
					Logger.log(Level.DEBUG, "Decrementing time on horse calls (" + calls.size() + ")");
					
					for(HorseCall call : calls) {
						call.setWait(call.getWait() - 1, true);
						if(call.isComplete()) queue_remove.add(call);
					}
				}
				
				if(!queue_remove.isEmpty()) {
					calls.removeAll(queue_remove);
					queue_remove.clear();
				}
			}
		}.runTaskTimer(Loader.plugin, 20, 20);
	}
	
	
	
	public void callHorse(DataHorse horse, Location location) {
		horse.summon(location);
	}
	
	public void callHorse(DataHorse horse, Location location, int delay) {
		if(isCalled(horse)) return;
		if(delay > 0) queue_add.add(new HorseCall(horse, location, delay));
		else callHorse(horse, location);
	}
	
	public void cancelCall(DataHorse horse) {
		if(!isCalled(horse)) return;
		if(!calls.isEmpty()) {
			for(HorseCall call : calls) 
				if(call.getHorse().matches(horse)) queue_remove.add(call);
		}
	}
	
	public boolean isCalled(DataHorse horse) {
		if(calls.isEmpty()) return false;
		for(HorseCall call : calls) if(call.getHorse().matches(horse)) return true;
		if(queue_add.isEmpty()) return false;
		for(HorseCall call : queue_add) if(call.getHorse().matches(horse)) return true;
		return false;
	}
	
}
