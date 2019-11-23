package me.feln.horsia.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.feln.horsia.util.Messenger;
import me.feln.horsia.util.ui.menu.Menu;

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
		Menu stable = new Menu(player, "&6Stable", 27);
		stable.setButton(Material.IRON_HORSE_ARMOR, "Horse", "Insert /n/ stats /n/ here", 14);
		stable.open();
		
		return true;
	}
	
}
