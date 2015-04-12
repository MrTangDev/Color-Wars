package com.gmail.mrtangdev.colorwars.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Chat {

    private static final String TAG = ChatColor.GOLD + "[!] " + ChatColor.GRAY;
    
    public static void message(CommandSender sender, String msg) {
	sender.sendMessage(TAG + msg);
    }
    
    public static void broadcast(String msg) {
	Bukkit.broadcastMessage(TAG + msg);
    }
}
