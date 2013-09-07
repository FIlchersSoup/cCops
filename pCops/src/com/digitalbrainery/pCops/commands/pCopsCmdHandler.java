package com.digitalbrainery.pCops.commands;

import com.digitalbrainery.pCops.pCops;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



/**
 *
 * @author FilchersSoup
 */
public class pCopsCmdHandler extends pCops
{
	
	public boolean pConditionsonCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args)
	{
		if (sender instanceof Player)
		{
			
			if (args.length == 0)
			{
			}
			else if (args[0].equalsIgnoreCase("basic") && sender.hasPermission("pcops.basic"))
			{ 
				final cmdBasic cmd = new cmdBasic();
				return cmd.run(sender, args);
			}
			else if (args[0].equalsIgnoreCase("flag") && sender.hasPermission("pcops.flag"))
			{ 
				final cmdFlag cmd = new cmdFlag();
				return cmd.run(sender, args);
			}
			else if (args[0].equalsIgnoreCase("read") && sender.hasPermission("pcops.read"))
			{ 
				final cmdRead cmd = new cmdRead();
				return cmd.run(sender, args);
			}
			else if (args[0].equalsIgnoreCase("clrflag") && sender.hasPermission("pcops.clrflag"))
			{ 
				final cmdClrFlag cmd = new cmdClrFlag();
				return cmd.run(sender, args);
			}
			
			sender.sendMessage("/pCops");
			sender.sendMessage("      clrflag <player>");
			return true;
		}
		
		return true;		
	}
	
}
