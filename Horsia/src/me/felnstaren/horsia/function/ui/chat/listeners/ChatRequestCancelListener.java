package me.felnstaren.horsia.function.ui.chat.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.felnstaren.horsia.util.Messenger;
import me.felnstaren.horsia.util.ui.chat.event.ChatRequestCancelEvent;

public class ChatRequestCancelListener implements Listener {

	@EventHandler
	public void onCancel(ChatRequestCancelEvent event) {
		Player player = event.getPlayer();
		
		player.sendMessage(Messenger.color("&eYou've cancelled your response"));
	}
	
}
