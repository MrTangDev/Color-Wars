package com.gmail.mrtangdev.colorwars.game;

public enum GameState {

    WAITING, INVINCIBLE, STARTED, ENDING;

    private static GameState state;

    public static GameState getState() {
	return state;
    }

    public static void setState(GameState state) {
	GameState.state = state;
    }
}
