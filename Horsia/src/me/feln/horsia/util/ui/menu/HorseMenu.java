package me.feln.horsia.util.ui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.feln.horsia.config.DataHorse;

public class HorseMenu extends Menu {

	public HorseMenu(Player player, DataHorse horse) {
		super(player, "&b" + horse.getName(), 27);
		
		setButton(Material.COMPASS, "&aCall Horse", "", "call_horse", 10);
		setButton(Material.ARROW, "&cBack", "", "open_stable", 26);
		setButton(Material.BARRIER, "&4Release Horse", "&cThis cannot be undone!", "release_horse", 16);
		setButton(Material.NAME_TAG, "&b&l" + horse.getName(), "&7Click to rename this horse", "rename_horse", 12);
		
		setElement(Material.KNOWLEDGE_BOOK, "&b" + horse.getName() + "&7's Stats; ", 18);
		setElement(Material.FIRE_CORAL_BLOCK, "&7Health", "&8" + horse.getHealth() + "/" + horse.getMaxHealth(), 19);
		setElement(Material.FEATHER, "&7Speed", "&8" + horse.getSpeed(), 20);
		setElement(Material.RABBIT_FOOT, "&7Jump", "&8" + horse.getJump(), 21);
	}
	
}
