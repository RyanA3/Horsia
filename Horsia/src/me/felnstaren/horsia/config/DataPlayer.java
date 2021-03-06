package me.felnstaren.horsia.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class DataPlayer {

	private YamlConfiguration data;
	private String path;
	private String player_name;
	
	private List<DataHorse> horses = new ArrayList<DataHorse>();
	
	
	
	public DataPlayer(String name) {
		Logger.log(Level.DEBUG, "Loading player with name " + name);
		this.player_name = name;
		this.path = "playerdata/" + name + ".yml";
		load(Loader.loadOrDefault(path, "default_player.yml"));
		save();
	}
	
	
	
	private void load(YamlConfiguration data) {
		this.data = data;
		
		//Load horses from config
		if(!data.isConfigurationSection("horse")) return;
		for(String name : data.getConfigurationSection("horse").getKeys(false)) {
			Logger.log(Level.DEBUG, "Loading horse; " + name);
			horses.add(new DataHorse(data.getConfigurationSection("horse").getConfigurationSection(name), player_name));
		}
	}
	
	
	
	private void saveHorse(DataHorse horse) {
		horse.saveData(data);
	}
	
	public void saveHorses() {
		if(horses.isEmpty()) return;
		for(DataHorse horse : horses) saveHorse(horse);
		save();
	}
	
	public void save() {
		Loader.save(data, path);
	}
	
	
	private void addHorse(DataHorse horse) {
		if(hasHorse(horse)) return;
		horses.add(horse);
	}
	
	//Will update a horse with properties from a new one, (new speed, jump, etc)
	public void setHorse(DataHorse horse) {
		if(hasHorse(horse)) {
			removeHorse(horse);
			addHorse(horse);
			
			Logger.log(Level.DEBUG, "Updating horse; " + horse.getName());
		} else {
			addHorse(horse);
			
			Logger.log(Level.DEBUG, "Adding a new horse; " + horse.getName());
		}

		Logger.log(Level.DEBUG, "Current horses of player; ");
		for(DataHorse h : horses) Logger.log(Level.DEBUG, h.getName());
	}
	
	public void removeHorse(DataHorse horse) {
		if(horses.isEmpty()) return;
		if(!hasHorse(horse)) return;
		List<DataHorse> remove = new ArrayList<DataHorse>();
		for(DataHorse h : horses) if(h.matches(horse)) remove.add(h);
		horses.removeAll(remove);
		
		data.set("horse." + horse.getName(), null);
		save();
	}
	
	public void renameHorse(DataHorse horse, String name) {
		if(hasHorse(name)) return;
		removeHorse(horse);
		horse.setName(name);
		setHorse(horse);
	}
	
	@Deprecated
	public void removeHorse(String name) {
		if(horses.isEmpty()) return;
		if(!hasHorse(name)) return;
		List<DataHorse> remove = new ArrayList<DataHorse>();
		for(DataHorse h : horses) if(h.getName().equals(name)) remove.add(h);
		horses.removeAll(remove);
	}
	
	public boolean hasHorse(DataHorse horse) {
		if(horses.isEmpty()) return false;
		for(DataHorse check : horses) {
			if(check.matches(horse)) return true;
			//if(check.isOwnedBy(player_name)) return true;
		}
		return false;
	}
	
	public boolean hasHorse(String name) {
		if(horses.isEmpty()) return false;
		for(DataHorse check : horses) {
			if(check.getName().equals(name)) return true;
			//if(check.isOwnedBy(player_name));
		}
		return false;
	}
	
	public DataHorse getHorse(String name) {
		if(horses.isEmpty()) return null;
		for(DataHorse check : horses) if(check.getName().equals(name)) return check;
		return null;
	}
	
	public DataHorse getHorse(int index) {
		if(horses.isEmpty()) return null;
		if(horses.size() < index + 1) return null;
		return horses.get(index);
	}
	
	public List<DataHorse> getHorses() {
		return this.horses;
	}
	
	public int getHorseCount() {
		return horses.size();
	}
	
	
	
	
	
	
	public YamlConfiguration getData() {
		return this.data;
	}
	
}
