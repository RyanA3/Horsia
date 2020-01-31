package me.felnstaren.horsia.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.horsia.util.IDerator;
import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.MyMath;
import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class DataHorse {
	
	private String name;
	private String owner;
	private ConfigurationSection data = null;
	private int id;
	
	private double speed, jump, health, max_health;
	private Style style;
	private Color color;
	
	private ItemStack armor_item, saddle_item;
	
	
	
	public DataHorse(ConfigurationSection data, String owner) {
		this.owner = owner;
		update(data);
	}
	
	public DataHorse(Horse horse) {
		update(horse);
	}
	
	public void update(ConfigurationSection data) {
		this.name = data.getName();
		this.data = data;
		
		Logger.log(Level.DEBUG, "Loading horse with name " + name);
		
		this.speed = data.getDouble("speed");
		this.jump = data.getDouble("jump");
		this.health = data.getDouble("health");
		this.max_health = data.getDouble("max_health");
		
		this.style = Style.valueOf(data.getString("style"));
		this.color = Color.valueOf(data.getString("color"));
		
		this.armor_item = data.getItemStack("armor_item");
		this.saddle_item = data.getItemStack("saddle_item");
		//this.armor = Material.valueOf(data.getString("armor", "AIR"));
		//this.saddle = Material.valueOf(data.getString("saddle", "AIR"));
		
		this.id = data.getInt("id");
	}
	
	public void update(Horse horse) {
		this.name = horse.getCustomName();
		this.data = null;
		
		this.max_health = MyMath.round(horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), 3);
		this.speed = MyMath.roundDown(horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue(), 3);
		this.jump = MyMath.roundDown(horse.getJumpStrength(), 3); //Round down to preserve id value
		this.health = MyMath.round(horse.getHealth(), 3);
		
		this.style = horse.getStyle();
		this.color = horse.getColor();
		
		if(horse.getInventory().getArmor() == null) this.armor_item = new ItemStack(Material.AIR);
		else this.armor_item = horse.getInventory().getArmor();
		if(horse.getInventory().getSaddle() == null) this.saddle_item = new ItemStack(Material.AIR);
		else this.saddle_item = horse.getInventory().getSaddle();
		
		this.id = IDerator.getID(horse.getJumpStrength());

		if(horse.getOwner() != null) this.owner = horse.getOwner().getName();
	}
	
	
	

	
	
	
	public Horse summon(Location location) {
		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
		
		horse.setCustomName(name);
		horse.setJumpStrength(IDerator.reverseID(id, jump)); //Put ID back into horse's jump height
		horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(max_health);
		horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
		horse.setHealth(health);
		horse.setStyle(style);
		horse.setColor(color);
		horse.getInventory().setArmor(armor_item);
		horse.getInventory().setSaddle(saddle_item);
		horse.setAdult();
		horse.setTamed(true);
		
		if(owner != null) horse.setOwner(Bukkit.getPlayerExact(owner));
		
		Logger.log(Level.DEBUG, "Summoned horse; " + horse.getCustomName());
		
		return horse;
	}
	
	
	
	public boolean matches(Horse horse) {
		return 
				name.equals(horse.getCustomName()) &&
				style.equals(horse.getStyle()) &&
				color.equals(horse.getColor()) &&
				id == IDerator.getID(horse.getJumpStrength());
	}
	
	public boolean matches(DataHorse horse) {
		Logger.log(Level.DEBUG, "Checking horse; " + id + " vs; " + horse.getID());
		
		return
				name.equals(horse.getName()) &&
				style.equals(horse.getStyle()) &&
				color.equals(horse.getColor()) &&
				id == horse.getID();
	}
	
	
	
	public void save() {
		if(data == null) {
			Logger.log(Level.WARNING, "Error, tried to save horse without a data file set.");
			return;
		}
		
		data.set("speed", speed);
		data.set("jump", jump);
		data.set("health", health);
		data.set("max_health", max_health);
		data.set("armor_item", armor_item);
		data.set("saddle_item", saddle_item);
		data.set("style", style.toString());
		data.set("color", color.toString());
		data.set("id", id);
	}
	
	public void saveData(YamlConfiguration config) {
		config.set("horse." + name + ".id", id);
		this.data = config.getConfigurationSection("horse." + name);
		save();
	}
	
	
	public void setData(ConfigurationSection newdata) {
		this.data = newdata;
	}
	
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ConfigurationSection getData() {
		return this.data;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double getJump() {
		return this.jump;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public double getMaxHealth() {
		return this.max_health;
	}
	
	public Style getStyle() {
		return this.style;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public ItemStack getArmor() {
		return this.armor_item;
	}
	
	public ItemStack getSaddle() {
		return this.saddle_item;
	}
	
	public boolean hasArmor() {
		if(armor_item == null) return false;
		return armor_item.getType() != Material.AIR;
	}
	
	public boolean hasSaddle() {
		if(saddle_item == null) return false;
		return saddle_item.getType() != Material.AIR;
	}
	
	public boolean isOwnedBy(String name) {
		if(owner == null) return false;
		return owner.equals(name);
	}
	
	public boolean hasOwner() {
		return owner != null;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Override
	public String toString() {
		return
				Messenger.color(
						"&7Speed[" + speed + "]/n/" +
						"&7Jump[" + jump + "]/n/" +
						"&7Health[" + health + "/" + max_health + "]/n/"
						);
	}
	
}
