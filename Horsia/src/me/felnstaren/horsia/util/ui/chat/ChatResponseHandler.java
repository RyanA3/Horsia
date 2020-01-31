package me.felnstaren.horsia.util.ui.chat;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.felnstaren.horsia.config.Loader;
import me.felnstaren.horsia.util.ui.chat.event.ChatRequestCancelEvent;
import me.felnstaren.horsia.util.ui.chat.event.ChatRequestTimeoutEvent;
import me.felnstaren.horsia.util.ui.chat.event.ChatResponseEvent;

public class ChatResponseHandler implements Listener {

	private HashMap<ChatRequest, Integer> requests = new HashMap<ChatRequest, Integer>();
	private ArrayList<ChatRequest> timeout = new ArrayList<ChatRequest>();
	private ArrayList<ChatRequest> add = new ArrayList<ChatRequest>();
	
	public ChatResponseHandler() {
		new BukkitRunnable() {
			public void run() {
				//Decrement timers, queue horses to spawn/despawn
				for(ChatRequest r : requests.keySet()) {
					requests.put(r, requests.get(r) - 1);
					if(requests.get(r) <= 0) timeout.add(r);
				}

				if(!timeout.isEmpty()) timeOut();
				if(!add.isEmpty()) addIn();
			}
		}.runTaskTimer(Loader.plugin, 100, 20);
	}
	
	
	
	private void timeOut() {
		if(timeout.isEmpty()) return;
		for(ChatRequest r : timeout) {
			requests.remove(r);
			Bukkit.getPluginManager().callEvent(new ChatRequestTimeoutEvent(r));
		}
		timeout.clear();
	}
	
	private void addIn() {
		if(add.isEmpty()) return;
		for(ChatRequest r : add) requests.put(r, 30);
		add.clear();
	}
	
	
	
	public void addToRequests(ChatRequest request) {
		if(isRequested(request.getPlayer())) return;
		add.add(request);
	}
	
	
	
	public boolean isRequested(Player player) {
		for(ChatRequest r : requests.keySet()) if(r.getPlayer().equals(player)) return true;
		return false;
	}
	
	public ChatRequest getRequest(Player player) {
		for(ChatRequest r : requests.keySet()) if(r.getPlayer().equals(player)) return r;
		return null;
	}
	
	public void removeRequest(ChatRequest request) {
		requests.remove(request);
	}
	
	public void removeRequest(Player player) {
		ChatRequest remove = null;
		for(ChatRequest r : requests.keySet()) if(r.getPlayer().equals(player)) remove = r;
		if(remove == null) return;
		requests.remove(remove);
	}
	
	
	
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(!isRequested(player)) return;
		
		ChatRequest request = getRequest(player);
		String message = event.getMessage();
		ChatResponse response = new ChatResponse(request, message);
		
		removeRequest(request);
		
		Event out;
		if(message.equalsIgnoreCase("cancel")) out = new ChatRequestCancelEvent(request);
		else out = new ChatResponseEvent(response);
		responseOut(out);
		
		event.setCancelled(true);
	}
	
	private synchronized void responseOut(Event out) {
		Bukkit.getPluginManager().callEvent(out);
	}
	
}
