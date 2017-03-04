package tk.silvertech.plugin.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.silvertech.plugin.commands.SpawnCommand;
import tk.silvertech.plugin.commands.WhiteListCommand;
import tk.silvertech.plugin.debug.DebugCommand;
import tk.silvertech.plugin.teleporter.TeleportCommand;
import tk.silvertech.plugin.utilities.ChatUtilites;

public class CommandManager implements CommandExecutor{
	private static List<Command> commands = null;
	
	public static void registerCommands(){
		// TODO Commands Listener
		commands = new ArrayList<Command>();
		//commands.add();
		commands.add(new DebugCommand());
		commands.add(new SpawnCommand());
		commands.add(new TeleportCommand());
		commands.add(new WhiteListCommand());
	}
	
	@Override
	public boolean onCommand(CommandSender sender,
			org.bukkit.command.Command c, String lal, String[] args) {
		// TODO Auto-generated method stub
		
		if(c.getName().equalsIgnoreCase("hubmanager")){
			if(args.length >= 1 && sender.hasPermission("hub.manager")){
				for (Command cmd : commands) {
					CommandInfo info = cmd.getClass().getAnnotation(CommandInfo.class);
					String command = args[0];
					if(command.equalsIgnoreCase(info.command().trim())){
						List<String> l = Arrays.asList(args);
						List<String> list = new ArrayList<String>();
						for (int i = 1; i < l.size(); i++) {
							list.add(l.get(i));
						}
						args = list.toArray(new String[list.size()]);
						if(info.op() == sender.isOp()){
							if(info.player() && sender instanceof Player){
								boolean worked = cmd.onCommand(sender, args);
								if(!worked){
									ChatUtilites.sendToSender(sender, "&9/asm &e" + info.command() + " &2"+info.description());
								}
							}
							else if(info.player() && !(sender instanceof Player)){
								ChatUtilites.sendToSender(sender, "&4This is command for player only!");
							}else{
								boolean worked = cmd.onCommand(sender, args);
								if(!worked){
									ChatUtilites.sendToSender(sender, "&9/manager &e" + info.command() + " &2"+info.description());
								}
							}
						}else{
							ChatUtilites.sendToSender(sender, "&cYou don't have any permission");
						}
						
						return true;
					}
				}
			}
			ChatUtilites.sendToSender(sender, "&e&l▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
			for (Command cmd : commands) {
				CommandInfo info = cmd.getClass().getAnnotation(CommandInfo.class);
				ChatUtilites.sendToSender(sender, "&9/manager &e" + info.command() + " &2"+info.description());
			}
			ChatUtilites.sendToSender(sender, "&e&l▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
			return true;
		}
		
		
		
		return true;
	}
}
