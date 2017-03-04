package tk.silvertech.plugin.teleporter;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.silvertech.plugin.command.Command;
import tk.silvertech.plugin.command.CommandInfo;
import tk.silvertech.plugin.utilities.ChatUtilites;

@CommandInfo(command="teleport",description="This is command for teleport",player=true,op=true)
public class TeleportCommand extends Command{

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		/*
		 * TODO List:
		 * "/manager teleport create <name> <id> <amount> <sort> <type> [text]"
		 * "/manager teleport delete <name>"
		 * "/manager teleport set <name> <key> <value>"
		 * "/manager teleport onclick server <name> <value>"
		 * "/manager teleport onclick world <name>"
		 * "/manager teleport update"
		 * */
		if(args.length >= 7){
			if(args[0].equalsIgnoreCase("create")){
				String name = args[1];
				if(!TeleportManager.isHere(name)){
					int id = Integer.parseInt(args[2]);
					int amount = Integer.parseInt(args[3]);
					int sort = Integer.parseInt(args[4]);
					int type = Integer.parseInt(args[5]);
					String text = args[6];
					if(args.length > 7){
						for (int i = 8; i < args.length; i++) {
							text += " "+args[i];
						}
					}
					TeleportItem item = new TeleportItem(name, text, new ArrayList<String>(), id, amount, type, sort);
					TeleportManager.add(item);
					ChatUtilites.sendToSender(sender, "&2Complete! &e&ladd &9'"+name+"'");
				}
				else{
					ChatUtilites.sendToSender(sender, "&4The Name is here!");
				}
				return true;
			}
		}else if(args.length == 2){
			if(args[0].equalsIgnoreCase("delete")){
				String name = args[1];
				if(TeleportManager.isHere(name)){
					TeleportManager.delete(name);
					ChatUtilites.sendToSender(sender, "&aComplete! &edelete &9"+name);
				}else{
					ChatUtilites.sendToSender(sender, "&4The Name isn't here!");
				}
				return true;
			}
		}
		else if(args.length >= 3){
			if(args[0].equalsIgnoreCase("onclick")){
				String name = args[2];
				if(TeleportManager.isHere(name)){
					TeleportItem item = TeleportManager.get(name);
					if(args[1].equalsIgnoreCase("world")){
						Location l = ((Player) sender).getLocation();
						item.setClick("world", l);
						ChatUtilites.sendToSender(sender, "&2Complete! &eOnclick World");
					}
					else if(args[1].equalsIgnoreCase("message")){
						String text = args[3];
						if(args.length > 3){
							for (int i = 4; i < args.length; i++) {
								text += " "+args[i];
							}
						}
						item.setClick("message", text);
						ChatUtilites.sendToSender(sender, "&2Complete! &eOnclick Message");
					}
					else if(args[1].equalsIgnoreCase("server")){
						try {
							item.setClick("server", args[3]);
							ChatUtilites.sendToSender(sender, "&2Complete! &eOnClick Server");
						} catch (ArrayIndexOutOfBoundsException e) {
							ChatUtilites.sendToSender(sender, "&4Not found the name of the server.");
						}
					}else{
						ChatUtilites.sendToSender(sender, "&7<server/message/world>");
					}
					return true;
				}else{
					ChatUtilites.sendToSender(sender, "&4The Name isn't here!");
				}
				return true;
			}
		}
		else if(args.length == 1){
			if(args[0].equalsIgnoreCase("update")){
				try {
					TeleportManager.update();
					ChatUtilites.sendToSender(sender, "&aUpdated!");
				} catch (Exception e) {
					ChatUtilites.sendToSender(sender, "&4Failed!");
				}
				return true;
			}
			else if(args[0].equalsIgnoreCase("list")){
				try {
					ChatUtilites.sendToSender(sender, "&7&l----------------------------------");
					for (String name : TeleportManager.getList()) {
						ChatUtilites.sendToSender(sender, "&9- "+name);
					}
					ChatUtilites.sendToSender(sender, "&7&l----------------------------------");
				} catch (Exception e) {
					ChatUtilites.sendToSender(sender, "&4Failed!");
				}
				return true;
			}
		}
		ChatUtilites.sendToSender(sender, "&e&l▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		ChatUtilites.sendToSender(sender, "&9/manager &2teleport &7create <name> <id> <amount> <sort> <type> [text]");
		ChatUtilites.sendToSender(sender, "&9/manager &2teleport &7delete <name>");
		ChatUtilites.sendToSender(sender, "&9/manager &2teleport &7onclick server <name> <value>");
		ChatUtilites.sendToSender(sender, "&9/manager &2teleport &7onclick world <name>");
		ChatUtilites.sendToSender(sender, "&9/manager &2teleport &7onclick message <name> <text>");
		ChatUtilites.sendToSender(sender, "&9/manager &2teleport &7update");
		ChatUtilites.sendToSender(sender, "&9/manager &2teleport &7list");
		ChatUtilites.sendToSender(sender, "&e&l▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		return true;
	}

}
