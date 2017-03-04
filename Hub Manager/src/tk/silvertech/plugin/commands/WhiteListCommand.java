package tk.silvertech.plugin.commands;

import org.bukkit.command.CommandSender;

import tk.silvertech.plugin.command.Command;
import tk.silvertech.plugin.command.CommandInfo;
import tk.silvertech.plugin.utilities.ChatUtilites;
import tk.silvertech.plugin.whitelist.WhiteListManager;

@CommandInfo(command="whitelist",description="For manage the white list.",player=false,op=true)
public class WhiteListCommand extends Command{

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		// TODO Add/Delete/Load/Save Commands
		if(args.length > 0){
			if(args[0].equalsIgnoreCase("add")){
				if(args.length >= 2){
					String name = args[1];
					if(args.length > 2){
						for (int i = 2; i < args.length; i++) {
							name += " " + args[i];
						}
					}
					WhiteListManager.add(name);
				}else{
					ChatUtilites.sendToSender(sender, "&9/manager &awhitelist &7add [name]");
				}
				return true;
			}
			else if(args[0].equalsIgnoreCase("delete")){
				if(args.length >= 2){
					String name = args[1];
					if(args.length > 2){
						for (int i = 2; i < args.length; i++) {
							name += " " + args[i];
						}
					}
					WhiteListManager.delete(name);
				}else{
					ChatUtilites.sendToSender(sender, "&9/manager &awhitelist &7delete [name]");
				}
				return true;
			}
			else if(args[0].equalsIgnoreCase("load")){
				ChatUtilites.sendToSender(sender, "&aLoaded!");
				return true;
			}
			else if(args[0].equalsIgnoreCase("save")){
				ChatUtilites.sendToSender(sender, "&aSaved!");
				return true;
			}
			else if(args[0].equalsIgnoreCase("toggle")){
				if(WhiteListManager.isEnable())WhiteListManager.disable();
				else if(!WhiteListManager.isEnable())WhiteListManager.enable();
				String message = "&eThe Whitelist is " + (WhiteListManager.isEnable() ? "&aOn" : "&cOff") + "!";
				ChatUtilites.sendToSender(sender, message);
				return true;
			}
			ChatUtilites.sendToSender(sender, "&9/manager &awhitelist &7load");
			ChatUtilites.sendToSender(sender, "&9/manager &awhitelist &7save");
			ChatUtilites.sendToSender(sender, "&9/manager &awhitelist &7toggle");
			ChatUtilites.sendToSender(sender, "&9/manager &awhitelist &7add [name]");
			ChatUtilites.sendToSender(sender, "&9/manager &awhitelist &7delete [name]");
			return true;
		}else{
			return false;
		}
	}

	
	
}
