package me.felnstaren.horsia.function.horse.listeners;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

import me.felnstaren.horsia.util.Messenger;
import net.minecraft.server.v1_15_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;

public class HorseTameListener implements Listener {

	@EventHandler
	public void onTame(EntityTameEvent event) {
		Player p = (Player) event.getOwner();
		CraftPlayer cp = (CraftPlayer) p;
		
		if(!(event.getEntity() instanceof Horse)) return;
		((Horse) event.getEntity()).setOwner(null);
		
		p.sendMessage(Messenger.color("&7Would you like to add this horse to your stable?"));
		String rjson = "[\"\",{\"text\":\"[\",\"color\":\"gray\"},{\"text\":\"" + "Confirm" + "\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/claimhorse\"}},{\"text\":\"]\",\"color\":\"gray\"}]";
		PacketPlayOutChat spacket = new PacketPlayOutChat(ChatSerializer.b(rjson));
		cp.getHandle().playerConnection.sendPacket(spacket);
	}
	
}
