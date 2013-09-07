package com.digitalbrainery.pCops.commands;

import org.bukkit.command.CommandSender;

/**
 *
 * @author FilchersSoup
 */
public class cmdFlag extends pCopsCmdHandler
{
	public boolean run (final CommandSender sender, final String[] args)
	{
		sender.sendMessage("You flagged someone!");
		return true;
	}
	
}
