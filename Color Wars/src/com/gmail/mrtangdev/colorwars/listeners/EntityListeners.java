package com.gmail.mrtangdev.colorwars.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.gmail.mrtangdev.colorwars.ColorWars;
import com.gmail.mrtangdev.colorwars.game.GameBonus;
import com.gmail.mrtangdev.colorwars.game.GameHandler;
import com.gmail.mrtangdev.colorwars.game.GameState;
import com.gmail.mrtangdev.colorwars.utils.Chat;

public class EntityListeners implements Listener {

    public Map<String, Integer> playerKills = new HashMap<>();

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
	if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
	    Player damaged = (Player) event.getEntity();
	    Player damager = (Player) event.getDamager();

	    if (damager.getItemInHand().getDurability() == damaged.getInventory().getHelmet().getDurability()) {
		damaged.setHealth(0);
		Chat.message(damager, "You killed " + damaged.getDisplayName());
		Chat.message(damaged, "You got killed by " + damager.getDisplayName());
		
		if (playerKills.get(damager.getName()) == null) {
		    playerKills.put(damager.getName(), 1);
		    Chat.message(damager, "You have 1 kill");
		} else {
		    playerKills.put(damager.getName(), playerKills.get(damager.getName()) + 1);
		    int kills = playerKills.get(damager.getName());
		    Chat.message(damager, "You have " + kills + " kills");
		    
		    if (kills == 5 || kills == 10 || kills == 15) {
			GameBonus.killStreak(damager, kills);
		    }
		    
		    if (kills == ColorWars.killAmount) {
			Chat.broadcast("GAME HAS ENDED!");
			Chat.broadcast("CONGRATULATIONS! THE WINNER IS " + damager.getDisplayName());
			GameHandler.stopGame();
		    }
		}
	    } else {
		Chat.message(damager, "You can only hit people with the same color as your dye.");
	    }
	}
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
	if (event.getEntity() instanceof Player) {
	    Player player = (Player) event.getEntity();
	    if (GameState.getState() != GameState.STARTED) {
		event.setCancelled(true);
	    }
	}
    }

}
