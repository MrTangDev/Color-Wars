package com.gmail.mrtangdev.colorwars.timers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.mrtangdev.colorwars.utils.Chat;

public class EndingTimer extends BukkitRunnable {

    private int time = 10;

    @Override
    public void run() {

	if (time == 0) {
	    Chat.broadcast("SHUTTING DOWN SERVER");
	    this.cancel();
	    Bukkit.getServer().shutdown();
	}
	
	time--;
    }


}
