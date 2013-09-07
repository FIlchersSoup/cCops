package com.digitalbrainery.pCops.listeners;

import com.digitalbrainery.pCops.pCops;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author FilchersSoup
 */
public class removeEquipment
{
	public void onExit(final Player player, String flag)
	{
		final FileConfiguration configFile = pCops.getPlugin().getConfig();
		
		StringBuilder equipmentPath = new StringBuilder("equipment.");
		equipmentPath.append(flag);
		equipmentPath.append(".items");
					
		StringBuilder itemName = new StringBuilder();
		
		List<String> equipmentItems = configFile.getStringList(equipmentPath.toString());
		String[] parsedEquipmentData;
		int itemId = 0;
		
		ItemStack inHand = player.getItemInHand();
		
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
					}
				}
			}
				
			for (ItemStack item : player.getInventory())
			{
				if (item != null)
				{
					ItemMeta meta = (ItemMeta)item.getItemMeta();
					if (meta != null && meta.getDisplayName() != null)
					{
						if (meta.getDisplayName().equalsIgnoreCase(itemName.toString()))
						{							
							/*if (inHand != null)
							{
								ItemMeta inHandMeta = (ItemMeta)inHand.getItemMeta();
								if (inHandMeta != null && inHandMeta.getDisplayName() != null)
								{
									if (inHandMeta.getDisplayName().equalsIgnoreCase(itemName.toString()))
									{
										player.getInventory().setItemInHand(null);
										player.updateInventory();
									}
								}
							}*/
							if (inHand.isSimilar(item))
							{
								player.sendMessage(ChatColor.DARK_RED + "INSIDE inHand == item");
								/*player.getInventory().removeItem(player.getInventory().getItemInHand());*/
								player.getInventory().clear(player.getInventory().getHeldItemSlot());
								player.updateInventory();
							}
							
							player.sendMessage(ChatColor.DARK_RED + "Removing Item");
							player.getInventory().removeItem(item);
							player.updateInventory();
							
						}
					}
				}
			}
					
			itemName = new StringBuilder();
		}
	}
}
