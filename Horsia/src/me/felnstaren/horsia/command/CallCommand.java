package me.felnstaren.horsia.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.config.Options;
import me.felnstaren.horsia.function.horse.HorseManager;
import me.felnstaren.horsia.function.horse.WorldHorseChecker;
import me.felnstaren.horsia.util.Messenger;

public class CallCommand implements CommandExecutor {
	
	private HorseManager hman;
	
	public CallCommand(HorseManager hman) {
		this.hman = hman;
	}
	
	

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
		Player p = (Player) sender;
		
		if(player.getHorseCount() <= 0) {
			sender.sendMessage(Messenger.color("&cYou don't have any horses!"));
			return true;
		}
		
		DataHorse horse = null;
		
		if(args.length == 0) horse = player.getHorses().get(0);
		else if(player.hasHorse(args[0])) horse = player.getHorse(args[0]);
		else sender.sendMessage(Messenger.color("&7" + args[0] + " &cis not one of your horses"));
		if(horse == null) return true;
		
		if(hman.isCalled(horse)) {
			sender.sendMessage(Messenger.color("&cThis horse is already on it's way"));
			return true;
		}
		
		if(WorldHorseChecker.horseEntityExists(horse)) {
			sender.sendMessage(Messenger.color("&cThis horse is not in the stable, and cannot be called"));
			return true;
		}
		
		if(horse.getHealth() <= 0.0) {
			sender.sendMessage(Messenger.color("&cThis horse is greatly injured and needs time to heal"));
			return true;
		}
		
		sender.sendMessage(Messenger.color("&aYou've called your horse! &7(" + horse.getName() + "&7)"));
		
		hman.call(player, horse, p.getLocation(), Options.call_time);

		return true;
	}
	
}
