package tk.silvertech.plugin.whitelist;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import tk.silvertech.plugin.HubManager;

public class WhiteListManager {

	private static boolean isOn = false;
	private static List<String> list = new ArrayList<String>();
	
	public static void load(){
		YamlConfiguration config = HubManager.getConfiguration();
		List<String> list = config.getStringList("whitelist.list");
		boolean isOn = config.getBoolean("whitelist.enable");
		WhiteListManager.list = list;
		WhiteListManager.isOn = isOn;
	}
	
	public static void save(){
		YamlConfiguration config = HubManager.getConfiguration();
		config.set("whitelist.list", list);
		config.set("whitelist.enable", isOn);
	}
	
	public static boolean isEnable(){
		return isOn;
	}
	
	public static void add(String name){
		list.add(name);
	}
	
	public static void add(Player p){
		add(p.getName());
	}
	
	public static void delete(String name){
		list.add(name);
	}
	
	public static void delete(Player p){
		delete(p.getName());
	}
	
	public static void enable(){
		isOn = true;
	}
	
	public static void disable(){
		isOn = false;
	}
	
	public static boolean isInList(Player p){
		if(p.isOp() || list.contains(p.getName())){
			return true;
		}
		return false;
	}
	
}
