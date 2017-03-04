package tk.silvertech.plugin.teleporter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import tk.silvertech.plugin.HubManager;
import tk.silvertech.plugin.debug.DebugManager;

public class TeleportManager {

	private static Map<String, TeleportItem> items
	 = new HashMap<String, TeleportItem>();
	
	public static void load(){
		YamlConfiguration config = HubManager.getConfiguration();
		List<String> list = config.getStringList("teleporter.list");
		int count = 0;
		for (String name : list) {
			TeleportItem item = TeleportItem.load(name);
			count++;
			add(item);
			System.out.println("[TeleportManager] Load /"+name);
		}
		update();
		System.out.println("[TeleportManager] "+count+" Loaded!");
	}
	
	public static void save(){
		YamlConfiguration config = HubManager.getConfiguration();
		List<String> list = Arrays.asList(items.keySet().toArray(new String[items.keySet().size()]));
		config.set("teleporter.list", list);
		for (String id : items.keySet()) {
			// TODO save teleport
			TeleportItem item = items.get(id);
			item.save();
		}
	}
	
	public static void add(TeleportItem item){
		items.put(item.getName(), item);
		item.setup();
	}
	
	public static void delete(String name){
		items.remove(name);
	}

	public static boolean isHere(String name) {
		// TODO Auto-generated method stub
		return items.containsKey(name);
	}

	public static TeleportItem get(String name) {
		// TODO Auto-generated method stub
		return items.get(name);
	}
	
	public static TeleportItem get(ItemStack item) {
		// TODO Auto-generated method stub
		for (TeleportItem i : items.values()) {
			if(i.getItem().equals(item)){
				return i;
			}
		}
		return null;
	}

	protected static void update(){
		Inventory inv = Bukkit.createInventory(null, 54, color("&6&lGamesPackages &7&lList"));
		for (String name : items.keySet()) {
			TeleportItem item = items.get(name);
			inv.setItem(item.getSort()-1, item.getItem());
			DebugManager.info("TeleportManager", "[Update] >> "+item.getItem());
		}
		TeleportManager.inv = inv;
	}
	
	private static Inventory inv = null;
	
	public static Inventory getInventory(){
		return inv;
	}
	
	public static void showInv(Player player) {
		player.openInventory(inv);
	}
	
	private static String color(String format){
		return ChatColor.translateAlternateColorCodes('&', format);
	}

	public static  Set<String> getList() {
		// TODO Auto-generated method stub
		return items.keySet();
	}
	
}
