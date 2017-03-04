package tk.silvertech.plugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.silvertech.plugin.command.Command;
import tk.silvertech.plugin.command.CommandInfo;
import tk.silvertech.plugin.spawn.SpawnManager;
import tk.silvertech.plugin.utilities.ChatUtilites;

@CommandInfo(command="spawn",description="this is command for spawn",player=true,op=true)
public class SpawnCommand extends Command{

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		Player p = (Player)sender;
		if(args.length == 2){
			if(args[0].equalsIgnoreCase("lobby")){
				if(args[1].equalsIgnoreCase("set")){
					SpawnManager.setLobby(p.getLocation());
					ChatUtilites.sendToSender(sender, "&aComplete &eset lobby!");
				}
				else if(args[1].equalsIgnoreCase("get")){
					ChatUtilites.sendToSender(sender, "&eLobby Location >> &7"+SpawnManager.getLobby());
				}
			}
		}
		ChatUtilites.sendToSender(sender, "&9/manager &aSpawn &7lobby <set:get>");
		return true;
	}

	
	
}
