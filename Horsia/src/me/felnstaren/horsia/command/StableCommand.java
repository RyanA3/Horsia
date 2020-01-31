package me.felnstaren.horsia.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.ui.menu.StableMenu;

public class StableCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("horsia.stable")) {
			sender.sendMessage(Messenger.permission("horsia.stable"));
			return true;
		}
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Messenger.color("&cOnly players can use this command!"));
			return true;
		}
		
		Player player = (Player) sender;
		StableMenu stable = new StableMenu(player);
		stable.open();
		
		return true;
	}
	
}
