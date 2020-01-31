package me.felnstaren.horsia.function.horse;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class HorseDespawn {

	private DataPlayer owner;
	private DataHorse horse;
	
	public HorseDespawn(DataPlayer owner, DataHorse horse) {
		this.owner = owner;
		this.horse = horse;
	}
	
	
	
	public void complete() {
		owner.setHorse(horse);
		owner.saveHorses();
		
		if(WorldHorseChecker.horseEntityExists(horse))
			WorldHorseChecker.getHorseEntity(horse).remove();
		else
			Logger.log(Level.WARNING, "Failed to find horse " + horse.getName());
	}
	
	
	
	public DataPlayer getOwner() {
		return owner;
	}
	
	public DataHorse getHorse() {
		return horse;
	}
	
}
