package com.gmail.mrtangdev.colorwars.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.gmail.mrtangdev.colorwars.ColorWars;
import com.gmail.mrtangdev.colorwars.timers.EndingTimer;
import com.gmail.mrtangdev.colorwars.timers.GraceTimer;
import com.gmail.mrtangdev.colorwars.timers.ItemTimer;
import com.gmail.mrtangdev.colorwars.utils.Chat;

public class GameHandler {
    
    public static void startGame() {
	GameState.setState(GameState.INVINCIBLE);
	new GraceTimer().runTaskTimer(ColorWars.getInstance(), 0, 20);
	
	for (Player player : Bukkit.getOnlinePlayers()) {
	    if (player.getGameMode() != GameMode.CREATIVE) {
		player.teleport(ColorWars.getWorld().getSpawnLocation());
		Chat.message(player, "You are invincible for 10 seconds.");
		new ItemTimer().runTaskTimer(ColorWars.getInstance(), 200, 40);
	    }
	}
    }
    
    public static void stopGame() {
	GameState.setState(GameState.ENDING);
	new EndingTimer().runTaskTimer(ColorWars.getInstance(), 0, 20);
	
	for (Player player : Bukkit.getOnlinePlayers()) {
	    GameItems.clearPlayer(player);
	    player.setAllowFlight(true);
	    player.setFlying(true);
	}
    }

}
