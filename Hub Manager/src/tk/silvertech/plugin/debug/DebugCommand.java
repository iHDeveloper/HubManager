package tk.silvertech.plugin.debug;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.silvertech.plugin.command.Command;
import tk.silvertech.plugin.command.CommandInfo;
import tk.silvertech.plugin.utilities.ChatUtilites;

@CommandInfo(command="debug",description="This is for debug.",player=true,op=true)
public class DebugCommand extends Command{

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		// TODO DEBUG COMMAND
		Player p = (Player)sender;
		if(args.length == 1){
			//TODO DEBUG CHEST COMMAND
			if(args[0].equalsIgnoreCase("start")){
				try {
					DebugManager.setDebuger(p);
					DebugManager.start();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					ChatUtilites.sendToPlayer(p, "&4&lError!");
				}
				return true;
			}
			else if(args[0].equalsIgnoreCase("stop")){
				try {
					DebugManager.stop();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					ChatUtilites.sendToPlayer(p, "&4&lError!");
				}
				return true;
			}
			else if(args[0].equalsIgnoreCase("shortend")){
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					ChatUtilites.sendToPlayer(p, "&4&lError!");
				}
				return true;
			}
		}
		return true;
	}

	
	
}
