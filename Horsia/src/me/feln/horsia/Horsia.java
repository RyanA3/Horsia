package me.feln.horsia;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.feln.horsia.command.CallCommand;
import me.feln.horsia.config.Loader;
import me.feln.horsia.config.Options;
import me.feln.horsia.func.listeners.Test;

public class Horsia extends JavaPlugin {

	public void onEnable() {
		//Config init
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		
		//Events
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new Test(), this);
		
		//Commands
		this.getCommand("call").setExecutor(new CallCommand());
	}
	
	public void onDisable() {
		
	}
	
}
