package com.gmail.mrtangdev.colorwars.game;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class GameItems {

    private static Random rand = new Random();

    private static byte[] dyeStack = new byte[] {
	1, 2, 3, 4, 5, 8, 11
	//red green brown blue purple gray yellow
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
	player.getInventory().clear();
	player.getInventory().setArmorContents(null);
	
	player.getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (byte) dyeStack[rand.nextInt(dyeStack.length)]));
	player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) dyeStack[rand.nextInt(dyeStack.length)]));
	player.updateInventory();
    }

}
