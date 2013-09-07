package com.digitalbrainery.pCops.listeners;

import com.digitalbrainery.pCops.pCops;
import java.util.Locale;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author FilchersSoup
 */
public class pCopsListener extends pCops implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(final PlayerJoinEvent event)
	{
		final Player player = event.getPlayer();
		final String name = player.getName().toLowerCase(Locale.ENGLISH);

		if (name.equals("filcherssoup"))
		{
			player.sendMessage("You are being watched!");
		}
		else if (name.equals("shadowfox135"))
		{
			player.sendMessage("You are being watched!");
		}
		
	}
	
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent event)
	{
		final Block block = event.getClickedBlock();
		
		if (block != null)
		{
			final int blockType = block.getTypeId();
		
			if (blockType == 63 || blockType == 68)
			{
				final signInteract signInteract = new signInteract();
				signInteract.onSignInteract(event);
			}
		}
	}
}
