package me.felnstaren.horsia.util.ui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.config.DataHorse;
import me.felnstaren.horsia.config.DataPlayer;
import me.felnstaren.horsia.config.Options;

public class StableMenu extends Menu {

	public StableMenu(Player p) {
		super(p, "&6Stable", 27);
		DataPlayer player = new DataPlayer(p.getName());
		
		setElement(Material.HAY_BLOCK, "&aThis is your stable", "&7This is where all of your /n/&7horses go. You can see all /n/&7of them here, and call them.", 0);
		
		int start_slot = (27 / 2) - (Options.max_horses / 2);
		for(int i = 0; i < player.getHorseCount(); i++) {
			DataHorse horse = player.getHorse(i);
			
			ItemStack button;
			if(horse.hasArmor()) button = horse.getArmor();
			else if(horse.hasSaddle()) button = horse.getSaddle();
			else button = new ItemStack(Material.GOLDEN_CARROT);
			
			setButton(button, "&b" + horse.getName(), horse.toString(), "horse_button", i + start_slot);
		}
	}
	
}
