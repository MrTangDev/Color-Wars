package com.gmail.mrtangdev.colorwars.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gmail.mrtangdev.colorwars.utils.Chat;

public class BlockListeners implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
	Player player = event.getPlayer();

	if (player.getGameMode() != GameMode.CREATIVE) {
	    event.setCancelled(true);
	    Chat.message(player, "You are not allowed to break blocks.");
	}
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
	Player player = event.getPlayer();

	if (player.getGameMode() != GameMode.CREATIVE) {
	    event.setCancelled(true);
	    Chat.message(player, "You are not allowed to place blocks.");
	}
    }

}
