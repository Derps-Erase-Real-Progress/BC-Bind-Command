package com.github.sashacrofter.bindcommand;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;



public class BindCommand extends JavaPlugin
{
	private Logger log;
	private CommandModifier cm;
	private File dataFile;
	
	public static void main (String[] args)
	{
	}
	
	public void onEnable()
	{
		log = this.getLogger();
		cm = null;
		dataFile = new File(getDataFolder()+"/BindCommandCM.txt");
		
		if(dataFile.exists()) cm = DataHandler.loadCM(dataFile.getAbsolutePath());
		else cm = new CommandModifier(); //Make new if nonexistent
	}
	
	public void onDisable()
	{
		if(!dataFile.exists()) //Create new file
		{
			try { dataFile.createNewFile(); }
			catch (Exception e) {}
		}
		DataHandler.saveCM(cm, dataFile.getAbsolutePath());
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
			if(cmd.getName().equalsIgnoreCase("bc")) //Execute stored command
			{
				if(args.length > 0)
				{
					//Assemble multi-word key if necessary
					String key = args[0];
					for(int i=1;i<args.length;i++) key = key+" "+args[i];
					//Get stored command from the key
					String command = this.cm.getCommand(sender, key); //TODO handle NullPointerException
					//TODO execute command as player
					//temporary
					this.log(command);
				}
			}
			else if(cmd.getName().equalsIgnoreCase("bc-bind")) //Bind new command
			{
				if(args.length > 1)
				{
					//TODO allow multi-word key and begin to interpret command at /<command>
					String key = args[0];
					//Assemble multi-word command
					String command = args[1];
					for(int i=2;i<args.length;i++) command = command+" "+args[i];
					//Create new bind
					if (this.cm.setKey(sender, key, command));
					else this.warn(sender, "Binding failed because that key is taken.");
				}
			} //TODO add removal of bound keys 
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
