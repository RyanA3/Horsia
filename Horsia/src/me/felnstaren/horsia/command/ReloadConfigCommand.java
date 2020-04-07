package me.felnstaren.horsia.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.horsia.config.Loader;
import me.felnstaren.horsia.config.Options;
import me.felnstaren.horsia.util.Messenger;

public class ReloadConfigCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("horsia.reload")) {
			sender.sendMessage(Messenger.color("&cYou do not have permission to &7horsia.reload&c!"));
			return true;
		}
		
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		
		sender.sendMessage(Messenger.color("&7&oReloaded the config file"));
		
		return true;
	}

}
