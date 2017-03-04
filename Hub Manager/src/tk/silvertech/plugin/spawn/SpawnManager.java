package tk.silvertech.plugin.spawn;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import tk.silvertech.plugin.HubManager;

public class SpawnManager {

	private static Location lobby = null;
	
	public static void load(){
		YamlConfiguration config = HubManager.getConfiguration();
		lobby = (Location)config.get("spawn.lobby");
	}
	
	public static void save(){
		YamlConfiguration config = HubManager.getConfiguration();
		config.set("spawn.lobby", lobby);
	}
	
	public static void setLobby(Location location){
		lobby = location;
	}
	
	public static Location getLobby(){
		return lobby;
	}
	
}
