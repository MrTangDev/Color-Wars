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
import com.gmail.mrtangdev.colorwars.utils.Config;

public class ColorWars extends JavaPlugin {

    private static ColorWars instance;
    
    private static World world;
    
    @Override
    public void onEnable() {
	instance = this;
	
	setupConfig();
	
	registerListeners();
	GameState.setState(GameState.WAITING);
	world = Bukkit.getWorld("world");
	world.setSpawnLocation(0, 100, 0);
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

	Config.setMaximumPlayers(getConfig().getInt("max-players"));
	Config.setMinimumPlayers(getConfig().getInt("min-players"));
	Config.setWaitTime(getConfig().getInt("wait-time"));
	Config.setKillAmount(getConfig().getInt("kill-amount"));
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
