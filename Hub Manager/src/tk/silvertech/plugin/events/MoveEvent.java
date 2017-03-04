package tk.silvertech.plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.silvertech.plugin.whitelist.WhiteListManager;

public class MoveEvent implements Listener{

	public void onMove(PlayerMoveEvent ev){
		final Player p = ev.getPlayer();
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
	}
	
}
