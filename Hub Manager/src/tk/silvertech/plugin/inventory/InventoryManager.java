package tk.silvertech.plugin.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryManager {

	public static void give(Player p){
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1, (short)7));
		ItemStack inv = new ItemStack(Material.COMPASS, 1);
		ItemMeta meta = inv.getItemMeta();
		meta.setDisplayName(color("&4&lTeleporter!"));
		inv.setItemMeta(meta);
		p.getInventory().setItem(0, inv);
	}
	
	private static String color(String format){
		return ChatColor.translateAlternateColorCodes('&', format);
	}
	
}
