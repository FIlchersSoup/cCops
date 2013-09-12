package com.digitalbrainery.pCops.listeners;

import com.digitalbrainery.pCops.pCops;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author FilchersSoup
 */
public class removeEquipment
{
	public String[] equipmentDataParser (String equipment)
	{
		/* Need to break out into another class and clean up the return*/
		StringBuilder itemName = new StringBuilder();
		
		String[] parts = new String[2];
		
		String[] parsedEquipmentData = equipment.split("[ ]+");
		for (int i = 0; i < parsedEquipmentData.length; i++)
		{
			if (i == 0)
			{
				parts[0] = parsedEquipmentData[i]; /*This is the Item Id*/
			}
			else
			{
				itemName.append(parsedEquipmentData[i]);
				if (i < parsedEquipmentData.length-1)
				{
					itemName.append(" ");
				}
			}
		}
		parts[1] = itemName.toString(); /*This is the name to be assigned to the Item*/
		return parts;
	}
	public void onExit(final Player player, String flag)
	{
		final FileConfiguration configFile = pCops.getPlugin().getConfig();
		
		StringBuilder equipmentPath = new StringBuilder("equipment.");
		equipmentPath.append(flag);
		equipmentPath.append(".items");
		List<String> equipmentItems = configFile.getStringList(equipmentPath.toString());
					
		String itemName = null;
		String[] parsedEquipmentData;
		
		ItemStack inHand = player.getItemInHand();
		
		for (String equipment : equipmentItems)
		{
			parsedEquipmentData = equipmentDataParser(equipment);
			itemName = parsedEquipmentData[1];
							
			for (ItemStack item : player.getInventory())
			{
				if (item != null)
				{
					ItemMeta meta = (ItemMeta)item.getItemMeta();
					if (meta != null && meta.getDisplayName() != null)
					{
						if (meta.getDisplayName().equalsIgnoreCase(itemName))
						{							
							if (inHand.isSimilar(item))
							{
								player.sendMessage(ChatColor.DARK_RED + "INSIDE inHand == item");
								/*player.getInventory().removeItem(player.getInventory().getItemInHand());*/
								player.getInventory().clear(player.getInventory().getHeldItemSlot());
								player.updateInventory();
							}
							else
							{
								player.sendMessage(ChatColor.DARK_RED + "INSIDE else");
								player.getInventory().removeItem(item);
								player.updateInventory();
							}
						}
					}
				}
			}
		}
	}
	
	public void onDrop(final PlayerDropItemEvent event, String flag)
	{
		final FileConfiguration configFile = pCops.getPlugin().getConfig();
		
		StringBuilder equipmentPath = new StringBuilder("equipment.");
		equipmentPath.append(flag);
		equipmentPath.append(".items");
		List<String> equipmentItems = configFile.getStringList(equipmentPath.toString());
					
		String itemName = null;
		String[] parsedEquipmentData;
		
		ItemStack item = event.getItemDrop().getItemStack();
		Player player = event.getPlayer();
				
		for (String equipment : equipmentItems)
		{
			parsedEquipmentData = equipmentDataParser(equipment);
			itemName = parsedEquipmentData[1];
							
			if (item != null)
			{
				ItemMeta meta = (ItemMeta)item.getItemMeta();
				if (meta != null && meta.getDisplayName() != null && itemName != null)
				{
					if (meta.getDisplayName().equalsIgnoreCase(itemName))
					{	
						event.setCancelled(true);
						player.getInventory().removeItem(item);
						/* May need to try
						 * player.getInventory().clear(player.getInventory().getHeldItemSlot());
						 */
						player.updateInventory();
					}
				}
			}
		}
	}
}
