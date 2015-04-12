package com.gmail.mrtangdev.colorwars.game;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.mrtangdev.colorwars.utils.Chat;

public class GameBonus {

    public static void killStreak(Player player, int streak) {
	if (streak == 5) {
	    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60*20, 1));
	    Chat.message(player, "You got a 5 killstreak. Here you got some SPEED I");
	} else if (streak == 10) {
	    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60*20, 1));
	    Chat.message(player, "You got a 10 killstreak. Here you got some JUMP I");
	} else if (streak == 15) {
	    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60*20, 2));
	    Chat.message(player, "You got a 5 killstreak. Here you got some SPEED II");
	}
    }

}
