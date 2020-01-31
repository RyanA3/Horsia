package me.felnstaren.horsia.util.ui.chat.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.felnstaren.horsia.util.ui.chat.ChatRequest;

public class ChatRequestCancelEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();
	
	private Player player;
	private String id;
	
	public ChatRequestCancelEvent(ChatRequest request) {
		super(true); //Makes event ASYNCHRONOUS
		this.player = request.getPlayer();
		this.id = request.getID();
	}
	
	
	
	public Player getPlayer() {
		return player;
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
