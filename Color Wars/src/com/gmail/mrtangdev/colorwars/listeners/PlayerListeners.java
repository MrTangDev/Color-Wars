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
import com.gmail.mrtangdev.colorwars.utils.Config;

public class PlayerListeners implements Listener {

    private Random rand = new Random();

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
	Player player = event.getPlayer();

	if ((GameState.getState() != GameState.WAITING) && (!player.isOp())) {
	    event.disallow(Result.KICK_OTHER, "Game has already started.");
	}
	if (Bukkit.getOnlinePlayers().length >= Config.getMaximumPlayers()) {
	    event.disallow(Result.KICK_OTHER, "Game has enough players.");
	}
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	Player player = event.getPlayer();

	Chat.message(player, "To kill people you have to punch the player with the same color as they have on their head");
	Chat.message(player, "The colors change every two seconds, so be careful");
	Chat.message(player, "First player to " + Config.getKillAmount() + " kills win.");

	if (GameState.getState() == GameState.WAITING) {
	    int playerAmount = Bukkit.getOnlinePlayers().length;

	    if (playerAmount >= Config.getMinimumPlayers()) {
		new WaitTimer().runTaskTimer(ColorWars.getInstance(), 20, 20);
	    } else {
		Chat.broadcast("Need " + (Config.getMinimumPlayers() - playerAmount) + " players to start.");
	    }
	}
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
	World world = ColorWars.getWorld();
	event.setRespawnLocation(world.getSpawnLocation().add(rand.nextInt(20), 0, rand.nextInt(20)));
    }

}
