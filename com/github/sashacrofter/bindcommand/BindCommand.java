package com.github.sashacrofter.bindcommand;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;



public class BindCommand extends JavaPlugin
{
	private Logger log;
	private CommandModifier cm;
	
	public static void main (String[] args)
	{
		
	}
	
	public void onEnable()
	{
		log = this.getLogger();
		cm = DataHandler.loadCM(getDataFolder()+"BindCommandCM.txt");
	}
	
	public void onDisable()
	{
		log.info(this.getName() + " has been disabled.");
		DataHandler.saveCM(cm, getDataFolder()+"BindCommandCM.txt");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args)
	{
		//Check permission
		if(!sender.hasPermission("bind"))
		{
			this.warn(sender, "You don't have permission to use this plugin.");
		} else
		{
			if(cmd.getName().equalsIgnoreCase("bc"))
			{
				
			}
		}
		return false;
	}
	
	//MESSAGE BRIDGES
	
	public void sendToMinecraftChat(String string)
	{
		getServer().broadcastMessage(string);
	}
	
	//INTERNAL CONVENIENCE METHODS
	
	/**
	 * log message
	 * @param message
	 */
	public void log(String message)
	{
		this.log.info(message);
	}
	
	public void message(CommandSender sender, String message)
	{
		sender.sendMessage(message);
	}
	
	public void warn(CommandSender sender, String warning)
	{
		message(sender, ChatColor.RED + warning);
	}
	
	//GETTERS AND SETTERS
}
