package com.tankstars.game;

import com.badlogic.gdx.physics.box2d.World;

public class Player {
    public Boolean isMain;
    private String name;
    private String tankName;
    private Tank tank;
    private World world;

    public Player(String playerName, String tankName, Boolean isMain) {
        this.name = playerName;
        this.tankName = tankName;
        this.isMain = isMain;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void createTank () {
        switch (this.tankName) {
            case "Helios":
                this.tank = new TankHelios(this, this.world);
                break;
            case "Coalition":
                this.tank = new TankCoalition(this, this.world);
                break;
            case "MarkOne":
                this.tank = new TankMarkOne(this, this.world);
                break;
        }
    }

    public void play () {

    }

    public void fire(float power, float angle) {
        this.tank.fire(power, angle);
    }

    public void handleHit() {
        tank.handleHit();
    }

    public boolean checkDead() {
        if (tank.getHealth() <= 0) {
            return true;
        }
        return false;
    }
}
