package com.gmail.mrtangdev.colorwars.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class GeneralListeners implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
	event.setFoodLevel(20);
    }

}
