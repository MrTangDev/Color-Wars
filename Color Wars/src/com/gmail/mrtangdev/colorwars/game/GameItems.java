package com.gmail.mrtangdev.colorwars.game;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class GameItems {

    public static Random rand = new Random();

    public static byte[] itemstack = new byte[] {
	1, 2, 3, 4, 5, 14, 8, 9, 11
	//red green brown blue purple orange gray pink yellow
    };


    public static void clearPlayer(Player player) {
	player.getInventory().clear();
	player.getInventory().setArmorContents(null);
	player.setFireTicks(0);

	for (PotionEffect effect : player.getActivePotionEffects()) {
	    player.removePotionEffect(effect.getType());
	}
    }

    public static void giveItems(Player player) {
	player.getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (byte) rand.nextInt(itemstack.length)));
	player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) rand.nextInt(itemstack.length)));
    }

}
