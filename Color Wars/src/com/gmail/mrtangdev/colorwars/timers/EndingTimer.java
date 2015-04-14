package com.gmail.mrtangdev.colorwars.timers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.mrtangdev.colorwars.utils.Chat;

public class EndingTimer extends BukkitRunnable {

    private int time = 20;

    @Override
    public void run() {
	if (time == 0) {
	    Chat.broadcast("SHUTTING DOWN SERVER");
	    this.cancel();
	    Bukkit.getServer().shutdown();
	}
	
	if (time <= 5) {
	    Chat.broadcast("Server shutting down in " + time + (time == 1 ? " second" : " seconds"));
	}
	
	time--;
    }

}
