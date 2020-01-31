package me.felnstaren.horsia;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.horsia.command.CallCommand;
import me.felnstaren.horsia.command.HorseClaimCommand;
import me.felnstaren.horsia.command.StableCommand;
import me.felnstaren.horsia.config.Loader;
import me.felnstaren.horsia.config.Options;
import me.felnstaren.horsia.function.horse.HorseManager;
import me.felnstaren.horsia.function.horse.listeners.HorseDismountListener;
import me.felnstaren.horsia.function.horse.listeners.HorseMountListener;
import me.felnstaren.horsia.function.horse.listeners.HorseRenameListener;
import me.felnstaren.horsia.function.horse.listeners.HorseTameListener;
import me.felnstaren.horsia.function.ui.chat.listeners.ChatRequestCancelListener;
import me.felnstaren.horsia.function.ui.chat.listeners.ChatRequestTimeoutListener;
import me.felnstaren.horsia.function.ui.chat.listeners.ClaimHorseRenameListener;
import me.felnstaren.horsia.function.ui.menu.listeners.HorseMenuListener;
import me.felnstaren.horsia.util.ui.chat.ChatResponseHandler;
import me.felnstaren.horsia.util.ui.menu.MenuEventTrigger;

public class Horsia extends JavaPlugin {

	public void onEnable() {
		//Config init
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		
		//Runnables & Managers
		HorseManager hman = new HorseManager();
		ChatResponseHandler crman = new ChatResponseHandler();
		
		//Events
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(crman, this);
		pm.registerEvents(new HorseMountListener(hman), this);
		pm.registerEvents(new HorseDismountListener(hman), this);
		pm.registerEvents(new MenuEventTrigger(), this);
		pm.registerEvents(new HorseTameListener(), this);
		pm.registerEvents(new HorseRenameListener(), this);
		pm.registerEvents(new ChatRequestTimeoutListener(), this);
		pm.registerEvents(new ChatRequestCancelListener(), this);
		pm.registerEvents(new ClaimHorseRenameListener(), this);
		pm.registerEvents(new HorseMenuListener(), this);
		
		//Commands
		this.getCommand("call").setExecutor(new CallCommand(hman));
		this.getCommand("stable").setExecutor(new StableCommand());
		this.getCommand("claimhorse").setExecutor(new HorseClaimCommand(crman));
	}
	
	public void onDisable() {
		
	}
	
}
