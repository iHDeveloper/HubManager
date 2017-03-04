package tk.silvertech.plugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeftEvent implements Listener{

	@EventHandler
	public void onLeft(PlayerQuitEvent ev){
		ev.setQuitMessage("");
	}
	
}
