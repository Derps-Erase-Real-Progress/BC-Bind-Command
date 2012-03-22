package com.github.sashacrofter.bindcommand;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class DataHandler
{
	/**
	 * Saves the CommandModifier to a file
	 */
	public static void saveCM(CommandModifier cm, String path)
	{
		ObjectOutputStream outputStream;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(path));
			outputStream.writeObject(cm);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the CommandModifier from a file
	 * @return CommandModifier object
	 */
	public static CommandModifier loadCM(String path)
	{
		ObjectInputStream inputStream;
		CommandModifier modifier = null;
		try
		{
			inputStream = new ObjectInputStream(new FileInputStream(path));
			modifier = (CommandModifier) inputStream.readObject();
			inputStream.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return modifier;
	}
}
