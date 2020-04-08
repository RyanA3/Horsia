package me.felnstaren.horsia.function.ui.chat.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.config.Options;
import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.ui.chat.event.ChatResponseEvent;

public class ClaimHorseRenameListener implements Listener {

	@EventHandler
	public void onInput(ChatResponseEvent event) {
		if(!event.getID().equals("claim_horse_rename")) return;
		
		Player p = event.getPlayer();
		DataPlayer player = new DataPlayer(p.getName());
		
		Entity e = p.getVehicle();
		
		if(!(e instanceof Horse)) {
			p.sendMessage(Messenger.color("&cYou must be on the horse you wish to rename"));
			return;
		}
		
		Horse h = (Horse) e;
		
		if(player.hasHorse(event.getResponse())) {
			p.sendMessage(Messenger.color("&cYou already have a horse with this name!"));
			return;
		}
		
		if(event.getResponse().length() < 4) {
			p.sendMessage(Messenger.color("&cThis name is too short"));
			return;
		}
		
		if(player.getHorseCount() >= Options.max_horses) {
			p.sendMessage(Messenger.color("&cThere is no room left in your stable"));
			return;
		}
		
		DataHorse horse = new DataHorse(h);
		if(h.getOwner() != null && !h.getOwner().equals(p)) {
			p.sendMessage(Messenger.color("&cThis horse already has an owner"));
			return;
		}
		
		h.setCustomName(Messenger.color(event.getResponse()));
		h.setOwner(p);
		horse.update(h);
		p.sendMessage(Messenger.color("&aYou've added &7" + event.getResponse() + " &ato your stable"));
		player.setHorse(horse);
		player.saveHorses();
		
	}
	
}
