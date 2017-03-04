package tk.silvertech.plugin.debug;

import org.bukkit.entity.Player;

import tk.silvertech.plugin.utilities.ChatUtilites;

public class DebugManager {

	private static Player debuger = null;
	private static boolean inDebugging = false;
	
	public static void setDebuger(Player p){
		debuger = p;
	}
	
	public static void start(){
		inDebugging = true;
		info("DebugManager", "Debug start");
	}
	
	public static void stop(){
		info("DebugManager", "Debug stop");
		inDebugging = false;
	}
	
	public static void info(String who, String msg){
		send("INFO", who, msg);
	}
	
	public static void err(String who, String msg){
		send("ERR", who, msg);
	}
	
	public static void warn(String who, String msg){
		send("WARN", who, msg);
	}
	
	private static void send(String type, String who, String msg){
		if(inDebugging){
			String message = "["+who+"] "+type.toUpperCase()+": "+msg;
			System.out.println(message);
			if(debuger != null)ChatUtilites.sendToPlayer(debuger, "&7"+message);
		}
	}
	
}
