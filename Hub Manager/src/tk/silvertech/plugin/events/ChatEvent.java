package tk.silvertech.plugin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener{

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent ev){
		Player p = ev.getPlayer();
		String message = ev.getMessage();
		if(p.hasPermission("color.format")){
			message = ChatColor.translateAlternateColorCodes('&', message);
		}
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', 
				"&1"+p.getDisplayName()+" &9>> "+message));
		ev.setMessage("");
		ev.setCancelled(true);
	}
	
}
