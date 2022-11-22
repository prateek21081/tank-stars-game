package com.tankstars.game;

public class Player {
    private String name;
    private Tank tank;

    public Player(String playerName, String tankName) {
        this.name = playerName;
    }

    public void createTank (String tankName) {
        this.tank = new Tank();
    }

    public void play () {

    }

}
