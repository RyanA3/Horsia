package me.felnstaren.horsia.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.ui.chat.ChatRequest;
import me.felnstaren.horsia.util.ui.chat.ChatResponseHandler;
import net.md_5.bungee.api.ChatColor;

public class HorseClaimCommand implements CommandExecutor {
	
	private ChatResponseHandler chat_request_manager;
	
	public HorseClaimCommand(ChatResponseHandler chat_request_manager) {
		this.chat_request_manager = chat_request_manager;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("horsia.claim")) {
			sender.sendMessage(Messenger.permission("horsia.claim"));
			return true;
		}
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Messenger.color("&cOnly players can use this command!"));
			return true;
		}

		Player p = (Player) sender;
		Entity mount = p.getVehicle();
		
		if(p.getVehicle() == null || !(mount instanceof Horse)) {
			p.sendMessage(Messenger.color("&cPlease mount the horse you wish to claim"));
			return true;
		}
		
		Horse h = (Horse) mount;
		DataHorse horse = new DataHorse(h);
		DataPlayer player = new DataPlayer(p.getName());
		if(h.getCustomName() != null && !player.hasHorse(horse)) {
			p.sendMessage(ChatColor.GRAY + "This is not your horse!");
			return true;
		}
		
		if(h.getCustomName() != null && player.hasHorse(horse)) {
			p.sendMessage(Messenger.color("&cThis horse is already in your stable"));
			return true;
		}
		
		p.sendMessage(Messenger.color("&aWhat would you like to name your horse..."));
		chat_request_manager.addToRequests(new ChatRequest(p, "claim_horse_rename"));
		
		return true;
	}

}
