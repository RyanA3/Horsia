package me.felnstaren.horsia.util.ui.chat.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.felnstaren.horsia.util.ui.chat.ChatResponse;

public class ChatResponseEvent extends Event {
	
	private static final HandlerList HANDLERS = new HandlerList();

	private Player player;
	private String response;
	private String id;
	
	public ChatResponseEvent(ChatResponse response) {
		super(true); //Makes event ASYNCHRONOUS
		this.player = response.getPlayer();
		this.response = response.getResponse();
		this.id = response.getID();
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
	
	
	
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
	
}
