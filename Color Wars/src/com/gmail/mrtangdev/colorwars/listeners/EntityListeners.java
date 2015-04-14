package com.gmail.mrtangdev.colorwars.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.gmail.mrtangdev.colorwars.game.GameBonus;
import com.gmail.mrtangdev.colorwars.game.GameHandler;
import com.gmail.mrtangdev.colorwars.game.GameState;
import com.gmail.mrtangdev.colorwars.utils.Chat;
import com.gmail.mrtangdev.colorwars.utils.Config;

public class EntityListeners implements Listener {

    public Map<String, Integer> playerKills = new HashMap<>();
    public Map<String, Integer> killStreak = new HashMap<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
	if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
	    Player damaged = (Player) event.getEntity();
	    Player damager = (Player) event.getDamager();

	    if ((damager.getItemInHand().getType() == Material.INK_SACK) && (damaged.getInventory().getHelmet().getType() == Material.WOOL)) {
		DyeColor dyeColor =  DyeColor.getByDyeData(damager.getItemInHand().getData().getData());
		DyeColor woolColor = DyeColor.getByWoolData(damaged.getInventory().getHelmet().getData().getData());

		if (dyeColor.getColor() == woolColor.getColor()) {

		    damaged.setHealth(0);
		    Chat.message(damager, "You killed " + damaged.getDisplayName());
		    Chat.message(damaged, "You got killed by " + damager.getDisplayName());
		    
		    if (killStreak.get(damaged.getName()) != null) {
			killStreak.put(damaged.getName(), 0);
		    }

		    if (playerKills.get(damager.getName()) == null) {
			playerKills.put(damager.getName(), 1);
			killStreak.put(damager.getName(), 1);
			Chat.message(damager, "You have 1 kill");
		    } else {

			playerKills.put(damager.getName(), playerKills.get(damager.getName()) + 1);
			int kills = playerKills.get(damager.getName());
			Chat.message(damager, "You have " + kills + " kills");

			int playerStreak = killStreak.get(damager.getName());
			killStreak.put(damager.getName(), playerStreak + 1);
			if (playerStreak == 5 || playerStreak == 10 || playerStreak == 15) {
			    GameBonus.killStreak(damager, playerStreak);
			}

			if (kills == Config.getKillAmount()) {
			    Chat.broadcast("GAME HAS ENDED!");
			    Chat.broadcast("CONGRATULATIONS! THE WINNER IS " + damager.getDisplayName().toUpperCase());
			    GameHandler.stopGame();
			}
		    }
		} else {
		    Chat.message(damager, "You can only hit people with the same color as your dye.");
		    damager.damage(4.0D);
		    event.setCancelled(true);
		}
	    }
	}
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
	if (event.getEntity() instanceof Player) {
	    if (GameState.getState() != GameState.STARTED) {
		event.setCancelled(true);
	    } else {
		if (event.getCause() == DamageCause.FALL || event.getCause() == DamageCause.SUFFOCATION) {
		    event.setCancelled(true);
		}
	    }
	}
    }

}
