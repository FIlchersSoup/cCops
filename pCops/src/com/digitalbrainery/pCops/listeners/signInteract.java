package com.digitalbrainery.pCops.listeners;

import com.digitalbrainery.pCops.pCops;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;


/**
 *
 * @author FilchersSoup
 */
public class signInteract extends pCopsListener
{
			
	public void onSignInteract(final PlayerInteractEvent event)
	{
		final Player player = event.getPlayer();

		/*final Block block = event.getClickedBlock();*/
		final Sign sign = (Sign)event.getClickedBlock().getState();
		
		StringBuilder flagPath = new StringBuilder(player.getName());
		flagPath.append(".flag");
			
		final FileConfiguration pFile = pCops.getPlayerFile().getFile();
		final FileConfiguration configFile = pCops.getPlugin().getConfig();
				
		if ("<-pCops->".equals(sign.getLine(0)) && "Enter".equalsIgnoreCase(sign.getLine(1)))
		{
			String flag = pFile.getString(flagPath.toString());			
			if (flag == null)
			{
				player.sendMessage(ChatColor.GOLD + "Welcome to Cops and Robbers!");
				final Random rand1 = new Random();
				rand1.nextInt(2);
				
				if(rand1.nextInt(2) == 1)
				{
					flag = "cop";
				}
				else
				{
					flag = "robber";
				}
				
				pFile.set(flagPath.toString(), flag);
					
				StringBuilder equipmentPath = new StringBuilder("equipment.");
				equipmentPath.append(flag);
				equipmentPath.append(".items");
					
				StringBuilder itemName = new StringBuilder();
					
				List<String> equipmentItems = configFile.getStringList(equipmentPath.toString());
				String[] parsedEquipmentData;
				int itemId = 0;
					
				for (String equipment : equipmentItems)
				{
					parsedEquipmentData = equipment.split("[ ]+");
					for (int i = 0; i < parsedEquipmentData.length; i++)
					{
						if (i == 0)
						{
							itemId = Integer.parseInt(parsedEquipmentData[i]);
						}
						else
						{
							itemName.append(parsedEquipmentData[i]);
							if (i < parsedEquipmentData.length-1)
							{
								itemName.append(" ");
								player.updateInventory();
							}
						}
					}
						
					ItemStack item = new ItemStack(itemId);
					ItemMeta meta = (ItemMeta)item.getItemMeta();
					meta.setDisplayName(itemName.toString());
					item.setItemMeta(meta);
					player.getInventory().addItem(item);
					itemName = new StringBuilder();					
				}
			}
			else if(flag.equalsIgnoreCase("cop") || flag.equalsIgnoreCase("robber"))
			{
				StringBuilder message = new StringBuilder(ChatColor.RED + "You are already playing as a ");
				message.append(flag);
				message.append("!");
				player.sendMessage(message.toString());
			}		
		}			
		else if ("<-pCops->".equals(sign.getLine(0)) && "Exit".equalsIgnoreCase(sign.getLine(1)))
		{
			String flag = pFile.getString(flagPath.toString());			
			if (flag == null)
			{
				player.sendMessage(ChatColor.RED + "You are not playing Cops and Robbers!");
			}
			else if(flag.equalsIgnoreCase("cop") || flag.equalsIgnoreCase("robber"))
			{
				
				final removeEquipment removeEquipment = new removeEquipment();
				removeEquipment.onExit(player, flag);
				
				pFile.set(flagPath.toString(), null);
				player.sendMessage(ChatColor.GOLD + "You have exited pCops!");
			}
		}
	
	}
}
