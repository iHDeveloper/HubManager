package tk.silvertech.plugin.utilities;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import tk.silvertech.plugin.HubManager;


public class ChatUtilites {
	
	private static String name = "&7&lServer Name";
	
	public static void load(){
		name = HubManager.config().getString("chat.settings.title").trim().replace("'", "");
	}
	
	public static void sendToConsole(String msg){
		String message = name+msg;
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void sendToPlayers(String msg){
		String message = name+msg;
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', 
				message));
	}
	
	public static void sendToPlayer(Player p, String msg){
		String message = name+msg;
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', 
				message));
	}
	
	public static void sendToSender(CommandSender sender, String msg){
		String message = name+msg;
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
				message));
	}
	
	public static void sendTitleToPlayer(Player p, Object title, Object subTitle, Integer fadeIn, Integer stay, Integer fadeOut){
		sendTitle(p, title, subTitle, fadeIn, stay, fadeOut);
	}
	
	public static void sendTitleToAllPlayers(String title, String subTitle, Integer fadeIn, Integer stay, Integer fadeOut){
		for (Player p : Bukkit.getOnlinePlayers()) {
			sendTitle(p, title, subTitle, fadeIn, stay, fadeOut);
		}
	}
	
	private static void sendTitle(Player p, Object title, Object subTitle, Integer fadeIn, Integer stay, Integer fadeOut){
		title = (Object)ChatColor.translateAlternateColorCodes('&', (String) title);
		subTitle = (Object)ChatColor.translateAlternateColorCodes('&', (String) subTitle);
		try{
			PacketPlayOutTitle t = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title.toString() + "\"}"), 20, 40, 20);
            PacketPlayOutTitle s = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + subTitle.toString() + "\"}"), 20, 40, 20);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(t);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(s);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void sendToPlayersWithPermission(String permission,
			String message) {
		// TODO Auto-generated method stub
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission(permission)){
				sendToPlayer(p, message);
			}
		}
	}
	
}
