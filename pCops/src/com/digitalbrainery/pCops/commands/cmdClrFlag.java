package com.digitalbrainery.pCops.commands;

import com.digitalbrainery.pCops.pCops;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author FilchersSoup
 */
public class cmdClrFlag extends pCopsCmdHandler
{
	public boolean run (final CommandSender sender, final String[] args)
	{
		
		if (args.length > 1)
		{
			Player target = sender.getServer().getPlayer(args[1]);
			
			if (target != null)
			{
			StringBuilder flagPath = new StringBuilder(target.getName());
			flagPath.append(".flag");
			
			final FileConfiguration pFile = pCops.getPlayerFile().getFile();			
			pFile.set(flagPath.toString(), null);
		
			StringBuilder message = new StringBuilder("You have removed ");
			message.append(target.getName());
			message.append(" from pCops!");			
			sender.sendMessage(message.toString());
			
			target.sendMessage("You have been removed from pCops!");
			return true;
			}
			
			sender.sendMessage("That player does not exists!");
			return true;
		}
		
		sender.sendMessage("/pCops clrflag <player>");
		return true;
	}
	
}
