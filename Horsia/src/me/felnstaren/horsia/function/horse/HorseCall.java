package me.felnstaren.horsia.function.horse;

import org.bukkit.Location;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;

public class HorseCall {
	
	private DataPlayer caller;
	private DataHorse horse;
	private Location location;

	public HorseCall(DataPlayer caller, DataHorse horse, Location location) {
		this.caller = caller;
		this.horse = horse;
		this.location = location;
	}
	
	
	
	public boolean horseMatches(HorseCall check) {
		return
				check.getHorse().matches(horse);
	}
	
	public void complete() {
		horse.summon(location);
	}
	
	
	
	public DataPlayer getCaller() {
		return this.caller;
	}
	
	public DataHorse getHorse() {
		return this.horse;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
}
