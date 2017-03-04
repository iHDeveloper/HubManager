package tk.silvertech.plugin.teleporter;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import tk.silvertech.plugin.HubManager;
import tk.silvertech.plugin.debug.DebugManager;
import tk.silvertech.plugin.utilities.ChatUtilites;

public class TeleportItem {

	private String name, text;
	private List<String> description;
	private int id, amount, type, sort;
	private ItemStack item;
	private String click_type;
	private Object click_object;
	
	public TeleportItem(String name, String text, List<String> desc, int id, int amount, int type, int sort) {
		// TODO insert data
		this.name = name;
		this.text = text;
		this.description = desc;
		this.id = id;
		this.sort = sort;
		this.amount = amount;
		this.type = type;
		this.click_type = "SERVER";
		this.click_object = "hub";
		DebugManager.info("TeleportItem", String.format(
				"Create {name:%s,text:%s,id:%s,sort:%s,amount:%s,type:%s,click:{type:%s,object:%s}}", 
				name, text, id, sort, amount , type, click_type, click_object));
		System.out.println(String.format(
				"Create {name:%s,text:%s,id:%s,sort:%s,amount:%s,type:%s,click:{type:%s,object:%s}}", 
				name, text, id, sort, amount , type, click_type, click_object));
	}
	
	@SuppressWarnings("deprecation")
	public void setup(){
		item = new ItemStack(id, amount, (short)type);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(color(text));
		for (int i = 0; i < description.size(); i++) {
			String desc = description.get(i);
			desc = color(desc);
			description.set(i, desc);
		}
		meta.setLore(description);
		item.setItemMeta(meta);
		//this.item = item;
	}
	
	private String color(String format){
		return ChatColor.translateAlternateColorCodes('&', format);
	}
	
	public void setClick(String type, Object value){
		this.click_type = type;
		this.click_object = value;
	}
	
	public ItemStack getItem(){
		return item;
	}
	
	public int getId(){return id;}
	
	public int getSort(){
		return sort;
	}

	protected void click(Player p){
		if(click_type.equalsIgnoreCase("server")){
			ByteArrayDataOutput out = ByteStreams.newDataOutput();		
			out.writeUTF("Connect");
			out.writeUTF(this.click_object.toString());
			p.sendPluginMessage(HubManager.getInstance(), "BungeeCord", out.toByteArray());
		}
		else if(click_type.equalsIgnoreCase("world")){
			p.teleport((Location)this.click_object);
		}else if(click_type.equalsIgnoreCase("message")){
			ChatUtilites.sendToPlayer(p, click_object.toString());
		}
	}
	
	protected static TeleportItem load(String name) {
		// TODO Auto-generated method stub
		YamlConfiguration config = HubManager.getConfiguration();
		String path = "teleporter.item-"+name+".";
		String text = path+"text";
		String amount = path+"amount";
		String type = path+"type";
		String sort = path+"sort";
		String id = path+"id";
		String desc = path+"description";
		String click = path+"click.";
		String click_type = click+"type";
		String click_object = click+"obj";
		TeleportItem item = new 
				TeleportItem(name,
						config.getString(text),
						config.getStringList(desc),
						config.getInt(id), 
						config.getInt(amount), 
						config.getInt(type), 
						config.getInt(sort));
		item.setClick(config.getString(click_type), config.get(click_object));
		System.out.println("");
		return item;
	}
	
	protected void save(){
		YamlConfiguration config = HubManager.getConfiguration();
		String path = "teleporter.item-"+name+".";
		String text = path+"text";
		String amount = path+"amount";
		String type = path+"type";
		String sort = path+"sort";
		String id = path+"id";
		String desc = path+"description";
		String click = path+"click.";
		String click_type = click+"type";
		String click_object = click+"obj";
		config.set(id, this.id);
		config.set(text, this.text);
		config.set(type, this.type);
		config.set(sort, this.sort);
		config.set(desc, this.description);
		config.set(amount, this.amount);
		config.set(click_type, this.click_type);
		config.set(click_object, this.click_object);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
