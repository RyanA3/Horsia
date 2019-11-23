package me.feln.horsia.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.feln.horsia.config.DataHorse;
import me.feln.horsia.config.DataPlayer;
import me.feln.horsia.util.Messenger;

public class CallCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("horsia.call")) {
			sender.sendMessage(Messenger.permission("horsia.call"));
			return true;
		}
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Messenger.color("&cOnly players can use this command!"));
			return true;
		}
		
		DataPlayer player = new DataPlayer(sender.getName());
		
		if(player.getHorseCount() <= 0) {
			sender.sendMessage(Messenger.color("&cYou don't have any horses!"));
			return true;
		}
		
		DataHorse horse = null;
		
		if(args.length == 0) horse = player.getHorses().get(0);
		else if(player.hasHorse(args[0])) horse = player.getHorse(args[0]);
		else sender.sendMessage(Messenger.color("&7" + args[0] + " &cis not one of your horses!"));
		if(horse == null) return true;
		
		sender.sendMessage(Messenger.color("&aYou've called your horse! &7(" + horse.getName() + "&7)"));
		
		
		
		horse.summon(((Player) sender).getLocation());
		
		
		
		return true;
	}
	
}
