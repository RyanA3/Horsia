package me.feln.horsia;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.feln.horsia.command.CallCommand;
import me.feln.horsia.command.StableCommand;
import me.feln.horsia.config.Loader;
import me.feln.horsia.config.Options;
import me.feln.horsia.func.horse.HorseCaller;
import me.feln.horsia.func.listeners.Test;
import me.feln.horsia.util.ui.menu.MenuEventTrigger;

public class Horsia extends JavaPlugin {

	public void onEnable() {
		//Config init
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		
		//Runnables & Managers
		HorseCaller caller = new HorseCaller();
		
		//Events
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new MenuEventTrigger(), this);
		pm.registerEvents(new Test(), this);
		
		//Commands
		this.getCommand("call").setExecutor(new CallCommand(caller));
		this.getCommand("stable").setExecutor(new StableCommand());
	}
	
	public void onDisable() {
		
	}
	
}
