package com.digitalbrainery.pCops.commands;

import org.bukkit.command.CommandSender;

/**
 *
 * @author FilchersSoup
 */
public class cmdBasic extends pCopsCmdHandler
{
	public boolean run (final CommandSender sender, final String[] args)
	{
		sender.sendMessage("You executed the basic command!");
		return true;
	}
	
}
