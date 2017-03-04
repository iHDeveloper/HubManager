package tk.silvertech.plugin.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HideUtilities {

	/* Hide */
	
	public static void hideFromAll(Player p){
		for (Player player : Bukkit.getOnlinePlayers()) {
			hide(player, p);
		}
	}
	
	public static void hideFromHim(Player p){
		for (Player player : Bukkit.getOnlinePlayers()) {
			hide(p, player);
		}
	}
	
	public static void hide(Player in, Player player){	
		in.hidePlayer(player);
	}
	
	/* Show */
	
	public static void showFromAll(Player p){
		for (Player player : Bukkit.getOnlinePlayers()) {
			show(player, p);
		}
	}
	
	public static void showFromHim(Player p){
		for (Player player : Bukkit.getOnlinePlayers()) {
			show(p, player);
		}
	}
	
	public static void show(Player in, Player player){
		in.showPlayer(player);
	}
	
}
