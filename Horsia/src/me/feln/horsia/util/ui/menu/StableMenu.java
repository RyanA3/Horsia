package me.feln.horsia.util.ui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.feln.horsia.config.DataPlayer;
import me.feln.horsia.config.Options;

public class StableMenu extends Menu {

	public StableMenu(Player player) {
		super(player, "&6Stable", 27);
		DataPlayer dp = new DataPlayer(player.getName());
		
		setElement(Material.HAY_BLOCK, "&aThis is your stable", "&7This is where all of your /n/&7horses go. You can see all /n/&7of them here, and call them.", 0);
		
		int start_slot = (27 / 2) - (Options.max_horses / 2);
		for(int i = 0; i < dp.getHorseCount(); i++) 
			setButton(Material.LEATHER_HORSE_ARMOR, "&b" + dp.getHorse(i).getName(), dp.getHorse(i).toString(), "horse_button", i + start_slot);
	}
	
}
