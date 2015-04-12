package com.gmail.mrtangdev.colorwars.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.gmail.mrtangdev.colorwars.ColorWars;
import com.gmail.mrtangdev.colorwars.game.GameState;
import com.gmail.mrtangdev.colorwars.timers.WaitTimer;
import com.gmail.mrtangdev.colorwars.utils.Chat;

public class PlayerListeners implements Listener {

    private Random rand = new Random();
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
	Player player = event.getPlayer();
	
	if ((GameState.getState() != GameState.WAITING) && (!player.isOp())) {
	    event.disallow(Result.KICK_OTHER, "Game has already started.");
	}
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	Player player = event.getPlayer();
	
	if (GameState.getState() == GameState.WAITING) {
	    Chat.message(player, "Welcome to Color Wars " + player.getName());
	    if ((Bukkit.getOnlinePlayers().length >= ColorWars.minPlayers) && (Bukkit.getOnlinePlayers().length <= ColorWars.maxPlayers)) { //remember
		new WaitTimer().runTaskTimer(ColorWars.getInstance(), 20, 20);
	    }
	}
	
	Chat.message(player, "To kill people you have to punch the player with the same color as they have on their head");
	Chat.message(player, "The colors change every two seconds, so be careful");
	Chat.message(player, "First player to " + ColorWars.killAmount + " kills win.");
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
	Player player = event.getPlayer();
	World world = ColorWars.getWorld();
	player.teleport(world.getSpawnLocation().add(rand.nextInt(20), world.getHighestBlockYAt(world.getSpawnLocation()) + 5, 20));
    }

}
