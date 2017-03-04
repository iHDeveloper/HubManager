package tk.silvertech.plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import tk.silvertech.plugin.HubManager;
import tk.silvertech.plugin.inventory.InventoryManager;
import tk.silvertech.plugin.spawn.SpawnManager;
import tk.silvertech.plugin.utilities.ChatUtilites;
import tk.silvertech.plugin.whitelist.WhiteListManager;

public class JoinEvent implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent ev){
		final Player p = ev.getPlayer();
		//
		PermissionUser user = PermissionsEx.getUser(p);
		String color = user.getOption("color");
		String prefix = user.getPrefix();
		p.setDisplayName(ChatColor.translateAlternateColorCodes('&', prefix+" "+color+p.getName()));
		//
		if(WhiteListManager.isEnable()){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!WhiteListManager.isInList(p)){
						p.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&eThe Server &4In maintenance! &9See you later!"));
					}
				}
			}).start();
		}
		//
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Location spawn = SpawnManager.getLobby();
				if(spawn != null)p.teleport(spawn);
				InventoryManager.give(p);
			}
		}).start();
		//
		String path = "join-message.";
		boolean enable = HubManager.config().getBoolean(path+"enable");
		String title = HubManager.config().getString(path+"title").trim().replace("'", "");
		String subTitle = HubManager.config().getString(path+"subtitle").trim().replace("'", "");
		String message = HubManager.config().getString(path+"message").trim().replace("'", "");
		if(enable){
			ChatUtilites.sendTitleToPlayer(p, title, subTitle, 20, 40, 20);
			ChatUtilites.sendToPlayer(p, message);
		}
		ev.setJoinMessage("");
	}
	
}
