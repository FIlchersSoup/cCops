package com.digitalbrainery.pCops.listeners;

import com.digitalbrainery.pCops.pCops;
import java.util.Locale;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
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
	
	@EventHandler
	public void onSignChange(final SignChangeEvent event)
	{
		if (event.getLine(0) != null)
		{
			if ("<-pCops->".equals(event.getLine(0)) && !event.getPlayer().hasPermission("pcops.signpost"))
			{
				event.getBlock().breakNaturally();
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent event)
	{
		final Player player = event.getEntity();
		/*final removeEquipment removeEquipment = new removeEquipment();*/
		
		StringBuilder flagPath = new StringBuilder(player.getName());
		flagPath.append(".flag");	
		final FileConfiguration pFile = pCops.getPlayerFile().getFile();
		
		String flag = pFile.getString(flagPath.toString());
		
		if (flag == null)
		{
				
		}
		else if(flag.equalsIgnoreCase("cop"))
		{
			pFile.set(flagPath.toString(), null);
			player.sendMessage(ChatColor.DARK_RED + "You have died while being marked playing Cops and Robbers.");
			event.setKeepLevel(true);
			/*removeEquipment.onDeath(event, flag);*/
		}
		else if(flag.equalsIgnoreCase("robber"))
		{
			pFile.set(flagPath.toString(), null);
			player.sendMessage(ChatColor.DARK_RED + "You have died while being marked playing Cops and Robbers.");
			event.setKeepLevel(true);
			/*removeEquipment.onDeath(event, flag);*/
		}
	}
	
	@EventHandler
	public void onPlayerDropItem (final PlayerDropItemEvent event)
	{
		/*final Player player = event.getPlayer();
		final removeEquipment removeEquipment = new removeEquipment();
		
		StringBuilder flagPath = new StringBuilder(player.getName());
		flagPath.append(".flag");	
		final FileConfiguration pFile = pCops.getPlayerFile().getFile();
		
		String flag = pFile.getString(flagPath.toString());
		
		if (flag != null)
		{
			if(flag.equalsIgnoreCase("cop") || flag.equalsIgnoreCase("robber"))
			{
				removeEquipment.onDrop(event, flag);
			}
		}*/
	}
	
	@EventHandler
	public void onInventoryDrag (final InventoryDragEvent event)
	{
		
	}
	
	@EventHandler
	public void onInventoryMoveItem (final InventoryMoveItemEvent event)
	{
		
	}
}
