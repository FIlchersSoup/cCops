package com.digitalbrainery.pCops.commands;

import org.bukkit.command.CommandSender;

/**
 *
 * @author FilchersSoup
 */
public class cmdRead extends pCopsCmdHandler
{
	public boolean run (final CommandSender sender, final String[] args)
	{
		sender.sendMessage("You read something from a .YML!");
		return true;
	}
	
}
