package tk.silvertech.plugin.utilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.silvertech.plugin.debug.DebugManager;

public class FreezeUtilities implements Listener{

	private static Map<Player, Location> freeze = 
			new HashMap<Player, Location>();
	
	public static void freeze(Player p){
		freeze.put(p, p.getLocation());
	}
	
	public static void unFreeze(Player p){
		freeze.remove(p);
	}
	
	public static void freezeAll(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			freeze(p);
		}
		DebugManager.info("FreezeUtilities", "Freaze All Players");
	}
	
	public static void unFreezeAll(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			unFreeze(p);
		}
		DebugManager.info("FreezeUtilities", "UnFreaze All Players");
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(freeze.containsKey(p)){
			Location pl = p.getLocation();
			Location l = freeze.get(p);
			l.setPitch(pl.getPitch());
			l.setYaw(pl.getYaw());
			if(pl.getX() > l.getX() || pl.getX() < l.getX()
					|| pl.getZ() > l.getZ() || pl.getZ() < l.getZ() ){
				p.teleport(l);
			}
		}
	}
	
}
