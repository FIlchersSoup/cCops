package com.digitalbrainery.pCops;
	
import com.digitalbrainery.pCops.commands.pCopsCmdHandler;
import com.digitalbrainery.pCops.listeners.pCopsListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author FilchersSoup
 */
public class pCops extends JavaPlugin {
 
	private static pCopsPlayerFile playerFile = new pCopsPlayerFile();
	private static Plugin plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
		reload();
		playerFile.PlayerFileAccessor(this,"pCops.yml");
		playerFile.saveDefaultFile();
		this.saveDefaultConfig();
		getLogger().info("pConditions has been enabled!");
	}
 
	@Override
	public void onDisable(){
		playerFile.saveFile();
		this.saveConfig();
		getLogger().info("pConditions has been disabled!");
	}
	
	public void reload()
	{
		final PluginManager pm = getServer().getPluginManager();
		registerListeners(pm);
	}
	
	private void registerListeners(PluginManager pm)
	{

		final pCopsListener pListener = new pCopsListener();
		pm.registerEvents(pListener, this);
		
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args)
	{
		if(command.getName().equalsIgnoreCase("pcops"))
		{
			final pCopsCmdHandler cmdHandler = new pCopsCmdHandler();
			return cmdHandler.pConditionsonCommand(sender, command, commandLabel, args);
		}
				
		return false;
	}
	
	public static Plugin getPlugin() 
	{
    return plugin;
	}
	
	public static pCopsPlayerFile getPlayerFile() 
	{
    return playerFile;
	}
}
