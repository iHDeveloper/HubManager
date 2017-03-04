package tk.silvertech.plugin.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tk.silvertech.plugin.debug.DebugManager;

public class PvPUtilities implements Listener{
	private static List<Player> players = new ArrayList<Player>();
	 
	public static void runAll(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			run(p);
		}
		DebugManager.info("PvPUtilities", "PvP Protector All Players");
	}
	
	public static void stopAll(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			stop(p);
		}
		DebugManager.info("PvPUtilities", "UnPvP Protector All Players");
	}
	
	public static void run(Player p){
		players.add(p);
	}
	
	public static void stop(Player p){
		players.remove(p);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent e){
		Player p = (Player)e.getEntity();
		if(players.contains(p)){
			e.setDamage(0);
			e.setCancelled(true);
		}
	}
	
}
