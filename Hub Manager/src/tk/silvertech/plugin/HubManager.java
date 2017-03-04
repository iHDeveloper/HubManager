package tk.silvertech.plugin;

import java.io.File;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.silvertech.plugin.command.CommandManager;
import tk.silvertech.plugin.events.ChatEvent;
import tk.silvertech.plugin.events.JoinEvent;
import tk.silvertech.plugin.events.LeftEvent;
import tk.silvertech.plugin.events.MoveEvent;
import tk.silvertech.plugin.spawn.SpawnManager;
import tk.silvertech.plugin.teleporter.TeleportManager;
import tk.silvertech.plugin.teleporter.TeleporterEvents;
import tk.silvertech.plugin.utilities.ChatUtilites;
import tk.silvertech.plugin.whitelist.WhiteListManager;

public class HubManager extends JavaPlugin{

	/*
	 * 
	 * Features:
	 * - Chat
	 * - Spawn
	 * - ScoreBoard
	 * - Tab Manager
	 * */
	
	private static HubManager instance = null;
	public static HubManager getInstance(){
		return instance;
	}
	
	@Override
	public void onEnable(){
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		File file = new File("plugins//HubManager//config.yml");
		try {
			file.createNewFile();
			configuration.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		instance = this;
		ChatUtilites.load();
		CommandManager.registerCommands();
		getCommand("hubmanager").setExecutor(new CommandManager());
		runThreads();
		registerEvents();
		try {
			loadManagers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		instance = this;
		ChatUtilites.sendToConsole("&2Enabled!");
	}
	
	private void runThreads() {
		// TODO Auto-generated method stub
		
	}

	private void loadManagers() {
		// TODO Load Config
		WhiteListManager.load();
		SpawnManager.load();
		TeleportManager.load();
	}
	
	protected void saveManagers(){
		// TODO Save Config
		WhiteListManager.save();
		SpawnManager.save();
		TeleportManager.save();
	}

	private void registerEvents(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new JoinEvent(), this);
		pm.registerEvents(new LeftEvent(), this);
		pm.registerEvents(new MoveEvent(), this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new TeleporterEvents(), this);
	}
	
	@Override
	public void onDisable(){
		saveManagers();
		saveConfig();
		ChatUtilites.sendToConsole("&4Disabled!");
	}

	public static Configuration config(){
		return getInstance().getConfig();
	}
	
	YamlConfiguration configuration = new YamlConfiguration();
	
	public YamlConfiguration getLoadedConfiguration(){
		return configuration;
	}
	
	public void saveConfig(){
		try {
			configuration.save("plugins//HubManager//config.yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static YamlConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return getInstance().getLoadedConfiguration();
	}
	
}
