package com.gmail.mrtangdev.colorwars.timers;

import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.mrtangdev.colorwars.ColorWars;
import com.gmail.mrtangdev.colorwars.game.GameHandler;
import com.gmail.mrtangdev.colorwars.utils.Chat;

public class WaitTimer extends BukkitRunnable {

    public int countdown = ColorWars.waitTime;

    @Override
    public void run() {
	if (countdown == 0) {
	    Chat.broadcast("GAME HAS STARTED!");

	    GameHandler.startGame();
	    this.cancel();

	}

	if (countdown % 60 == 0) {
	    Chat.broadcast("Game starting in " + countdown / 60 + (countdown / 60 == 1 ? " minute" : " minutes"));
	} else if (countdown % 10 == 0) {
	    Chat.broadcast("Game starting in " + countdown + " seconds");
	} else if (countdown <= 5 && countdown >= 1) {
	    Chat.broadcast("Game starting in " + countdown + (countdown == 1 ?  " second" : " seconds"));
	}

	countdown--;
    }
}
