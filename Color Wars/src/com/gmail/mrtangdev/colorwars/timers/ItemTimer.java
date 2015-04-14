package com.gmail.mrtangdev.colorwars.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.mrtangdev.colorwars.game.GameItems;
import com.gmail.mrtangdev.colorwars.game.GameState;

public class ItemTimer extends BukkitRunnable {

    @Override
    public void run() {
	if (GameState.getState() == GameState.STARTED) {
	    for (Player player : Bukkit.getOnlinePlayers()) {
		GameItems.giveItems(player);
	    }
	} else {
	    this.cancel();
	    return;
	}
    }

}
