package me.feln.horsia.util.ui.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.feln.horsia.util.ui.menu.Menu;

public class MenuEvent extends Event {

	protected static final HandlerList HANDLERS = new HandlerList();
	private Menu menu;

	public MenuEvent(Menu menu) {
		this.menu = menu;
	}
	
	
	
	public Menu getMenu() {
		return this.menu;
	}

	
	
	public HandlerList getHandlers() {
		return HANDLERS;
	}	
	
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
	
}
