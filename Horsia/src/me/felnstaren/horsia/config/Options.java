package me.felnstaren.horsia.config;

import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class Options {
	
	public static int call_time, despawn_time, max_horses = -1;

	public static void load(YamlConfiguration config) {
		Logger.log(Level.DEBUG, "Loading config options from file; " + config.getCurrentPath());
		
		call_time = config.getInt("call-time");
		despawn_time = config.getInt("despawn-time");
		max_horses = config.getInt("max-horses");
		
		Logger.logger_priority = Level.valueOf(config.getString("logger-priority"));
	}
	
}
