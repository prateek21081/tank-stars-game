package com.tankstars.game;

public class Player {
    private String name;
    private Tank tank;

    public Player(String playerName, String tankName) {
        this.name = playerName;
    }

    public void createTank (String tankName) {
        switch (tankName) {
            case "Helios":
                tank = new TankHelios();
                break;
            case "Coalition":
                tank = new TankCoalition();
                break;
            case "MarkOne":
                tank = new TankMarkOne();
                break;
        }
    }

    public void play () {

    }

}
