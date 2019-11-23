package me.feln.horsia.config;

import java.math.BigDecimal;

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

import me.feln.horsia.util.MyMath;
import me.feln.horsia.util.logger.Level;
import me.feln.horsia.util.logger.Logger;

public class DataHorse {
	
	private static int id_accuracy = 11;

	private String name;
	private ConfigurationSection data = null;
	private int id;
	
	private double speed, jump, health, max_health;
	private Style style;
	private Color color;
	
	private Material armor, saddle;
	
	
	
	public DataHorse(ConfigurationSection data) {
		this.name = data.getName();
		this.data = data;
		
		Logger.log(Level.DEBUG, "Creating horse with name " + name);
		
		this.speed = data.getDouble("speed");
		this.jump = data.getDouble("jump");
		this.health = data.getDouble("health");
		this.max_health = data.getDouble("max_health");
		
		this.style = Style.valueOf(data.getString("style"));
		this.color = Color.valueOf(data.getString("color"));
		
		this.armor = Material.valueOf(data.getString("armor", "AIR"));
		this.saddle = Material.valueOf(data.getString("saddle", "AIR"));
		
		this.id = data.getInt("id");
	}
	
	public DataHorse(Horse horse) {
		this.name = horse.getCustomName();
		this.data = null;
		
		this.max_health = MyMath.round(horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), 3);
		this.speed = MyMath.round(horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue(), 3);
		this.jump = MyMath.roundDown(horse.getJumpStrength(), 3); //Round down to preserve id value
		this.health = MyMath.round(horse.getHealth(), 3);
		
		this.style = horse.getStyle();
		this.color = horse.getColor();
		
		if(horse.getInventory().getArmor() == null) this.armor = Material.AIR;
		else this.armor = horse.getInventory().getArmor().getType();
		if(horse.getInventory().getSaddle() == null) this.saddle = Material.AIR;
		else this.saddle = horse.getInventory().getSaddle().getType();
		
		this.id = calcID(horse.getJumpStrength());
		Logger.log(Level.DEBUG, "Calculated horse id from jump value; " + horse.getJumpStrength() + " - " + MyMath.roundDown(horse.getJumpStrength(), 3) + " is " + id);
	}
	
	//Calculate a horse's id from its jump strength
	public static int calcID(double jvalue) {
		double base = MyMath.roundDown(jvalue, id_accuracy);
		double remove = MyMath.roundDown(base, 3);
		return (int) ((base - remove) * Math.pow(10, id_accuracy));
	}
	
	public static double reverseID(double idin, double jvalue) {
		//double add = MyMath.roundDown(jvalue, 3);
		//double base = MyMath.roundDown(idin, id_accuracy);
		BigDecimal precisebase = new BigDecimal(idin);
		precisebase = precisebase.movePointLeft(id_accuracy);
		BigDecimal precisejvalue = new BigDecimal(jvalue);
		precisebase = precisebase.add(precisejvalue);
		return precisebase.doubleValue();
	}
	
	
	
	public Horse summon(Location location) {
		Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE);
		
		horse.setCustomName(name);
		horse.setJumpStrength(reverseID(id, jump)); //Put ID back into horse's jump height
		horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(max_health);
		horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
		horse.setHealth(health);
		horse.setStyle(style);
		horse.setColor(color);
		horse.getInventory().setArmor(new ItemStack(armor));
		horse.getInventory().setSaddle(new ItemStack(saddle));
		
		return horse;
	}
	
	
	
	public boolean matches(Horse horse) {
		return 
				name.equals(horse.getCustomName()) &&
				style.equals(horse.getStyle()) &&
				color.equals(horse.getColor()) &&
				id == calcID(horse.getJumpStrength());
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
		data.set("armor", armor.name());
		data.set("saddle", saddle.name());
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
	
	public Material getArmor() {
		return this.armor;
	}
	
	public Material getSaddle() {
		return this.saddle;
	}
	
}
