package com.gmail.mrtangdev.colorwars.timers;

import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.mrtangdev.colorwars.game.GameState;
import com.gmail.mrtangdev.colorwars.utils.Chat;

public class GraceTimer extends BukkitRunnable {

    private int time = 10;
    
    @Override
    public void run() {
	if (time == 0) {
	    Chat.broadcast("Grace period has ended. You are no longer invincible.");
	    GameState.setState(GameState.STARTED);
	    this.cancel();
	    return;
	}
	
	if (time <= 3) {
	    Chat.broadcast("Grace period ending in " + time + (time == 1 ? " second" : " seconds"));
	}
	
	time--;
    }

}
