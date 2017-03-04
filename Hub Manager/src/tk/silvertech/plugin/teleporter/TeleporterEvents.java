package tk.silvertech.plugin.teleporter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeleporterEvents implements Listener{

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerInstacrt(PlayerInteractEvent e){
		if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(color("&4&lTeleporter!"))){
			TeleportManager.showInv(e.getPlayer());
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerEnter(InventoryClickEvent ev){
		//System.out.println(String.format("{Action:%s,Inventory:%s,Who:%s}", ev.getAction(), ev.getInventory().getName(), ev.getWhoClicked()));
		Player p = (Player)ev.getWhoClicked();
		if(ev.getAction().equals(InventoryAction.PICKUP_ONE) 
				|| ev.getAction().equals(InventoryAction.PICKUP_ALL)
				|| ev.getAction().equals(InventoryAction.PICKUP_HALF)
				|| ev.getAction().equals(InventoryAction.PICKUP_SOME)){
			if(ev.getInventory().equals(TeleportManager.getInventory())){
				ev.setCancelled(true);
				TeleportItem item = TeleportManager.get(ev.getCurrentItem());
				if(item == null)return;
				item.click(p);
				p.closeInventory();
			}
		}
	}
	
	private static String color(String format){
		return ChatColor.translateAlternateColorCodes('&', format);
	}
	
}
