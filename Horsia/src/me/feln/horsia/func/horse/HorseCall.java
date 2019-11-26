package me.feln.horsia.func.horse;

import org.bukkit.Location;

import me.feln.horsia.config.DataHorse;

public class HorseCall {
	
	private DataHorse horse;
	private Location location;
	private int wait;

	public HorseCall(DataHorse horse, Location location, int wait) {
		this.horse = horse;
		this.location = location;
		this.wait = wait;
	}
	
	
	
	public boolean horseMatches(HorseCall check) {
		return
				check.getHorse().matches(horse);
	}
	
	public void complete() {
		horse.summon(location);
	}
	
	
	
	public void setWait(int wait) {
		this.wait = wait;
	}
	
	public void setWait(int wait, boolean auto_complete) {
		this.wait = wait;
		if(auto_complete && wait <= 0) complete();
	}
	
	public boolean isComplete() {
		return wait <= 0;
	}
	
	public DataHorse getHorse() {
		return this.horse;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public int getWait() {
		return this.wait;
	}
	
}
