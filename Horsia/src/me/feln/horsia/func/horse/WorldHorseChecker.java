package me.feln.horsia.func.horse;

import org.bukkit.World;
import org.bukkit.entity.Horse;

import me.feln.horsia.config.DataHorse;
import me.feln.horsia.config.Loader;
import me.feln.horsia.util.logger.Level;
import me.feln.horsia.util.logger.Logger;

public class WorldHorseChecker {

	public static boolean horseEntityExists(DataHorse horse) {
		for(World world : Loader.plugin.getServer().getWorlds()) 
			for(Horse c : world.getEntitiesByClass(Horse.class)) {
				int checkid = DataHorse.calcID(c.getJumpStrength());
				Logger.log(Level.DEBUG, "Checking entity horse id (" + checkid + ") against data horse id (" + horse.getID() + ")");
				if(checkid == horse.getID()) return true;
			}
		return false;
	}
	
	public static Horse getHorseEntity(DataHorse horse) {
		for(World world : Loader.plugin.getServer().getWorlds()) 
			for(Horse c : world.getEntitiesByClass(Horse.class)) {
				int checkid = DataHorse.calcID(c.getJumpStrength());
				Logger.log(Level.DEBUG, "Checking entity horse id (" + checkid + ") against data horse id (" + horse.getID() + ")");
				if(checkid == horse.getID()) return c;
			}
		return null;
	}
	
}
