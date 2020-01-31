package me.felnstaren.horsia.function.ui.chat.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.ui.chat.event.ChatRequestTimeoutEvent;

public class ChatRequestTimeoutListener implements Listener {

	@EventHandler
	public void onTimeout(ChatRequestTimeoutEvent event) {
		Player player = event.getPlayer();

		player.sendMessage(Messenger.color("&cYou took too long to respond!"));
	}
	
}
