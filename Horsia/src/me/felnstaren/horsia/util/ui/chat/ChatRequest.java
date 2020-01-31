package me.felnstaren.horsia.util.ui.chat;

import org.bukkit.entity.Player;

public class ChatRequest {
	
	private Player player;
	private String id;
	
	public ChatRequest(Player player, String id) {
		this.player = player;
		this.id = id;
	}
	
	
	
	public Player getPlayer() {
		return player;
	}
	
	public String getID() {
		return id;
	}

}
