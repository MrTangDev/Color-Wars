package com.gmail.mrtangdev.colorwars.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.mrtangdev.colorwars.game.GameItems;

public class ItemTimer extends BukkitRunnable {

    @Override
    public void run() {
	for (Player player : Bukkit.getOnlinePlayers()) {
	    GameItems.clearPlayer(player);
	    GameItems.giveItems(player);
	}
    }

}
