package com.github.sashacrofter.bindcommand;

import java.util.HashMap;

import org.bukkit.command.CommandSender;

public class CommandModifier
{
	private HashMap<String, HashMap<String, String>> map;
	
	public CommandModifier()
	{
		map = new HashMap<String, HashMap<String, String>>();
	}
	
	/**
	 * Converts a key and playername into the String the player bound the key to
	 * @param player the player invoking the bound command
	 * @param key the key the player invoked
	 * @return the string that the player bound to the key
	 */
	public String getCommand(CommandSender player, String key)
	{
		return this.map.get(player.getName()).get(key);
	}
	
	/**
	 * Adds a new player to the CommandModifier HashMap
	 * @param player playername to add
	 * @return true if added successfully, false if player already exists
	 */
	public boolean setPlayer(CommandSender player)
	{
		if(this.map.containsKey(player.getName())) return false;
		this.map.put(player.getName(), new HashMap<String, String>());
		return true;
	}
	
	/**
	 * Removes the bound key from the player's bound key list
	 * @param player player to remove
	 * @return true if player was removed successfully, false if it was not existent
	 */
	public boolean removePlayer(CommandSender player)
	{
		if(!this.map.containsKey(player.getName())) return false;
		this.map.remove(player);
		return true;
	}
	
	/**
	 * Adds a new command to the player's bound command HashMap
	 * @param player
	 * @param key
	 * @param command
	 * @return true if key was added successfully, false if key is already bound
	 */
	public boolean setKey(CommandSender player, String key, String command)
	{
		if(!this.map.containsKey(player)) this.setPlayer(player);
		if(this.map.get(player).containsKey(key)) return false;
		this.map.get(player).put(key, command);
		return true;
	}
	
	/**
	 * Removes the bound key from the player's bound key list
	 * @param player playername to remove the key from
	 * @param key key to remove
	 * @return true if key was removed successfully, false if it was not existent
	 */
	public boolean removeKey(CommandSender player, String key)
	{
		if(!this.map.get(player.getName()).containsKey(key)) return false;
		this.map.get(player.getName()).remove(key);
		return true;
	}
}
