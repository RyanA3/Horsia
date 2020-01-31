package me.felnstaren.horsia.function.horse.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityMountEvent;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.function.horse.HorseManager;
import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;
import net.md_5.bungee.api.ChatColor;

public class HorseMountListener implements Listener {

	private HorseManager hman;
	
	public HorseMountListener(HorseManager hman) {
		this.hman = hman;
	}
	
	
	
	@EventHandler
	public void onMount(EntityMountEvent event) {
		if(!(event.getEntity() instanceof Player)) return;
		if(!(event.getMount() instanceof Horse)) return;
		
		Horse h = (Horse) event.getMount();
		Player p = (Player) event.getEntity();
		DataPlayer player = new DataPlayer(p.getName());
		
		if(h.getCustomName() == null) return;
		
		DataHorse horse = new DataHorse(h);
		
		if(!player.hasHorse(horse)) {
			event.setCancelled(true);
			p.sendMessage(ChatColor.GRAY + "The horse kicks you off!");
			return;
		}
		
		
		if(hman.isDespawning(horse)) hman.cancelDespawn(horse);
		
		player.setHorse(horse);
		player.saveHorses();
		
		Logger.log(Level.DEBUG, "Cancelled despawn of & updated horse; " + horse.getName());
	}
	
}
