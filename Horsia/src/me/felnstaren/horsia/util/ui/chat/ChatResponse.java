package me.felnstaren.horsia.util.ui.chat;

import org.bukkit.entity.Player;

public class ChatResponse {

	private Player player;
	private String id;
	private String response;
	
	public ChatResponse(Player player, String response, String id) {
		this.player = player;
		this.response = response;
		this.id = id;
	}
	
	public ChatResponse(ChatRequest request, String response) {
		this.player = request.getPlayer();
		this.id = request.getID();
		this.response = response;
	}
	
	
	
	public Player getPlayer() {
		return player;
	}
	
	public String getResponse() {
		return response;
	}
	
	public String getID() {
		return id;
	}
	
}
