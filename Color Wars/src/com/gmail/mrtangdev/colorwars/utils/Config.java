package com.gmail.mrtangdev.colorwars.utils;

public class Config {

    private static int MIN_PLAYERS = 4;
    private static int MAX_PLAYERS = 16;
    private static int WAIT_TIME = 30;
    private static int KILL_AMOUNT = 25;
    
    public static int getMinimumPlayers() {
	return MIN_PLAYERS;
    }
    public static void setMinimumPlayers(int amount) {
	MIN_PLAYERS = amount;
    }
    
    public static int getMaximumPlayers() {
	return MAX_PLAYERS;
    }
    public static void setMaximumPlayers(int amount) {
	MAX_PLAYERS = amount;
    }
    
    public static int getWaitTime() {
	return WAIT_TIME;
    }
    public static void setWaitTime(int time) {
	WAIT_TIME = time;
    }
    
    public static int getKillAmount() {
	return KILL_AMOUNT;
    }
    public static void setKillAmount(int killAmount) {
	KILL_AMOUNT = killAmount;
    }
    
}
