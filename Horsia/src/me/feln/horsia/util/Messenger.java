package me.feln.horsia.util;

public class Messenger {

	public static String color(String message) {
		return message.replace('&', '§');
	}
	
	public static String prefix(String message, String prefix) {
		return prefix.concat(message);
	}
	
	public static String permission(String permission) {
		return color("&cYou do not have permission to &7" + permission + "&c!");
	}
	
}
