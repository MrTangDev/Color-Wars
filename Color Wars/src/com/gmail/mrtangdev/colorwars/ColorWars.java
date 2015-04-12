package com.gmail.mrtangdev.colorwars;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.mrtangdev.colorwars.game.GameState;
import com.gmail.mrtangdev.colorwars.listeners.BlockListeners;
import com.gmail.mrtangdev.colorwars.listeners.EntityListeners;
import com.gmail.mrtangdev.colorwars.listeners.GeneralListeners;
import com.gmail.mrtangdev.colorwars.listeners.PlayerListeners;

public class ColorWars extends JavaPlugin {
/*
 * Color Wars
 * No teams, 16 max, minimum 4
 * Objective: Get 25 kills
 * When starting, you become a random color, it will change every 2 seconds
 * To kill another player you must use the random color you have 
 * If you can't, run the fuck away
 * 1 shot 1 kill
 * 5, 10 and 15 kill bonus: 1 minute Speed I, jump boost II and speed II
 * Configuration: Player limit and kill objective and wait time
 * 
 */
    private static ColorWars instance;
    private static World world;
    
    public static int minPlayers = 4;
    public static int maxPlayers = 16;
    public static int waitTime = 30;
    public static int killAmount = 25;
    
    
    @Override
    public void onEnable() {
	instance = this;
	
	setupConfig();
	
	registerListeners();
	GameState.setState(GameState.WAITING);
	world = Bukkit.getWorld("world");
	world.setSpawnLocation(0, world.getHighestBlockYAt(world.getSpawnLocation()) + 5, 0);
    }
    
    @Override
    public void onDisable() {
	instance = null;
    }
    
    private void setupConfig() {
	if (!this.getDataFolder().exists()) {
	    this.getDataFolder().mkdirs();
	}
	getConfig().options().copyDefaults(true);
	saveConfig();

	minPlayers = getConfig().getInt("max-players");
	maxPlayers = getConfig().getInt("min-players");
	waitTime = getConfig().getInt("wait-time");
	killAmount = getConfig().getInt("kill-amount");
    }
    
    private void registerListeners() {
	PluginManager pm = Bukkit.getPluginManager();
	pm.registerEvents(new BlockListeners(), this);
	pm.registerEvents(new EntityListeners(), this);
	pm.registerEvents(new GeneralListeners(), this);
	pm.registerEvents(new PlayerListeners(), this);
    }

    public static ColorWars getInstance() {
	return instance;
    }
    
    public static World getWorld() {
	return world;
    }

}
